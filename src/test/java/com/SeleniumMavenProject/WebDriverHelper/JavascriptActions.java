package com.SeleniumMavenProject.WebDriverHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SeleniumMavenProject.Common.CustomLogger;

public class JavascriptActions {
	private JavascriptExecutor js;
	private WebDriver driver;

	public JavascriptActions(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		this.driver = driver;
	}

	public void focus(String cssSelector) {
		js.executeScript("$('" + cssSelector + "').focus()");
	}

	public void focus(WebElement element) {
		js.executeScript("$(arguments[0]).focus()", element);
	}

	public void scrollToBottom(WebDriver driver) {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollBy(int x, int y) {
		js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
	}

	public void scrollToElement(WebElement element) {
		try {
			js.executeScript(
					"var x = $(arguments[0]); "
							+ "window.scroll(0,parseInt(x.offset().top - 100));",
					element);
		} catch (Exception e) {
			CustomLogger.logInfo("scrollToElement: " + e.getMessage());
		}
	}

	public void scrollElementIntoViewPort(WebElement element) {
		if (!isElementInViewPort(element)) {
			scrollToElement(element);
		}
	}

	public boolean isElementInViewPort(WebElement element) {
		return (Boolean) js.executeScript(
				"return ($(window).scrollTop() + 60 < $(arguments[0]).offset().top) && ($(window).scrollTop() "
						+ "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + 60)",
				element);
	}

	public String execute(String script) {
		Object value = null;
		try {
			waitForJavascriptToLoad();
			value = js.executeScript("return " + script);
		} catch (Exception e) {
			CustomLogger.logError("execute: " + e.getMessage());
		}
		return value.toString();
	}

	public String getWindowErrors() {
		return js.executeScript("return window.errors || ''").toString();
	}

	private boolean waitForJavascriptToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js
							.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for JS to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
}
