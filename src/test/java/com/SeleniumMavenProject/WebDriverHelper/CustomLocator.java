package com.SeleniumMavenProject.WebDriverHelper;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.SeleniumMavenProject.Common.CustomLogger;

public class CustomLocator {
	private WebElement foundElement = null;

	public WebElement getElement(List<WebElement> elements) {
		CustomLogger.logInfo(String.format(
				"getElement: List contains {%s} elements", elements.size()));

		elements.forEach((e) -> {
			if (e != null && e.isDisplayed()) {
				foundElement = e;
			}
		});

		if (foundElement == null) {
			throw new NoSuchElementException(
					"getElement: Element not found in the list");
		}
		return foundElement;
	}

	public WebElement getElementByValue(List<WebElement> elements,
			String attribute, String value) {
		CustomLogger.logInfo(
				String.format("getElementByValue: List contains {%s} elements",
						elements.size()));

		elements.forEach((e) -> {
			if (e.getAttribute(attribute).equals(value)) {
				foundElement = e;
			}
		});

		if (foundElement == null) {
			throw new NoSuchElementException(String.format(
					"getElementByValue: Element with attribute {%s} and value {%s} is not found in the list",
					attribute.toUpperCase(), value));
		}
		return foundElement;
	}

	public WebElement getElementByText(List<WebElement> elements, String text) {
		CustomLogger.logInfo(
				String.format("getElementByText: List contains {%s} elements",
						elements.size()));

		elements.forEach((e) -> {
			if (e.getText().equalsIgnoreCase(text)) {
				foundElement = e;
			}
		});

		if (foundElement == null) {
			throw new NoSuchElementException(String.format(
					"getElementByText: Element with text {%s} is not found in the list",
					text));
		}
		return foundElement;
	}
}
