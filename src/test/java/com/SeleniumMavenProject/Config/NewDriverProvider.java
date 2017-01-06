package com.SeleniumMavenProject.Config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.SeleniumMavenProject.Common.CustomLogger;

public class NewDriverProvider {
	private static WebDriver driver;

	private enum Browser {
		CHROME, FIREFOX, IE, EDGE
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	public static WebDriver createWebDriverInstance() {
		Browser browser = Browser.valueOf(Configuration.getBrowser());

		switch (browser) {
			// If browser equals IE set driver property as IEWebDriver instance
			case IE :

				driver = new InternetExplorerDriver();
				break;

			case EDGE :

				driver = new EdgeDriver();
				break;

			// If browser equals FIREFOX set driver property as FirefoxWebDriver
			// instance
			case FIREFOX :

				DesiredCapabilities capabilities = DesiredCapabilities
						.firefox();
				driver = new FirefoxDriver(capabilities);
				break;

			// If browser equals CHROME set driver property as ChromeWebDriver
			// instance
			case CHROME :

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-extensions");

				driver = new ChromeDriver(chromeOptions);
				break;

			default :
				throw new RuntimeException(String.format(
						"createWebDriverInstance: Provided driver {%s} is not supported",
						browser));
		}

		driver.manage().timeouts().implicitlyWait(
				Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(
				Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		CustomLogger.logInfo(String.format(
				"createWebDriverInstance: {%s} Driver started successfully",
				browser));
		return driver;
	}
}
