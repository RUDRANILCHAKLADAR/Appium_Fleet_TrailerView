package core;

public class Constants {

    public static final String USER_TOKEN = "userToken";
    public static String APPIUM_URL = "appiumURL";
    public static String ANDROID_DEVICE_NAME = "AndroidDeviceName";
    public static String ANDROID_PLATFORM_NAME = "android";
    public static String ANDROID_AUTOMATION_DRIVER = "androidAutomationDriver";
    public static String ANDROID_APP_PATH = "androidAppPath";
    public static String ANDROID_SERVER_INSTALL_TIMEOUT = "uiautomator2ServerInstallTimeout";
    public static String IOS_DEVICE_NAME = "iOSDeviceName";
    public static String IOS_PLATFORM_NAME = "iOS";
    public static String IOS_AUTOMATION_DRIVER = "iosAutomationDriver";
    public static String IOS_VERSION = "iOSVersion";
    public static String IOS_APP_PATH = "iOSAppPath";

    public static String CONFIG_PROPERTIES_PATH = "//src//test//java//config//config.properties";
    public static String ENVIRONMENT_CONFIG_PATH = "//src//test//java//config//environment.json";
    public static String ENVIRONMENT_CONFIG = "envConfig";
    public static String ENVIRONMENT_NAME = "environment";


    public enum Platform {
        ANDROID("android"),
        iOS("iOS");

        private String platformName = "";

        Platform(String name) {
            this.platformName = name;
        }

        public static Platform getPlatformFromName(String platformName) {
            if(platformName.equals(ANDROID.getPlatformName())) {
                return ANDROID;
            } else if(platformName.equals(iOS.getPlatformName())) {
                return iOS;
            }
            return ANDROID;
        }

        public String getPlatformName() {
            return platformName;
        }
    }

}
