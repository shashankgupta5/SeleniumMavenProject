package com.SeleniumMavenProject.Config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.SeleniumMavenProject.Common.CustomLogger;

public class DriverFactory {

	private static final DriverType defaultDriverType = DriverType.FIREFOX;

	public static WebDriver createWebDriverInstance(String browserName, String platform) throws MalformedURLException {
		WebDriver driver;
		DriverType driverType = defaultDriverType;
		try {
			driverType = DriverType.valueOf(browserName.toUpperCase());
		} catch (Exception e) {
			CustomLogger.logError("createWebDriverInstance: " + e.getMessage());
			CustomLogger.logInfo("createWebDriverInstance: unable to determine web driver type, defaulting to {"
					+ driverType.toString() + "}");
		}

		DesiredCapabilities capabilities = driverType.getDesiredCapabilities();

		if (Configuration.useRemoteWebDriver()) {
			if (platform != null && !platform.isEmpty()) {
				capabilities.setPlatform(Platform.valueOf(platform.toUpperCase()));
			}
			driver = new RemoteWebDriver(new URL(Configuration.getGridURL()), capabilities);
		} else {
			driver = driverType.getWebDriverObject(capabilities);
		}

		driver.manage().timeouts().implicitlyWait(Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		CustomLogger.logInfo(String.format("createWebDriverInstance: {%s} Driver on {%s} OS started successfully",
				driverType.toString().toUpperCase(), platform.toUpperCase()));

		return driver;
	}
}
