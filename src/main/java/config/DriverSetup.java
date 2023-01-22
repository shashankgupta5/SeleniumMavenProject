package config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface DriverSetup {
    WebDriver getWebDriverObject(Capabilities capabilities);

    Capabilities getCapabilities();
}
