package config;

import common.Constants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public enum DriverType implements DriverSetup {

	FIREFOX() {
		public MutableCapabilities getCapabilities() {
			return new FirefoxOptions();
		}

		public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
					Constants.getPathToGeckoDriverExe());
			return new FirefoxDriver((FirefoxOptions) capabilities);
		}
	},
	CHROME() {
		public MutableCapabilities getCapabilities() {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions
					.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
			return chromeOptions;
		}

		public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
					Constants.getPathToChromeDriverExe());
			return new ChromeDriver((ChromeOptions) capabilities);
		}
	},
	CHROME_HEADLESS() {
		public MutableCapabilities getCapabilities() {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions
					.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
			chromeOptions.setHeadless(true);
			return chromeOptions;
		}

		public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
					Constants.getPathToChromeDriverExe());
			return new ChromeDriver((ChromeOptions) capabilities);
		}
	};
}
