package config;

import common.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = new FileInputStream(Constants.PATH_TO_CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getProp(String propertyName) {
        if (!StringUtils.isBlank(propertyName)) {
            return properties.getProperty(propertyName);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static int getExplicitWaitTimeOut() {
        return Integer.parseInt(getProp("EXPLICIT_WAIT_TIMEOUT"));
    }

    public static int getSleepTimeOut() {
        return Integer.parseInt(getProp("SLEEP_TIMEOUT"));
    }

    static String getGridURL() {
        return getProp("GRID_URL");
    }

    static int getImplicitWaitTimeOut() {
        return Integer.parseInt(getProp("IMPLICIT_WAIT_TIMEOUT"));
    }

    static boolean useRemoteWebDriver() {
        return Boolean.parseBoolean(getProp("IS_REMOTE_ENVIRONMENT"));
    }

}
