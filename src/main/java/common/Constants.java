package common;

public class Constants {
    public static final String BASE_USER_DIR = System.getProperty("user.dir") + "/",
            SRC_MAIN_DIR = "src/main/",
            SRC_MAIN_RESOURCE_DIR = BASE_USER_DIR + SRC_MAIN_DIR + "resources/",
            PATH_TO_CONFIG_FILE = SRC_MAIN_RESOURCE_DIR + "default_config.properties";

//    private static final String PATH_TO_DRIVER_EXE = SRC_MAIN_RESOURCE_DIR + "driver/";

//    public static String getPathToGeckoDriverExe() {
//        return getOSName().matches("(.*)mac(.*)")
//               ? PATH_TO_DRIVER_EXE + "GeckoDriver/geckodriver"
//               : PATH_TO_DRIVER_EXE + "GeckoDriver/geckodriver.exe";
//    }
//
//    public static String getPathToChromeDriverExe() {
//        return getOSName().matches("(.*)mac(.*)")
//               ? PATH_TO_DRIVER_EXE + "ChromeDriver/chromedriver"
//               : PATH_TO_DRIVER_EXE + "ChromeDriver/chromedriver.exe";
//    }

    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }
}
