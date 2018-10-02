package tests;

import config.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestRunner {

    @BeforeMethod
    @Parameters(value = {"browser", "platform"})
    public void beforeMethod(@Optional String browser, @Optional String platform) {
    }

    @AfterMethod
    public void afterMethod() {
    }

    protected WebDriver getWebDriver() {
        return DriverManager.getWebDriver();
    }
}
