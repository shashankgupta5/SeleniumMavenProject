package com.SeleniumMavenProject.Pages;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SeleniumMavenProject.Common.CustomLogger;

public class ToolsQAPage extends BasePage {

	@FindBy(xpath = "//*[@id='content']/table/thead/tr/following::tr")
	private List<WebElement> tableData;

	@FindBy(id = "button1")
	private WebElement newWindowBtn;

	@FindBy(xpath = "//*[@id='content']/p[4]/button")
	private WebElement newBrowserTabBtn;

	public void getPracticeTableData() {
		assertThat(driver.getTitle(),
				is("Demo Table for practicing Selenium Automation"));

		StringBuilder builder = new StringBuilder();
		for (WebElement elm : tableData) {
			String text = elm.getText();
			if (StringUtils.isNotBlank(text)) {
				builder.append(text);
			}
		}
		CustomLogger.logInfo(builder.toString());
		assertThat("table conent shouldn't null", builder.toString(),
				containsString("Total 4 buildings"));
	}

	public void performWindowSwitching() {
		assertThat(driver.getTitle(),
				is("Demo Windows for practicing Selenium Automation"));
		waitAndClick(newWindowBtn);
		switchToBrowserTab();
		assertThat("window count to be '2'", driver.getWindowHandles().size(),
				equalTo(2));
		closeCurrentBroserTab();
		assertThat("window count to be '1'", driver.getWindowHandles().size(),
				equalTo(1));
		switchToBrowserTab();

		waitAndClick(newBrowserTabBtn);
		switchToBrowserTab();
		assertThat("window count to be '2'", driver.getWindowHandles().size(),
				equalTo(2));
		closeCurrentBroserTab();
		assertThat("window count to be '1'", driver.getWindowHandles().size(),
				equalTo(1));
		switchToBrowserTab();
	}
}
