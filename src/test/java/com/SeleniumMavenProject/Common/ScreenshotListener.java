package com.SeleniumMavenProject.Common;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		String filePath = String.format("%s%s_%s.png", SCREENSHOT_DIR,
				result.getMethod().getMethodName(), getTimeStamp());

		Shooter failureScreenshot = new Shooter(
				NewDriverProvider.getWebDriver());
		failureScreenshot.captureWebPage(filePath);
	}

	private String getTimeStamp() {
		LocalDateTime time = LocalDateTime.now();
		return time.format(DateTimeFormatter.ofPattern("dd.MMM.uuu_HH.mm.ss"));
	}
}
