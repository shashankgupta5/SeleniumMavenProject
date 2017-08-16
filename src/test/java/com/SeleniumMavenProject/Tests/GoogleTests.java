package com.SeleniumMavenProject.Tests;

import org.testng.annotations.Test;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Common.Retry;
import com.SeleniumMavenProject.Config.DriverManager;
import com.SeleniumMavenProject.Pages.GooglePage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Title("GoogleTests")
@Description("Tests for Google")
@Test(groups = { "google_tests" }, retryAnalyzer = Retry.class)
public class GoogleTests extends TestRunner {

	@Title("testMethod_01_searchSomething")
	@Description("Test method to search on google")
	public void testMethod_01_searchSomething() throws Exception {
		try {
			navigateToUrl("https://www.google.co.in/");
			new GooglePage(DriverManager.getWebDriver()).searchSomething("cheese");
		} catch (Exception e) {
			CustomLogger.logError("testMethod_01_searchSomething: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
