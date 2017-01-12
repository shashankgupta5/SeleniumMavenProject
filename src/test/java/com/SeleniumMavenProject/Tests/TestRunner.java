package com.SeleniumMavenProject.Tests;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.Method;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.SeleniumMavenProject.Common.CustomLogger;
import com.SeleniumMavenProject.Common.ScreenshotListener;
import com.SeleniumMavenProject.Config.Configuration;
import com.SeleniumMavenProject.Config.NewDriverProvider;
import com.SeleniumMavenProject.WebDriverHelper.VideoRecorder;

import ru.yandex.qatools.allure.annotations.Step;

@Listeners(ScreenshotListener.class)
public class TestRunner {

	private static WebDriver driver;
	private VideoRecorder videoRecorder;

	private final String RESULTS_DIR = System.getProperty("user.dir")
			+ File.separator + "results" + File.separator;
	private final String VIDEOS_DIR = RESULTS_DIR + "videos" + File.separator;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		CustomLogger.initLogger();
		Configuration.initConfigFile();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() throws Exception {
		startBrowser();
		setBrowserSize();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method m) {
		startRecording(VIDEOS_DIR, m.getName());
		CustomLogger.startTestCase(m);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method m) {
		CustomLogger.endTestCase(m);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() throws Exception {
		stopRecording();
		stopBrowser();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		Configuration.endConfigFile();
		CustomLogger.endLogger();
	}

	@Step("Start Browser")
	private void startBrowser() {
		driver = NewDriverProvider.createWebDriverInstance();
	}

	@Step("Set Browser Size")
	private void setBrowserSize() {
		Dimension browserSize = Configuration.getBrowserSize();

		if (browserSize != null) {
			driver.manage().window().setSize(browserSize);
		} else {
			driver.manage().window().maximize();
		}
	}

	@Step("Navigate to \"{0}\"")
	void navigateToUrl(String url) {
		driver.get(url);
	}

	@Step("Close Browser")
	private void stopBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	private void startRecording(String srcDir, String fileName) {
		try {
			java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
					.getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;

			Rectangle captureSize = new Rectangle(0, 0, width, height);

			GraphicsConfiguration gc = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();

			videoRecorder = new VideoRecorder(gc, captureSize,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey,
							MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24,
							FrameRateKey, Rational.valueOf(15), QualityKey,
							1.0f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
							"black", FrameRateKey, Rational.valueOf(30)),
					null, new File(srcDir), fileName);
			videoRecorder.start();
		} catch (Exception e) {
			CustomLogger.logError("startRecording: " + e.getMessage());
		}
	}

	private void stopRecording() {
		try {
			videoRecorder.stop();
			if (videoRecorder != null) {
				videoRecorder = null;
			}
		} catch (Exception e) {
			CustomLogger.logError("stopRecording: " + e.getMessage());
		}
	}
}
