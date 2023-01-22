package config;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class DriverFactory {

    public static WebDriver createWebDriverInstance(String browserName, String platform) throws MalformedURLException {
        DriverType driverType = DriverType.FIREFOX;
        try {
            driverType = DriverType.valueOf(browserName.toUpperCase());
        } catch (Exception e) {
            logger.error("Unable to determine web driver type, defaulting to {}", driverType.toString());
        }

        return getWebDriverFromConfiguration(driverType, platform);
    }

    private static WebDriver getWebDriverFromConfiguration(DriverType driverType, String platform) throws MalformedURLException {
        WebDriver driver;
        Capabilities capabilities = driverType.getCapabilities();
        if (PropertyReader.useRemoteWebDriver()) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.merge(capabilities);
            if (StringUtils.isNotBlank(platform)) {
                desiredCapabilities.setPlatform(Platform.valueOf(platform.toUpperCase()));
            }
            driver = new RemoteWebDriver(new URL(PropertyReader.getGridURL()), desiredCapabilities);
        } else {
            driver = driverType.getWebDriverObject(capabilities);
        }

        configureWebDriver(driver);

        logger.info("{} Driver on {} OS started successfully",
                driverType.toString().toUpperCase(), platform.toUpperCase());

        return driver;
    }

    private static void configureWebDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertyReader.getImplicitWaitTimeOut()));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
}
