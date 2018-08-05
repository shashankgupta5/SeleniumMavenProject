package helper;

import config.PropertyReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
public class Wait {
    private static FluentWait<WebDriver> wait;

    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebDriver waitForFrameAvailableAndSwitch(WebDriver driver, int frameLocator) {
        return getWait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public static void waitForElementToBeHidden(WebDriver driver, WebElement element) {
        getWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return !element.isDisplayed();
                } catch (WebDriverException e) {
                    return true;
                }
            }

            @Override
            public String toString() {
                return "waitForElementToBeHidden: invisibility of " + element;
            }
        });
    }

    public static boolean waitForNumberOfElementsToBe(WebDriver driver, List<WebElement> elements, int number) {
        return getWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (elements.size() == number);
            }

            @Override
            public String toString() {
                return String.format("waitForNumberOfElementsToBe: number to be \"%s\". Current number: \"%s\"", number,
                        elements.size());
            }
        });
    }

    public static boolean waitForElementTextToBe(WebDriver driver, WebElement element, String text) {
        return getWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return element.getText().matches(text);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("waitForElementTextToBe: '%s' to be present in element %s", text, element);
            }
        });
    }

    public static void waitUntilPageLoaded(WebDriver driver) {
        Wait.waitForJSEventToBeTrue(driver, "return (document.readyState != 'loading')");
    }

    public static boolean waitForJSEventToBeTrue(WebDriver driver, String javascript) {
        return getWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (Boolean) ((JavascriptExecutor) driver).executeScript(javascript);
                } catch (TimeoutException e) {
                    logger.debug("Timed out trying to execute Javascript [{}]", javascript);
                    return false;
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("Javascript event to be true via %s", javascript);
            }
        });
    }

    public static void waitForXMilliseconds(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException ignored) {
        }
    }

    public static FluentWait<WebDriver> getWait(WebDriver driver) {
        if (wait == null) {
            return new FluentWait<>(driver)
                    .withTimeout(Duration.of(PropertyReader.getExplicitWaitTimeOut(), ChronoUnit.SECONDS))
                    .pollingEvery(Duration.of(200, ChronoUnit.MILLIS))
                    .ignoring(NoSuchElementException.class);
        }
        return wait;
    }
}
