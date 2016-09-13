package com.SeleniumMavenProject.Pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SeleniumMavenProject.Common.CustomLogger;

public class ToolsQAPage extends BasePage {

	@FindBy(xpath = "//*[@id='content']/table/thead/tr/following::tr")
	private List<WebElement> tableData;

	@FindBy(className = "ui-tabs-anchor")
	private List<WebElement> iFrame_Tabs;

	@FindBy(id = "button1")
	private WebElement newWindowBtn;

	@FindBy(xpath = "//*[@id='content']/p[4]/button")
	private WebElement newBrowserTabBtn;

	public void practiceTable() {
		for (WebElement elm : tableData) {
			String text = elm.getText();
			if (StringUtils.isNotBlank(text)) {
				CustomLogger.logInfo(text);
			}
		}
	}

	public void performWindowSwitching() {
		waitAndClick(newWindowBtn);
		switchToBrowserTab();
		closeCurrentBroserTab();
		switchToBrowserTab();

		waitAndClick(newBrowserTabBtn);
		switchToBrowserTab();
		closeCurrentBroserTab();
		switchToBrowserTab();
	}
}
