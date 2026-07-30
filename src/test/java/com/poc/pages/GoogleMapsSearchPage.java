package com.poc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the Google Maps search flow (https://www.google.com/maps).
 */
public class GoogleMapsSearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.xpath("//input[@role='combobox']");
    private final By searchButton = By.xpath("//button[@class='mL3xi']");
    private final By resultsFeed = By.cssSelector("div[role='feed']");
    private final By resultLinks = By.cssSelector("div[role='feed'] a[href*='/maps/place/']");
    private final By consentButton = By.xpath(
            "//button[contains(translate(., 'ACEOPTNY', 'aceoptny'), 'accept')]");

    public GoogleMapsSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://www.google.com/maps");
    }

    /**
     * Google shows an EU/region-dependent cookie consent page on first visit.
     * Best-effort dismissal: absent when not shown, so it never fails the
     * test if the consent step doesn't appear for the current session/region.
     */
    public void dismissConsentIfPresent() {
        try {
            WebElement consent = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(consentButton));
            consent.click();
        } catch (TimeoutException ignored) {
        }
    }

    public void searchFor(String term) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(term);
        driver.findElement(searchButton).click();
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsFeed));
        wait.until(ExpectedConditions.presenceOfElementLocated(resultLinks));
        return driver.findElements(resultLinks);
    }
}
