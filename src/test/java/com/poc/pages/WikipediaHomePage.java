package com.poc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for https://www.wikipedia.org
 */
public class WikipediaHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logo = By.id("www-wikipedia-org");
    private final By searchInput = By.id("searchInput");
    private final By searchButton = By.cssSelector("button[type='submit']");

    public WikipediaHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.wikipedia.org");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public boolean isSearchInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
    }
}
