package com.poc.tests;

import com.poc.base.BaseTest;
import com.poc.pages.GoogleMapsSearchPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Searches for "Restaurants" and asserts that at least one
 * business result is returned in the results feed.
 */
public class RestaurantSearchTest extends BaseTest {

    @Test(description = "Searching Google Maps for 'Restaurants' returns at least one result")
    public void verifyRestaurantSearchReturnsResults() {
        GoogleMapsSearchPage searchPage = new GoogleMapsSearchPage(driver);
        searchPage.open();
        searchPage.dismissConsentIfPresent();
        searchPage.searchFor("Restaurants");

        List<WebElement> results = searchPage.getSearchResults();
        Assert.assertFalse(results.isEmpty(), "Expected at least one restaurant result, but found none");
    }
}
