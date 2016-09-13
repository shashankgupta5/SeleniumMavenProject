package com.SeleniumMavenProject.Tests;

import org.testng.annotations.Test;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Pages.GoogleHomePage;

@Test(groups = {"google_tests"})
public class GoogleTests extends TestRunner {

	public void testMethod_01_searchGoogle() throws Exception {
		try {
			navigateToUrl("http://www.google.com");

			GoogleHomePage googlePage = new GoogleHomePage();
			googlePage.searchThis("Selenium").writeUrls();
		} catch (Exception e) {
			CustomLogger
					.logError("testMethod_01_searchGoogle: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
