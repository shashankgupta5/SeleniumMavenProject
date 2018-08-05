package config;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    static {
        try {
            String DEFAULT_CONFIG_FILE = System.getProperty("user.dir") + File.separator
                    + "default_config.properties";
            properties = new Properties();

            InputStream inputStream = new FileInputStream(DEFAULT_CONFIG_FILE);
            properties.load(inputStream);
            inputStream.close();
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
