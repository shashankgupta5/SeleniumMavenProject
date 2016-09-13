package com.SeleniumMavenProject.Tests;

import java.lang.reflect.Method;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Common.ScreenshotListener;
import com.SeleniumMavenProject.Config.Configuration;
import com.SeleniumMavenProject.Config.NewDriverProvider;

@Listeners(ScreenshotListener.class)
public class TestRunner {

	private static WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		CustomLogger.initLogger();
		Configuration.initConfigFile();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		startBrowser();
		setBrowserSize();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method m) {
		CustomLogger.startTestCase(m);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method m) {
		CustomLogger.endTestCase(m);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		stopBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		Configuration.endConfigFile();
		CustomLogger.endLogger();
	}

	private void startBrowser() {
		driver = NewDriverProvider
				.createWebDriverInstance(Configuration.getBrowser());
	}

	private void setBrowserSize() {
		Dimension browserSize = Configuration.getBrowserSize();

		if (browserSize != null) {
			driver.manage().window().setSize(browserSize);
		} else {
			driver.manage().window().maximize();
		}
	}

	protected void navigateToUrl(String url) {
		driver.get(url);
	}

	private void stopBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
