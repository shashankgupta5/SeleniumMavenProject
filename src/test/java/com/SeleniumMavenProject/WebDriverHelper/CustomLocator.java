package com.SeleniumMavenProject.WebDriverHelper;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.SeleniumMavenProject.Common.CustomLogger;

public class CustomLocator {
	public WebElement getElement(List<WebElement> elements) {
		WebElement foundElement = null;
		CustomLogger.logInfo(String.format(
				"getElement: List contains {%s} elements", elements.size()));

		for (WebElement element : elements) {
			if (element != null && element.isDisplayed()) {
				foundElement = element;
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(
					"getElement: Element not found in the list");
		}
		return foundElement;
	}

	public WebElement getElementByValue(List<WebElement> elements,
			String attribute, String value) {
		WebElement foundElement = null;
		CustomLogger.logInfo(
				String.format("getElementByValue: List contains {%s} elements",
						elements.size()));

		for (WebElement element : elements) {
			if (element.getAttribute(attribute).equals(value)) {
				foundElement = element;
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(String.format(
					"getElementByValue: Element with attribute {%s} and value {%s} is not found in the list",
					attribute.toUpperCase(), value));
		}
		return foundElement;
	}

	public WebElement getElementByText(List<WebElement> elements,
			String value) {
		WebElement foundElement = null;
		CustomLogger.logInfo(
				String.format("getElementByText: List contains {%s} elements",
						elements.size()));

		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(value)) {
				foundElement = element;
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(String.format(
					"getElementByText: Element with text {%s} is not found in the list",
					value));
		}
		return foundElement;
	}
}
