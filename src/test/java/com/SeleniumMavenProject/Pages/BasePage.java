package com.SeleniumMavenProject.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Config.Configuration;
import com.SeleniumMavenProject.Config.NewDriverProvider;
import com.SeleniumMavenProject.WebDriverHelper.CustomLocator;
import com.SeleniumMavenProject.WebDriverHelper.JavascriptActions;
import com.SeleniumMavenProject.WebDriverHelper.Shooter;
import com.SeleniumMavenProject.WebDriverHelper.Wait;

public class BasePage {
	protected final WebDriver driver = NewDriverProvider.getWebDriver();
	protected Wait wait;
	protected Shooter shooter;
	protected CustomLocator locator;
	protected JavascriptActions jsActions;

	protected int DEFAULT_WAIT = Configuration.getWait();

	public BasePage() {
		wait = new Wait(driver);
		shooter = new Shooter(driver);
		jsActions = new JavascriptActions(driver);

		PageFactory.initElements(driver, this);
	}

	public void refreshWebPage() {
		driver.navigate().refresh();
	}

	public void closeCurrentBroserTab() {
		driver.close();
	}

	protected void switchToBrowserTab() {
		try {
			List<String> tabs = new ArrayList<String>(
					driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			CustomLogger.logInfo(
					String.format("switchToBrowserTab: Currently at {%s} tab",
							driver.getTitle()));
		} catch (Exception e) {
			CustomLogger.logError(
					String.format("switchToBrowserTab: {%s}", e.getMessage()));
		}
	}

	protected void waitAndClick(WebElement element) {
		wait.forElementClickable(element).click();
	}

	protected void waitAndSendKeys(WebElement element, String keys) {
		wait.forElementVisible(element);
		element.clear();
		element.sendKeys(keys);
	}

	protected boolean isStringInTitle(String givenTitle) {
		String currentTitle = driver.getTitle();
		if (!currentTitle.contains(givenTitle)) {
			CustomLogger.logInfo(String.format(
					"isStringInURL: {%s} current url doesn't contains {%s}",
					currentTitle, givenTitle));
			return false;
		}
		return true;
	}

	protected boolean isStringInURL(String givenString) {
		String currentURL = driver.getCurrentUrl();
		if (!currentURL.contains(givenString)) {
			CustomLogger.logInfo(String.format(
					"isStringInURL: {%s} current url doesn't contains {%s}",
					currentURL, givenString));
			return false;
		}
		return true;
	}

	public String getRandomNumber(int length) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('0' + rnd.nextInt(10)));
		}
		return sb.toString();
	}

	public String getRandomString(int length) {
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'l', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z'};
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(alphabet[rnd.nextInt(alphabet.length)]);
		}
		return sb.toString();
	}

	public boolean hasQuit() {
		try {
			return driver.toString().contains("null");
		} catch (Exception e) {
			CustomLogger
					.logError(String.format("hasQuit: {%s}", e.getMessage()));
			return true;
		}
	}

	public void acceptAlert() {
		while (isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			alert.accept();

			CustomLogger.logError(String.format(
					"waitForAlertAndAccept: Detected and Closed alert with text {%s}",
					alertText));
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}
