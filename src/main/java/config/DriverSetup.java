package config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface DriverSetup {
    WebDriver getWebDriverObject(MutableCapabilities capabilities);
    MutableCapabilities getCapabilities();
}
