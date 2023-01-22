package tests;

import config.DriverManager;
import listeners.MethodListener;
import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(MethodListener.class)
public class TestRunner {

	@BeforeMethod
	@Parameters(value = {"browser", "platform"})
	public void beforeMethod(@Optional String browser, @Optional String platform) {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterSuite
	public void afterSuite() {
		DriverManager.quitDriver();
	}

	protected void failTestIfRequired(String message) {
		throw new TestNGException(message);
	}
}
