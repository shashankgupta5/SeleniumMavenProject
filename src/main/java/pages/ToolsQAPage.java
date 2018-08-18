package pages;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class ToolsQAPage extends BasePage {

    @FindBy(xpath = "//*[@id='content']/table/thead/tr/following::tr")
    private List<WebElement> tableData;

    @FindBy(id = "button1")
    private WebElement newWindowBtn;

    @FindBy(xpath = "//*[@id='content']/p[4]/button")
    private WebElement newBrowserTabBtn;

    public ToolsQAPage(WebDriver driver) {
        super(driver);
    }

    public void getPracticeTableData() {
        assertThat(getDriver().getTitle(), Is.is("Demo Table for practicing Selenium Automation"));

        StringBuilder builder = new StringBuilder();
        tableData.stream().map(WebElement::getText).filter(StringUtils::isNotBlank).forEach(builder::append);

        logger.info(builder.toString());
        assertThat("getPracticeTableData: table conent shouldn't be null", builder.toString(),
                containsString("Total 4 buildings"));
    }

    public void performWindowSwitching() {
        assertThat(getDriver().getTitle(), Is.is("Demo Windows for practicing Selenium Automation"));
        waitAndClick(newWindowBtn);
        switchToBrowserTab();
        assertThat("performWindowSwitching: window count to be '2'", getWindowCount(), Is.is(2));
        closeCurrentBroserTab();
        assertThat("performWindowSwitching: window count to be '1'", getWindowCount(), Is.is(1));
        switchToBrowserTab();

        waitAndClick(newBrowserTabBtn);
        switchToBrowserTab();
        assertThat("performWindowSwitching: window count to be '2'", getWindowCount(), Is.is(2));
        closeCurrentBroserTab();
        assertThat("performWindowSwitching: window count to be '1'", getWindowCount(), Is.is(1));
        switchToBrowserTab();
    }
}
