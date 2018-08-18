package com.SeleniumMavenProject.tests;

import common.Retry;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.GooglePage;

@Test(groups = {"google_tests"}, retryAnalyzer = Retry.class)
public class GoogleTests extends TestRunner {

    @Description("Test method to search on google")
    public void testMethod_01_searchSomething() throws Exception {
        try {
            GooglePage googlePage = new GooglePage(getWebDriver());
            googlePage.navigateToUrl("https://www.google.co.in/");
            googlePage.searchSomething("cheese");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
