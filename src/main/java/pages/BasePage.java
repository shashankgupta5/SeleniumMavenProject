package pages;

import helper.HelperImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends HelperImpl {

    public BasePage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
}
