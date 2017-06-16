package com.SeleniumMavenProject.WebDriverHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.google.common.base.Function;

public class JavascriptActions {
	private JavascriptExecutor js;
	private WebDriver driver;

	public JavascriptActions(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		this.driver = driver;
	}

	public void click(WebElement element) {
		js.executeScript("$(arguments[0])[0].click()", element);
	}

	public void focus(String cssSelector) {
		js.executeScript("$('" + cssSelector + "').focus()");
	}

	public void focus(WebElement element) {
		js.executeScript("$(arguments[0]).focus()", element);
	}

	public void scrollToTop() {
		js.executeScript("window.scrollTo(0, 0)");
	}

	public void scrollToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollBy(int x, int y) {
		js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
	}

	public void scrollToElement(WebElement element) {
		try {
			js.executeScript("var x = $(arguments[0]); " + "window.scroll(0,parseInt(x.offset().top - 100));", element);
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

	public Object execute(String script, WebElement element) {
		return js.executeScript(script, element);
	}

	public Object executeScript(String script) {
		waitForJavascriptToLoad();
		return js.executeScript("return " + script);
	}

	public Object executeASyncScript(String script) {
		waitForJavascriptToLoad();
		return js.executeAsyncScript(script);
	}

	public String getWindowErrors() {
		return js.executeScript("return window.errors || ''").toString();
	}

	private boolean waitForJavascriptToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// wait for jQuery to load
		Function<WebDriver, Boolean> jQueryLoad = (WebDriver b) -> {
			try {
				return ((Long) js.executeScript("return jQuery.active") == 0);
			} catch (Exception e) {
				return true;
			}
		};

		// wait for JS to load
		Function<WebDriver, Boolean> jsLoad = (WebDriver b) -> {
			return js.executeScript("return document.readyState").toString().equals("complete");
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
}
