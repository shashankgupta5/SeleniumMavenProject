package com.SeleniumMavenProject.WebDriverHelper;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Config.Configuration;
import com.google.common.base.Function;

public class Wait {

	private WebDriverWait wait;
	private final int DEFAULT_TIMEOUT_FOR_EXPLICIT_WAIT = Configuration
			.getDefaultTimeOut();

	public Wait(WebDriver driver) {
		this.wait = new WebDriverWait(driver,
				DEFAULT_TIMEOUT_FOR_EXPLICIT_WAIT);
	}

	public WebElement forElementPresent(By by) {
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			CustomLogger.logError("forElementPresent: " + e.getMessage());
		}
		return null;
	}

	public WebElement forElementClickable(WebElement element) {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			CustomLogger.logError("forElementClickable: " + e.getMessage());
		}
		return null;
	}

	public WebElement forElementVisible(WebElement element) {
		try {
			return wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			CustomLogger.logError("forElementVisible: " + e.getMessage());
		}
		return null;
	}

	public boolean forElementNotVisible(WebElement element) {
		Function<WebDriver, Boolean> elementNotVisible = (WebDriver d) -> {
			try {
				return !element.isDisplayed();
			} catch (NoSuchElementException e) {
				return true;
			} catch (StaleElementReferenceException e) {
				return true;
			} catch (WebDriverException e) {
				return true;
			}
		};

		return wait.until(elementNotVisible);
	}

	public Alert forAlertPresent() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean forTitleIsNotEmpty() {
		Function<WebDriver, Boolean> titleNotEmpty = (WebDriver d) -> {
			return StringUtils.isNotBlank(d.getTitle());
		};

		return wait.until(titleNotEmpty);
	}

	public boolean forNewWindowPresent() {
		Function<WebDriver, Boolean> newWindowPresent = (WebDriver d) -> {
			Object[] windows = d.getWindowHandles().toArray();
			return windows.length > 1;
		};

		return wait.until(newWindowPresent);
	}

	public boolean forCssValuePresentForElement(final WebElement element,
			final String cssProperty, final String expectedValue) {
		return wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return expectedValue.equals(element.getCssValue(cssProperty));
			}
		});
	}

	public boolean forAttributeContains(WebElement element, String attribute,
			String value) {
		return wait.until(ExpectedConditions.attributeContains(element,
				attribute, value));
	}

	public boolean forUrlContains(String text) {
		return wait.until(ExpectedConditions.urlContains(text));
	}

	public void forXSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			CustomLogger.logError(
					String.format("forXSeconds: {%s}", e.getMessage()));
		}
	}
}
