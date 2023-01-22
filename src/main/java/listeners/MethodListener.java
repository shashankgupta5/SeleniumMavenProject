package listeners;

import common.Constants;
import config.DriverFactory;
import config.DriverManager;
import java.net.MalformedURLException;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			String browserName = getDefaultsForDriverIfMissing(method, "browser");
			String platformName = getDefaultsForDriverIfMissing(method, "platform");
			try {
				DriverManager
						.setWebDriver(DriverFactory.createWebDriverInstance(browserName, platformName));
			} catch (MalformedURLException e) {
				Assert.fail(e.getMessage());
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			DriverManager.quitDriver();
		}
	}

	private String getDefaultsForDriverIfMissing(IInvokedMethod method, String propertyName) {
		String value = method.getTestMethod().getXmlTest().getLocalParameters().get(propertyName);
		if (StringUtils.isNotBlank(value)) {
			return value;
		}

		if (StringUtils.equals(propertyName, "platform")) {
			return Constants.getOSName().matches("(.*)mac(.*)")
					? "mac"
					: "windows";
		}

		// Defaulting browser value to chrome as no browser was passed,
		// plus criteria to decide on runtime, like OS above
		return "chrome";
	}
}
