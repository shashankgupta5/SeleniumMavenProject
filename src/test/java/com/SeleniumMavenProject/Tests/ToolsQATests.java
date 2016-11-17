package com.SeleniumMavenProject.Tests;

import org.testng.annotations.Test;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Pages.ToolsQAPage;

@Test(groups = {"toolsqa_tests"})
public class ToolsQATests extends TestRunner {

	public void testMethod_01_getTableData() throws Exception {
		try {
			navigateToUrl("http://toolsqa.com/automation-practice-table/");

			new ToolsQAPage().getPracticeTableData();
		} catch (Exception e) {
			CustomLogger
					.logError("testMethod_01_getTableData: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void testMethod_02_practiceSwitchingWindows() throws Exception {
		try {
			navigateToUrl(
					"http://toolsqa.com/automation-practice-switch-windows/");

			new ToolsQAPage().performWindowSwitching();
		} catch (Exception e) {
			CustomLogger.logError("testMethod_02_practiceSwitchingWindows: "
					+ e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
