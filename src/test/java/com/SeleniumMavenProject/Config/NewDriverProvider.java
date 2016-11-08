package com.SeleniumMavenProject.Config;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.SeleniumMavenProject.Common.CustomLogger;

public class NewDriverProvider {
	private static WebDriver driver;

	private enum Browser {
		CHROME, FIREFOX, IE, EDGE, REMOTE, SAFARI
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

				driver = new FirefoxDriver();
				break;

			// If browser equals CHROME set driver property as ChromeWebDriver
			// instance
			case CHROME :

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-extensions");

				driver = new ChromeDriver(chromeOptions);
				break;

			// If browser equals REMOTE set driver property as RemoteWebDriver
			// instance
			case REMOTE :

				try {
					driver = new RemoteWebDriver(
							new URL(Configuration.getGridURL()),
							DesiredCapabilities.firefox());
				} catch (Exception e) {
					CustomLogger.logError(String.format(
							"createWebDriverInstance: RemoteWebDriver {%s}",
							e.getMessage()));
				}
				break;

			// If browser equals SAFARI set driver property as Safari
			// instance
			case SAFARI :

				driver = new SafariDriver();
				break;

			default :
				throw new RuntimeException(String.format(
						"createWebDriverInstance: Provided driver {%s} is not supported",
						browser));
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		CustomLogger.logInfo(String.format(
				"createWebDriverInstance: {%s} Driver started successfully",
				browser));
		return driver;
	}
}
