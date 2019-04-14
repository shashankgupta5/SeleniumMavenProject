package common;

public class Constants {
    public static final String BASE_USER_DIR = System.getProperty("user.dir") + "/",
            SRC_MAIN_DIR = "src/main/",
            SRC_MAIN_RESOURCE_DIR = BASE_USER_DIR + SRC_MAIN_DIR + "resources/",
            SRC_TEST_DIR = "src/test/",
            SRC_TEST_RESOURCE_DIR = BASE_USER_DIR + SRC_TEST_DIR + "resources/",
            PATH_TO_CONFIG_FILE = SRC_MAIN_RESOURCE_DIR + "default_config.properties",
            PATH_TO_DRIVER_EXE = SRC_MAIN_RESOURCE_DIR + "driver/",
            PATH_TO_GECKO_DRIVER_EXE = PATH_TO_DRIVER_EXE + "GeckoDriver/geckodriver.exe",
            PATH_TO_CHROME_DRIVER_EXE = PATH_TO_DRIVER_EXE + "ChromeDriver/chromedriver.exe",
            PATH_TO_IE_DRIVER_EXE = PATH_TO_DRIVER_EXE + "IEDriver/IEDriverServer.exe",
            PATH_TO_EDGE_DRIVER_EXE = PATH_TO_DRIVER_EXE + "EdgeDriver/MicrosoftWebDriver.exe";
}
