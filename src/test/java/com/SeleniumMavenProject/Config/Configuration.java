package com.SeleniumMavenProject.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;

import com.SeleniumMavenProject.Common.CustomLogger;

public class Configuration {
	private static String DEFAULT_CONFIG_FILE_NAME = System.getProperty(
			"user.dir") + File.separator + "default_config.properties";
	private static Properties prop;
	private static InputStream inputStream;

	public static void initConfigFile() throws Exception {
		prop = new Properties();
		inputStream = new FileInputStream(DEFAULT_CONFIG_FILE_NAME);

		try {
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new Exception(String.format(
					"startConfiguration: CANNOT FIND DEFAULT CONFIG FILE : {%s}",
					DEFAULT_CONFIG_FILE_NAME));
		}
	}

	private static String getProp(String propertyName) {
		if (!StringUtils.isBlank(propertyName)) {
			return prop.getProperty(propertyName);
		} else {
			return StringUtils.EMPTY;
		}
	}

	public static Dimension getBrowserSize() {
		String size = getProp("BROWSER_SIZE");

		if (size.equals("MAXIMIZE") || size.split("x").length == 2) {
			if (size.equals("MAXIMIZE")) {
				return null;
			} else {
				return new Dimension(Integer.parseInt(size.split("x")[0]),
						Integer.parseInt(size.split("x")[1]));
			}
		} else {
			CustomLogger.logError(String.format(
					"getBrowserSize: {%s} is not a proper value", size));
			throw new WebDriverException(String.format(
					"getBrowserSize: {%s} is not a proper value", size));
		}
	}

	public static int getExplicitWaitTimeOut() {
		return Integer.parseInt(getProp("EXPLICIT_WAIT_TIMEOUT"));
	}

	public static int getSleepTimeOut() {
		return Integer.parseInt(getProp("SLEEP_TIMEOUT"));
	}

	static String getBrowser() {
		return getProp("BROWSER");
	}

	static int getImplicitWaitTimeOut() {
		return Integer.parseInt(getProp("IMPLICIT_WAIT_TIMEOUT"));
	}

	public static void endConfigFile() {
		if (inputStream != null) {
			inputStream = null;
		}
		if (prop != null) {
			prop = null;
		}
	}
}
