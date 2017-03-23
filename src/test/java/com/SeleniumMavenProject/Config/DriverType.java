package com.SeleniumMavenProject.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public enum DriverType implements DriverSetup {

	FIREFOX("firefox") {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.firefox();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}
	},
	CHROME("chrome") {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.chrome();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new ChromeDriver(capabilities);
		}
	},
	IE("internet explorer") {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.internetExplorer();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new InternetExplorerDriver(capabilities);
		}
	},
	EDGE("MicrosoftEdge") {
		public DesiredCapabilities getDesiredCapabilities() {
			return DesiredCapabilities.edge();
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new EdgeDriver(capabilities);
		}
	},
	SAFARI("safari") {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("safari.cleanSession", true);
			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new SafariDriver(capabilities);
		}
	};

	private String value;

	private DriverType(String value) {
		this.value = value;
	}

	public String getBrowerName() {
		return this.value;
	}
}
