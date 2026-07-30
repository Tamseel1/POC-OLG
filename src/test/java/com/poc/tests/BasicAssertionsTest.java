package com.poc.tests;

import com.poc.base.BaseTest;
import com.poc.pages.WikipediaHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAssertionsTest extends BaseTest {

    @Test(description = "Wikipedia home page loads with the expected title and key visible elements")
    public void verifyHomePageTitleAndElements() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.open();

        Assert.assertEquals(homePage.getPageTitle(), "Wikipedia", "Page title did not match expected value");
        Assert.assertTrue(homePage.isLogoDisplayed(), "Wikipedia logo was not visible");
        Assert.assertTrue(homePage.isSearchInputDisplayed(), "Search input field was not visible");
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Search button was not visible");
    }
}
