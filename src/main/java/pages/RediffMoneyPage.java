package pages;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RediffMoneyPage extends AbstractPage<RediffMoneyPage> {

	private StringBuilder builder = new StringBuilder();

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

		columns.stream()
				.map(WebElement::getText)
				.forEach(each -> builder.append(each).append("\t"));

		builder.append("\n");

		for (int i = 0; i < rows.size(); i++) {
			for (int j = 1; j <= columns.size(); j++) {
				builder.append(rows.get(i).findElement(By.xpath("td[" + j + "]")).getText()).append("\t");
			}
			builder.append("\n");
		}

		logger.info("\n{}", builder.toString());
		return this;
	}

	@Override
	public void verify() {
		if (StringUtils.isBlank(builder.toString())) {
			Assert.fail("No data found in the table, hence failing");
		}
	}
}
