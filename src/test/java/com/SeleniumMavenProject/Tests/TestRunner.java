package com.SeleniumMavenProject.Tests;

import java.lang.reflect.Method;

import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Common.ScreenshotListener;
import com.SeleniumMavenProject.Config.Configuration;
import com.SeleniumMavenProject.Config.DriverFactory;
import com.SeleniumMavenProject.Config.DriverManager;

import ru.yandex.qatools.allure.annotations.Step;

@Listeners(ScreenshotListener.class)
public class TestRunner {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		CustomLogger.initLogger();
		Configuration.initConfigFile();
	}

	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "browser", "platform" })
	public void beforeTest(String browser, String platform) throws Exception {
		DriverManager.setWebDriver(DriverFactory.createWebDriverInstance(browser, platform));
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
	public void afterTest() throws Exception {
		stopBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		Configuration.endConfigFile();
		CustomLogger.endLogger();
	}

	@Step("Set Browser Size")
	private void setBrowserSize() {
		Dimension browserSize = Configuration.getBrowserSize();

		if (browserSize != null) {
			DriverManager.getWebDriver().manage().window().setSize(browserSize);
		} else {
			DriverManager.getWebDriver().manage().window().maximize();
		}
	}

	@Step("Navigate to \"{0}\"")
	void navigateToUrl(String url) {
		DriverManager.getWebDriver().get(url);
	}

	@Step("Close Browser")
	private void stopBrowser() {
		if (DriverManager.getWebDriver() != null) {
			DriverManager.getWebDriver().quit();
			DriverManager.setWebDriver(null);
		}
	}
}
