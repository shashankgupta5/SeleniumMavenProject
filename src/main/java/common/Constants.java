package common;

public class Constants {
	public static final String BASE_USER_DIR = System.getProperty("user.dir") + "/",
			SRC_MAIN_DIR = "src/main/",
			SRC_MAIN_RESOURCE_DIR = BASE_USER_DIR + SRC_MAIN_DIR + "resources/",
			PATH_TO_CONFIG_FILE = SRC_MAIN_RESOURCE_DIR + "default_config.properties";

	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
}
