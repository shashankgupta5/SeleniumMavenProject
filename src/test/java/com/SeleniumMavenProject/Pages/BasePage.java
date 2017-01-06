package com.SeleniumMavenProject.Pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Config.NewDriverProvider;
import com.SeleniumMavenProject.WebDriverHelper.CustomLocator;
import com.SeleniumMavenProject.WebDriverHelper.JavascriptActions;
import com.SeleniumMavenProject.WebDriverHelper.Shooter;
import com.SeleniumMavenProject.WebDriverHelper.Wait;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class BasePage {
	protected final WebDriver driver = NewDriverProvider.getWebDriver();
	protected Wait wait;
	protected Shooter shooter;
	protected CustomLocator locator;
	protected JavascriptActions jsActions;

	protected final String SCREENSHOTS_DIR = System.getProperty("user.dir")
			+ File.separator + "results" + File.separator + "screenshots"
			+ File.separator;

	public BasePage() {
		wait = new Wait(driver);
		shooter = new Shooter(driver);
		jsActions = new JavascriptActions(driver);

		PageFactory.initElements(driver, this);
	}

	@Step("Refresh Web Page")
	public void refreshWebPage() {
		driver.navigate().refresh();
	}

	@Step("Close Current Tab")
	public void closeCurrentBroserTab() {
		driver.close();
	}

	@Step("Switch To Browser Tab")
	protected void switchToBrowserTab() {
		try {
			List<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			CustomLogger.logInfo(
					String.format("switchToBrowserTab: Currently at {%s} tab",
							driver.getTitle()));
		} catch (Exception e) {
			CustomLogger.logError(
					String.format("switchToBrowserTab: {%s}", e.getMessage()));
		}
	}

	@Attachment(value = "{0}", type = "image/png")
	public byte[] saveScreenshot(String attachName) throws IOException {
		return shooter.captureWebPageAsByteArray();
	}

	@Attachment(value = "{0}", type = "image/png")
	public byte[] saveElementScreenshot(WebElement element, String attachName)
			throws IOException {
		return shooter.captureWebElementAsByteArray(element);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public String getRandomNumber(int length) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append((char) ('0' + rnd.nextInt(10)));
		}
		return sb.toString();
	}

	@Attachment(value = "{0}", type = "text/plain")
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

	@Step("Accept Alert")
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

	@Step("Wait And Click")
	protected void waitAndClick(WebElement element) {
		wait.forElementClickable(element).click();
	}

	@Step("Wait And Send Keys")
	protected void waitAndSendKeys(WebElement element, String keys) {
		wait.forElementVisible(element);
		element.clear();
		element.sendKeys(keys);
	}

	@Step("Is String In Title")
	protected boolean isStringInTitle(String givenTitle) {
		String currentTitle = driver.getTitle();
		if (!currentTitle.contains(givenTitle)) {
			CustomLogger.logInfo(String.format(
					"isStringInTitle: {%s} current title doesn't contains {%s}",
					currentTitle, givenTitle));
			return false;
		}
		return true;
	}

	@Step("Is String In URL")
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

	protected int getWindowCount() {
		return driver.getWindowHandles().size();
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
