package com.SeleniumMavenProject.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public enum DriverType implements DriverSetup {

	FIREFOX() {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.firefox();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}
	},
	CHROME() {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.addArguments("chrome.switches", "--disable-extensions");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new ChromeDriver(capabilities);
		}
	},
	IE() {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.internetExplorer();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new InternetExplorerDriver(capabilities);
		}
	},
	EDGE() {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.edge();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new EdgeDriver(capabilities);
		}
	},
	SAFARI() {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("safari.cleanSession", true);
			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new SafariDriver(capabilities);
		}
	},
	PHANTOMJS() {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("takesScreenshot", true);
			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
			return new PhantomJSDriver(desiredCapabilities);
		}
	};
}
