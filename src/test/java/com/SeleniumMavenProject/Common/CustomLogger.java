package com.SeleniumMavenProject.Common;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CustomLogger {
	private static Logger logger;

	public static void initLogger() {
		logger = Logger.getLogger("Selenium_Tests");
		PropertyConfigurator.configure(System.getProperty("user.dir")
				+ File.separator + "log4j.properties");
	}

	public static void logInfo(String message) {
		logger.info(message);
	}

	public static void logWarning(String message) {
		logger.warn(message);
	}

	public static void logError(String message) {
		logger.error(message);
	}

	public static void startTestCase(Method testMethod) {
		logger.info(
				"--------------------------------------------------------------------------------------");
		logger.info(
				"			" + testMethod.getDeclaringClass().getSimpleName()
						+ "_" + testMethod.getName());
		logger.info(
				"--------------------------------------------------------------------------------------");
	}

	public static void endTestCase(Method testMethod) {
		logger.info(
				"--------------------------------------------------------------------------------------");
		logger.info("			End Of "
				+ testMethod.getDeclaringClass().getSimpleName() + "_"
				+ testMethod.getName());
		logger.info(
				"--------------------------------------------------------------------------------------");
	}

	public static void endLogger() {
		if (logger != null) {
			logger = null;
		}
	}
}
