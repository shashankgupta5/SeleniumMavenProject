package tests;

import org.testng.annotations.Test;
import pages.RediffMoneyPage;

public class RediffMoneyTests extends TestRunner {

    @Test
    public void testMethod_01_printTable() throws Exception {
        try {
            RediffMoneyPage rediffMoney = new RediffMoneyPage(getWebDriver());

            rediffMoney
                    .navigateToPage()
                    .handleTable()
                    .verify();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
