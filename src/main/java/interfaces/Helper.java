package interfaces;

import config.PropertyReader;
import helper.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public interface Helper {
    Logger logger = LoggerFactory.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    WebDriver getDriver();
    void setDriver(WebDriver driverToSet);

    //=====
    // Driver-related actions
    //=====

    default JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    default void executeAsyncJavascript(String javascriptToExecute, Object... arguments) {
        getJavascriptExecutor().executeAsyncScript(javascriptToExecute, arguments);
    }

    default void executeAsyncJavascript(String javascriptToExecute) {
        getJavascriptExecutor().executeAsyncScript(javascriptToExecute);
    }

    default void executeJavascript(String javascriptToExecute, Object... arguments) {
        getJavascriptExecutor().executeScript(javascriptToExecute, arguments);
    }

    default void executeJavascript(String javascriptToExecute) {
        getJavascriptExecutor().executeScript(javascriptToExecute);
    }

    default void focus(String cssSelector) {
        executeJavascript("$('" + cssSelector + "').focus()");
    }

    default void focus(WebElement element) {
        executeJavascript("$(arguments[0]).focus()", element);
    }

    default void scrollDown(int amountInPixels) {
        executeJavascript("window.scrollBy(0, arguments[0]);", amountInPixels);
    }

    default void scrollToBottom() {
        executeJavascript("window.scrollTo(0, document.body.scrollHeight);");
    }

    default void scrollToElement(WebElement element) {
        executeJavascript("arguments[0].scrollIntoView(true);", element);
    }

    default void scrollToTop() {
        executeJavascript("window.scrollTo(0, 0);");
    }

    default void scrollBy(int x, int y) {
        executeJavascript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    default Actions getActionBuilder() {
        return new Actions(getDriver());
    }

    default void navigateToUrl(String url) {
        getDriver().get(url);
    }

    default void refreshWebPage() {
        getDriver().navigate().refresh();
    }

    default void closeCurrentBroserTab() {
        getDriver().close();
    }

    default void switchToBrowserTab() {
        try {
            List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(tabs.get(tabs.size() - 1));
            logger.info("Currently at {} tab", getDriver().getTitle());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    //=====
    // Alert-related actions
    //=====

    default void acceptAlert() {
        while (isAlertPresent()) {
            Alert alert = getDriver().switchTo().alert();
            String alertText = alert.getText();
            alert.accept();

            logger.info("Fund and Closed alert with text {}", alertText);
        }
    }

    default boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //=====
    // Element-related actions
    //=====

    default void clickElement(WebElement element) {
        element.click();
        logger.debug("clicked {} with raw Selenium", element.toString());
    }

    default void clickWithJavascript(String xpath) {
        WebElement element = getElementByXpath(xpath);
        clickWithJavascript(element);
    }

    default void clickWithJavascript(WebElement element) {
        executeJavascript("arguments[0].click();", element);
        logger.debug("clicked {} with Javascript", element.toString());
    }

    default WebElement getElementByAttribute(String prependOrTag, String attribute, String attributeValue) {
        return getElementByXpath(String.format("//%s[contains(@%s, '%s')]", prependOrTag, attribute, attributeValue));
    }

    default WebElement getElementByXpath(String xpath) {
        return getElementBy(By.xpath(xpath));
    }

    default WebElement getElementBy(By byLocator) {
        WebElement element = getDriver().findElement(byLocator);
        return element;
    }

    default List<WebElement> getElementsByXpath(String xpath) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        return elements;
    }

    default WebElement getElementByText(String text) {
        return getElementByXpath(String.format("//*[text()='%s']", text));
    }

    default WebElement getElement(List<WebElement> elements) {
        WebElement foundElement = null;

        for (WebElement element : elements) {
            if (element != null && element.isDisplayed()) {
                foundElement = element;
            }
        }

        if (foundElement == null) {
            logger.error("WebElement not found in the list");
        }
        return foundElement;
    }

    default WebElement getElementByValue(List<WebElement> elements, String attribute, String value) {
        WebElement foundElement = null;

        for (WebElement element : elements) {
            if (element.getAttribute(attribute).equals(value)) {
                foundElement = element;
            }
        }

        if (foundElement == null) {
            logger.error(
                    "WebElement with attribute {} and value {} is not found in the list",
                    attribute.toUpperCase(), value);
        }
        return foundElement;
    }

    default WebElement getElementByText(List<WebElement> elements, String text) {
        WebElement foundElement = null;

        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(text)) {
                foundElement = element;
            }
        }

        if (foundElement == null) {
            logger.error("WebElement with text {} is not found in the list", text);
        }
        return foundElement;
    }

    default boolean isElementVisible(WebElement element) {
        changeImplicitWait(2000);
        try {
            return element.isDisplayed();
        } catch (WebDriverException exception) {
            if (exception instanceof NoSuchElementException) {
                logger.debug("WebElement {} NOT FOUND", element);
            } else if (exception instanceof ElementNotVisibleException) {
                logger.debug("WebElement {} NOT VISIBLE", element);
            }
            return false;
        } finally {
            restoreExistingImplicitWait();
        }
    }

    default boolean isElementVisible(String xpath) {
        changeImplicitWait(2000);
        try {
            WebElement element = getElementByXpath(xpath);
            return element.isDisplayed();
        } catch (WebDriverException exception) {
            if (exception instanceof NoSuchElementException) {
                logger.debug("WebElement NOT FOUND with selector of {}", xpath);
            } else if (exception instanceof ElementNotVisibleException) {
                logger.debug("WebElement NOT VISIBLE with selector of {}", xpath);
            }
            return false;
        } finally {
            restoreExistingImplicitWait();
        }
    }

    //=====
    // Page-related actions
    //=====

    default String getPageSource() {
        return getDriver().getPageSource();
    }

    default void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    default void switchToIframeIfPresent() {
        switchToIframeIfPresent(0);
    }

    default void switchToIframeIfPresent(int frameNumber) {
        getDriver().switchTo().frame(frameNumber);
    }

    //=====
    // Wait-related actions
    //=====

    default void waitFor(int milliseconds) {
        Wait.waitForXMilliseconds(milliseconds);
    }

    default WebElement waitForElementToBeVisible(String xpath) {
        return Wait.waitForElementToBeVisible(getDriver(), By.xpath(xpath));
    }

    default WebElement waitForElementToBeVisible(WebElement element) {
        return Wait.waitForElementToBeVisible(getDriver(), element);
    }

    default void waitForElementToBeHidden(String xpath) {
        Wait.waitForElementToBeHidden(getDriver(), getElementByXpath(xpath));
    }

    default void waitForElementToBeHidden(WebElement element) {
        Wait.waitForElementToBeHidden(getDriver(), element);
    }

    default boolean waitForElementTextToBe(WebElement element, String text) {
        return Wait.waitForElementTextToBe(getDriver(), element, text);
    }

    default void waitAndClickWithJavascript(WebElement element) {
        waitForElementToBeVisible(element);
        clickWithJavascript(element);
    }

    default void waitAndClick(WebElement element) {
        waitForElementToBeVisible(element);
        clickElement(element);
    }

    default void waitAndSendKeys(WebElement element, String keys) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(keys);
    }

    default void waitAndSendKeysLikeHuman(WebElement element, String keys) {
        waitForElementToBeVisible(element);
        element.clear();

        char keysArray[] = keys.toCharArray();
        for (int i = 0; i < keys.length(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            element.sendKeys(keysArray[i] + "");
        }
    }

    default void waitUntilPageLoaded() {
        Wait.waitUntilPageLoaded(getDriver());
    }

    default void waitForFrameAvailableAndSwitch(int frameNumber) {
        Wait.waitForFrameAvailableAndSwitch(getDriver(), frameNumber);
    }

    default void changeImplicitWait(int timeoutInMillis) {
        getDriver().manage().timeouts().implicitlyWait(timeoutInMillis, TimeUnit.MILLISECONDS);
    }

    default void restoreExistingImplicitWait() {
        changeImplicitWait(PropertyReader.getExplicitWaitTimeOut() * 1000);
    }

    default int getWindowCount() {
        return getDriver().getWindowHandles().size();
    }

    default boolean hasQuit() {
        try {
            return getDriver().toString().contains("null");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return true;
        }
    }
}
