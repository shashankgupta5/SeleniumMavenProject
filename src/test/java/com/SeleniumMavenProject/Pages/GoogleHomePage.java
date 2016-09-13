package com.SeleniumMavenProject.Pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.SeleniumMavenProject.Common.CustomLogger;

public class GoogleHomePage extends BasePage {
	
	@FindBy(how = How.ID, using = "lst-ib")
	private WebElement searchBox;

	@FindBy(how = How.NAME, using = "btnG")
	private WebElement searchBtn;

	@FindBy(how = How.ID, using = "resultStats")
	private WebElement resultStatus;

	@FindBy(how = How.XPATH, using = "//h3/a")
	private List<WebElement> urlList;

	public GoogleHomePage() {
		super();
	}

	public GoogleHomePage searchThis(String something) {
		isStringInTitle("Google");
		waitAndSendKeys(searchBox, something);
		waitAndClick(searchBtn);
		wait.forElementVisible(resultStatus);
		CustomLogger.logInfo("searchThis: Search for item took {"
				+ resultStatus.getText() + "}");

		return this;
	}

	public void writeUrls() {
		for (WebElement url : urlList) {
			if (!StringUtils.isBlank(url.getText())) {
				CustomLogger.logInfo("writeUrls: {" + url.getText() + "}");
			}
		}
	}
}
