package com.SeleniumMavenProject.Common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		// Check if test not succeed
		if (!result.isSuccess()) {
			// Check if maxtry count is reached
			if (count < maxTry) {
				// Increase the maxTry count by 1
				count++;
				// Mark test as failed
				result.setStatus(ITestResult.FAILURE);
				// Tells TestNG to re-run the test
				return true;
			} else {
				// If maxCount reached,test marked as failed
				result.setStatus(ITestResult.FAILURE);
			}
		} else {
			// If test passes, TestNG marks it as passed
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}
