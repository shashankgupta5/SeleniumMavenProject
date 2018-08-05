package com.SeleniumMavenProject.tests;

import common.Retry;
import org.testng.annotations.Test;
import pages.GooglePage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Title("GoogleTests")
@Description("Tests for Google")
@Test(groups = {"google_tests"}, retryAnalyzer = Retry.class)
public class GoogleTests extends TestRunner {

    @Title("testMethod_01_searchSomething")
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
