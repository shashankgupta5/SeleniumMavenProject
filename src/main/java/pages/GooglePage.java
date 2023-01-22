package pages;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Slf4j
public class GooglePage extends AbstractPage {

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GooglePage navigateToPage() {
        navigateToUrl("https://www.google.co.in/");
        return this;
    }

    public GooglePage searchSomething(String text) {
        waitAndSendKeysLikeHuman(getPathToSearchBar(), text);
        getActionBuilder().sendKeys(Keys.ENTER).perform();
        return this;
    }

    @Override
    public void verify() {
        String statusText = waitAndGetText(getPathToResultStatus());
        logger.info(statusText);

        Assert.assertTrue(StringUtils.isNotBlank(statusText), "Search text to be not blank");
    }

    private String getPathToSearchBar() {
        return "//*[@name='q']";
    }

    private String getPathToResultStatus() {
        return "//*[@id='result-stats']";
    }
}
