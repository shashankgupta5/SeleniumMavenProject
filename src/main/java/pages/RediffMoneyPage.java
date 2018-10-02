package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RediffMoneyPage extends BasePage {

    public RediffMoneyPage(WebDriver driver) {
        super(driver);
    }

    public void handleTable() {
        WebElement table = getElementByXpath("//table[@class='dataTable']");
        List<WebElement> columns = table.findElements(By.xpath("thead/tr/th"));
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));

        System.out.println();

        for (WebElement column : columns) {
            System.out.print(column.getText() + "\t");
        }

        System.out.println();

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 1; j <= columns.size(); j++) {
                System.out.print(rows.get(i).findElement(By.xpath("td[" + j + "]")).getText() + "\t");
            }
            System.out.println();
        }
    }
}
