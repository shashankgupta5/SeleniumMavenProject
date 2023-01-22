package pages;

import helper.HelperImpl;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage<T> extends HelperImpl {

	public AbstractPage(WebDriver driver) {
		setDriver(driver);
	}

	public abstract T navigateToPage();

	public abstract void verify();
}
