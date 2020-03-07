package config;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private DriverManager() {

	}

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public static void quitDriver() {
		if (getWebDriver() != null) {
			getWebDriver().close();
			getWebDriver().quit();
			webDriver.remove();
		}
	}
}
