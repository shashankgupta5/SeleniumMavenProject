package pages;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class GooglePage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchBox;

    @FindBy(id = "resultStats")
    private WebElement status;

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void searchSomething(String text) {
        waitAndSendKeysLikeHuman(searchBox, text);
        getActionBuilder().sendKeys(Keys.ENTER).perform();
        waitForElementToBeVisible(status);
        String statusText = status.getText();
        logger.info(statusText);
        assertThat("searchSomething: serach text to be not blank", true, Is.is(StringUtils.isNotBlank(statusText)));
    }
}
