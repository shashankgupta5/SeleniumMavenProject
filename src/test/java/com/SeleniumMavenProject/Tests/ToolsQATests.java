package com.SeleniumMavenProject.tests;

import common.Retry;
import org.testng.annotations.Test;
import pages.ToolsQAPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Title("ToolsQATests")
@Description("Tests for ToolsQA Website")
@Test(groups = {"toolsqa_tests"}, retryAnalyzer = Retry.class)
public class ToolsQATests extends TestRunner {

    @Title("testMethod_01_getTableData")
    @Description("Test method to get table data")
    public void testMethod_01_getTableData() throws Exception {
        try {
            ToolsQAPage page = new ToolsQAPage(getWebDriver());
            page.navigateToUrl("http://toolsqa.com/automation-practice-table/");
            page.getPracticeTableData();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Title("testMethod_02_practiceSwitchingWindows")
    @Description("Test method to perfrom window switching")
    public void testMethod_02_practiceSwitchingWindows() throws Exception {
        try {
            ToolsQAPage page = new ToolsQAPage(getWebDriver());
            page.navigateToUrl("http://toolsqa.com/automation-practice-switch-windows/");
            page.performWindowSwitching();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
