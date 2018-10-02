package tests;

import org.testng.annotations.Test;
import pages.RediffMoneyPage;

public class RediffMoneyTests extends TestRunner {

    @Test
    public void testMethod_01_printTable() throws Exception {
        try {
            RediffMoneyPage rediffMoney = new RediffMoneyPage(getWebDriver());
            rediffMoney.navigateToUrl("https://money.rediff.com/gainers/bsc/daily/groupa");
            rediffMoney.handleTable();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
