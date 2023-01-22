package config;

import common.Constants;
import org.openqa.selenium.Capabilities;
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
        public Capabilities getCapabilities() {
            return new FirefoxOptions();
        }

        public WebDriver getWebDriverObject(Capabilities capabilities) {
            return new FirefoxDriver((FirefoxOptions) capabilities);
        }
    },
    CHROME() {
        public Capabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
            return chromeOptions;
        }

        public WebDriver getWebDriverObject(Capabilities capabilities) {
            return new ChromeDriver((ChromeOptions) capabilities);
        }
    },
    CHROME_HEADLESS() {
        public Capabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions", "--disable-notifications", "disable-infobars");
            chromeOptions.setHeadless(true);
            return chromeOptions;
        }

        public WebDriver getWebDriverObject(Capabilities capabilities) {
            return new ChromeDriver((ChromeOptions) capabilities);
        }
    };
}
