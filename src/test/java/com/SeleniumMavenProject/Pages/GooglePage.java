package com.SeleniumMavenProject.Pages;

import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.SeleniumMavenProject.Common.CustomLogger;

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
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		wait.forElementVisible(status);
		String t = status.getText();
		CustomLogger.logInfo("searchSomething: " + t);
		assertThat("searchSomething: serach text to be not blank", true, Is.is(StringUtils.isNotBlank(t)));
	}
}
