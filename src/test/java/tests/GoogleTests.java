package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.GooglePage;

@Test
public class GoogleTests extends TestRunner {

    @Description("Test method to search on google")
    public void testMethod_01_searchSomething() {
        try {
            GooglePage googlePage = new GooglePage(getWebDriver());

            googlePage
                    .navigateToPage()
                    .searchSomething("Cheese")
                    .verify();

        } catch (Exception e) {
            failTestIfRequired(e.getMessage());
        }
    }
}
