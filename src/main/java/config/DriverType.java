package config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum DriverType implements DriverSetup {

    FIREFOX() {
        public MutableCapabilities getCapabilities() {
            return new FirefoxOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new FirefoxDriver((FirefoxOptions) capabilities);
        }
    },
    CHROME() {
        public MutableCapabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
            return chromeOptions;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new ChromeDriver((ChromeOptions) capabilities);
        }
    },
    CHROME_HEADLESS() {
        public MutableCapabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
            chromeOptions.setHeadless(true);
            return chromeOptions;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new ChromeDriver((ChromeOptions) capabilities);
        }
    },
    IE() {
        public MutableCapabilities getCapabilities() {
            return new InternetExplorerOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            InternetExplorerOptions ieCapabilities = (InternetExplorerOptions) capabilities;
            ieCapabilities.introduceFlakinessByIgnoringSecurityDomains();
            ieCapabilities.ignoreZoomSettings();

            return new InternetExplorerDriver(ieCapabilities);
        }
    },
    EDGE() {
        public MutableCapabilities getCapabilities() {
            return new EdgeOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new EdgeDriver((EdgeOptions) capabilities);
        }
    },
    SAFARI() {
        public MutableCapabilities getCapabilities() {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setCapability("safari.cleanSession", true);
            return safariOptions;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new SafariDriver((SafariOptions) capabilities);
        }
    };
}
