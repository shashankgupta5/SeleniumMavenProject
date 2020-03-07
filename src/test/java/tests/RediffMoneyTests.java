package tests;

import config.DriverManager;
import org.testng.annotations.Test;
import pages.RediffMoneyPage;

public class RediffMoneyTests extends TestRunner {

	@Test
	public void testMethod_01_printTable() {
		try {
			RediffMoneyPage rediffMoney = new RediffMoneyPage(DriverManager.getWebDriver());

			rediffMoney
					.navigateToPage()
					.handleTable()
					.verify();

		} catch (Exception e) {
			failTestIfRequired(e.getMessage());
		}
	}
}
