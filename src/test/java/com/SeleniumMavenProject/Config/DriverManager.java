package com.SeleniumMavenProject.Config;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}
}
