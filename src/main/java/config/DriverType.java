package config;

import common.Constants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

public enum DriverType implements DriverSetup {

    FIREFOX() {
        public MutableCapabilities getCapabilities() {
            return new FirefoxOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, Constants.PATH_TO_GECKO_DRIVER_EXE);
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
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, Constants.PATH_TO_CHROME_DRIVER_EXE);
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
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, Constants.PATH_TO_CHROME_DRIVER_EXE);
            return new ChromeDriver((ChromeOptions) capabilities);
        }
    },
    IE() {
        public MutableCapabilities getCapabilities() {
            return new InternetExplorerOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, Constants.PATH_TO_IE_DRIVER_EXE);

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
            System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, Constants.PATH_TO_EDGE_DRIVER_EXE);
            return new EdgeDriver((EdgeOptions) capabilities);
        }
    };
}
