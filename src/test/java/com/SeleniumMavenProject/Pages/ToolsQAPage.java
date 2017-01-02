package com.SeleniumMavenProject.Pages;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SeleniumMavenProject.Common.CustomLogger;

import ru.yandex.qatools.allure.annotations.Step;

public class ToolsQAPage extends BasePage {

	@FindBy(xpath = "//*[@id='content']/table/thead/tr/following::tr")
	private List<WebElement> tableData;

	@FindBy(id = "button1")
	private WebElement newWindowBtn;

	@FindBy(xpath = "//*[@id='content']/p[4]/button")
	private WebElement newBrowserTabBtn;

	@FindBy(tagName = "button")
	private List<WebElement> btns;

	@FindBy(id = "navbar-container")
	private WebElement navBar;

	@FindBy(linkText = "details")
	private List<WebElement> details;

	@FindBy(xpath = "//*[@class='cancel']")
	private WebElement cancelBtn;

	public ToolsQAPage() {
		super();
	}

	@Step("Started getting practice table data")
	public void getPracticeTableData() throws IOException {
		assertThat(driver.getTitle(),
				is("Demo Table for practicing Selenium Automation"));

		StringBuilder builder = new StringBuilder();
		tableData.forEach((e) -> {
			String text = e.getText();
			if (StringUtils.isNotBlank(text)) {
				builder.append(text);
			}
		});

		CustomLogger.logInfo(builder.toString());
		assertThat("getPracticeTableData: table conent shouldn't be null",
				builder.toString(), containsString("Total 4 buildings"));
	}

	@Step("Started performing window switching")
	public void performWindowSwitching() {
		assertThat(driver.getTitle(),
				is("Demo Windows for practicing Selenium Automation"));
		waitAndClick(newWindowBtn);
		switchToBrowserTab();
		assertThat("performWindowSwitching: window count to be '2'",
				getWindowCount(), is(2));
		closeCurrentBroserTab();
		assertThat("performWindowSwitching: window count to be '1'",
				getWindowCount(), is(1));
		switchToBrowserTab();

		waitAndClick(newBrowserTabBtn);
		switchToBrowserTab();
		assertThat("performWindowSwitching: window count to be '2'",
				getWindowCount(), is(2));
		closeCurrentBroserTab();
		assertThat("performWindowSwitching: window count to be '1'",
				getWindowCount(), is(1));
		switchToBrowserTab();
	}
}
