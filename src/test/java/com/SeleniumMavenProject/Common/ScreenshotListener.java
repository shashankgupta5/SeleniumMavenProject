package com.SeleniumMavenProject.Common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.SeleniumMavenProject.Config.NewDriverProvider;
import com.SeleniumMavenProject.WebDriverHelper.Shooter;

public class ScreenshotListener extends TestListenerAdapter {
	private String SCREENSHOT_DIR = System.getProperty("user.dir")
			+ File.separator + "results" + File.separator + "failure"
			+ File.separator;

	@Override
	public void onTestFailure(ITestResult result) {
		String filePath = String.format("%s%s_%s", SCREENSHOT_DIR,
				result.getMethod().getMethodName(), getTimeStamp());

		Shooter failureScreenshot = new Shooter(
				NewDriverProvider.getWebDriver());
		failureScreenshot.captureWebPage(filePath);
	}

	private String getTimeStamp() {
		Date currDate = new Date();
		String currentTime = new SimpleDateFormat("dd.MM.yyyy h.mm.ss")
				.format(currDate);
		return currentTime.replace(" ", "_");
	}
}
