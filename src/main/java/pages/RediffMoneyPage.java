package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Consumer;

public class RediffMoneyPage extends AbstractPage {

    public RediffMoneyPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public RediffMoneyPage navigateToPage() {
        navigateToUrl("https://money.rediff.com/gainers/bsc/daily/groupa");
        return this;
    }

    public RediffMoneyPage handleTable() {
        WebElement table = getElementByXpath("//table[@class='dataTable']");
        List<WebElement> columns = table.findElements(By.xpath("thead/tr/th"));
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));

        Consumer<WebElement> elementTextConsumer = element -> logger.info(element.getText());
        columns.forEach(elementTextConsumer);

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 1; j <= columns.size(); j++) {
                logger.info(rows.get(i).findElement(By.xpath("td[" + j + "]")).getText() + "\t");
            }
            logger.info("\n");
        }

        return this;
    }

    @Override
    public void verify() {
        //TODO-Implement verify
    }
}
