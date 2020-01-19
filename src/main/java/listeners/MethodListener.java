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
            String browserName = getDefaultBrowserIfMissing(method);
            String platformName = getDefaultForPlatformIfMissing(method);
            try {
                DriverManager.setWebDriver(DriverFactory.createWebDriverInstance(browserName, platformName));
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

    private String getDefaultBrowserIfMissing(IInvokedMethod method) {
        String value = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
        if (StringUtils.isNotBlank(value))
            return value;

        return "chrome";
    }

    private String getDefaultForPlatformIfMissing(IInvokedMethod method) {
        String value = method.getTestMethod().getXmlTest().getLocalParameters().get("platform");
        if (StringUtils.isNotBlank(value))
            return value;

        return Constants.getOSName().matches("(.*)mac(.*)")
               ? "mac"
               : "windows";
    }
}
