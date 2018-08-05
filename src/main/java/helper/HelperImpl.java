package helper;

import interfaces.Helper;
import org.openqa.selenium.WebDriver;

public class HelperImpl implements Helper {
    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(WebDriver driverToSet) {
        driver = driverToSet;
    }
}
