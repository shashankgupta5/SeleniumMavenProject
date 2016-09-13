package com.SeleniumMavenProject.Config;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.SeleniumMavenProject.Common.CustomLogger;

public class NewDriverProvider {
	private static WebDriver driver;
	private static String DRIVER_DIR = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "resources"
			+ File.separator + "Driver";

	private enum Browser {
		CHROME, FIREFOX, IE, EDGE, REMOTE, SAFARI
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	public static WebDriver createWebDriverInstance(String browser) {
		Browser b = Browser.valueOf(browser);

		switch (b) {
			// If browser equals IE set driver property as IEWebDriver instance
			case IE :

				System.setProperty(
						InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,
						DRIVER_DIR + File.separator + "IEDriver"
								+ File.separator + "IEDriverServer.exe");

				driver = new InternetExplorerDriver();
				break;

			case EDGE :

				System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY,
						"");

				driver = new EdgeDriver();
				break;

			// If browser equals FIREFOX set driver property as FirefoxWebDriver
			// instance
			case FIREFOX :

				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
						DRIVER_DIR + File.separator + "FirefoxDriver"
								+ File.separator + "geckodriver.exe");

				driver = new FirefoxDriver();
				break;

			// If browser equals CHROME set driver property as ChromeWebDriver
			// instance
			case CHROME :

				ChromeOptions chromeOptions = new ChromeOptions();

				System.setProperty(
						ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
						DRIVER_DIR + File.separator + "ChromeDriver"
								+ File.separator + "chromedriver.exe");

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
		driver.manage().deleteAllCookies();
		CustomLogger.logInfo(String.format(
				"createWebDriverInstance: {%s} Driver started successfully",
				browser));
		return driver;
	}
}
