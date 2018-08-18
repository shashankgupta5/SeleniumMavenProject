package com.SeleniumMavenProject.tests;

import common.Retry;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.ToolsQAPage;

@Test(groups = {"toolsqa_tests"}, retryAnalyzer = Retry.class)
public class ToolsQATests extends TestRunner {

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
