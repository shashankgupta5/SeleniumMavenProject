package com.SeleniumMavenProject.Config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.SeleniumMavenProject.Common.CustomLogger;

public class DriverFactory {

	public static WebDriver createWebDriverInstance(String browserName, String platform) throws MalformedURLException {
		WebDriver driver = null;

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (browserName.equals(BrowserType.CHROME)) {
			capabilities.setBrowserName(BrowserType.CHROME);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
		} else if (browserName.equals(BrowserType.FIREFOX)) {
			capabilities.setBrowserName(BrowserType.FIREFOX);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
		} else if (browserName.equals(BrowserType.IE)) {
			capabilities.setBrowserName(BrowserType.IE);
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
		} else if (browserName.equals(BrowserType.EDGE)) {
			capabilities.setBrowserName(BrowserType.EDGE);
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
		} else {
			throw new RuntimeException(String.format(
					"createWebDriverInstance: Unable to create WebDriver Instance with {%s} browser and {%s} platform",
					browserName.toUpperCase(), platform.toUpperCase()));
		}

		driver = new RemoteWebDriver(new URL(Configuration.getGridURL()), capabilities);

		driver.manage().timeouts().implicitlyWait(Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Configuration.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		CustomLogger.logInfo(
				String.format("createWebDriverInstance: {%s} Driver started successfully", browserName.toUpperCase()));

		return driver;
	}
}
