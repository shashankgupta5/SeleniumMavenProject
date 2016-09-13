package com.SeleniumMavenProject.Common;

import org.testng.Assert;

public class Assertions {

	public static boolean assertEquals(Object actual, Object expected,
			String message) {
		boolean check = true;
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			CustomLogger
					.logError("assertEquals: check failed, " + e.getMessage());
			check = false;
		}
		return check;
	}

	public static boolean assertNotEquals(Object actual, Object expected,
			String message) {
		boolean check = true;
		try {
			Assert.assertNotEquals(actual, expected, message + " expected ["
					+ expected + "] while found is [" + actual + "]");
		} catch (AssertionError e) {
			CustomLogger.logError(
					"assertNotEquals: check failed, " + e.getMessage());
			check = false;
		}
		return check;
	}

	public static boolean assertTrue(boolean condition, String message) {
		boolean check = true;
		try {
			Assert.assertTrue(condition, message);
		} catch (AssertionError e) {
			CustomLogger
					.logError("assertTrue: check failed, " + e.getMessage());
			check = false;
		}
		return check;
	}

	public static boolean assertFalse(boolean condition, String message) {
		boolean check = true;
		try {
			Assert.assertFalse(condition, message);
		} catch (AssertionError e) {
			CustomLogger
					.logError("assertFalse: check failed, " + e.getMessage());
			check = false;
		}
		return check;
	}

	public static boolean assertStringContains(String actual,
			String expectedToContain) {
		boolean check = true;
		try {
			if (!actual.contains(expectedToContain)) {
				throw new AssertionError("expected [" + expectedToContain
						+ "] to be present in [" + actual + "]");
			}
		} catch (AssertionError e) {
			CustomLogger.logError(
					"assertStringContains: check failed, " + e.getMessage());
			check = false;
		}
		return check;
	}

	public static void assertTestPass(boolean actual, boolean expected) {
		if (!assertEquals(actual, expected, "")) {
			throw new AssertionError(
					"assertTestPass:  final check for test case failed, expected ["
							+ expected + "] while found [" + actual + "]");
		}
	}
}
