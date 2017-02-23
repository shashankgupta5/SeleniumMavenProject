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

	public Wait(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Configuration.getExplicitWaitTimeOut());
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

	public boolean forElementNotClickable(WebElement element) {
		ExpectedCondition<WebElement> elementVisible = ExpectedConditions.visibilityOf(element);
		Function<WebDriver, Boolean> notClickable = (WebDriver d) -> {
			try {
				WebElement e = elementVisible.apply(d);
				return !e.isEnabled();
			} catch (NoSuchElementException e) {
				return true;
			} catch (StaleElementReferenceException e) {
				return true;
			} catch (WebDriverException e) {
				return true;
			}
		};

		return wait.until(notClickable);
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
		Function<WebDriver, Boolean> notVisible = (WebDriver d) -> {
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

		return wait.until(notVisible);
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

	public boolean forWindowCountToBe(int windowCount) {
		return wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));
	}

	public boolean forNewWindowPresent() {
		Function<WebDriver, Boolean> newWindowPresent = (WebDriver d) -> {
			Object[] windows = d.getWindowHandles().toArray();
			return windows.length > 1;
		};

		return wait.until(newWindowPresent);
	}

	public boolean forCssValuePresentForElement(WebElement element, String cssProperty, String expectedValue) {
		Function<WebDriver, Boolean> cssValuePresent = (WebDriver d) -> {
			return expectedValue.equals(element.getCssValue(cssProperty));
		};

		return wait.until(cssValuePresent);
	}

	public boolean forAttributeContains(WebElement element, String attribute, String value) {
		return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	}

	public boolean forUrlContains(String text) {
		return wait.until(ExpectedConditions.urlContains(text));
	}

	public void forXSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			CustomLogger.logError(String.format("forXSeconds: {%s}", e.getMessage()));
		}
	}
}
