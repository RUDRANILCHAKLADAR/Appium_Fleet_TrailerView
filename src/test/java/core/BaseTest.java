package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseTest {

    private Constants.Platform currentPlatform = Constants.Platform.ANDROID;

    private AppiumDriver driver;


    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass
    public void setUp(@Optional("androidOnly") String emulator, @Optional String platformName, @Optional String udid, @Optional String deviceName,
                           @Optional("androidOnly") String systemPort,
                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) throws Exception {

        Properties properties = new Properties();
//        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
//        File logFile = new File(strFile);
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//        Log.info("log path: " + strFile);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_PROPERTIES_PATH);
        properties.load(fis);

        URL url = new URL(properties.getProperty(Constants.APPIUM_URL));

        platformName = "android";
        switch (Constants.Platform.getPlatformFromName(platformName)) {
            case ANDROID -> {
                currentPlatform = Constants.Platform.ANDROID;
                UiAutomator2Options options = new UiAutomator2Options();
                options.setDeviceName(properties.getProperty(Constants.ANDROID_DEVICE_NAME));
                options.setPlatformName(Constants.ANDROID_PLATFORM_NAME);
                options.setAutomationName(properties.getProperty(Constants.ANDROID_AUTOMATION_DRIVER));
                options.setAppWaitForLaunch(true);
                options.setCapability("appPackage", "com.spireon.atidriver.stage");
                options.setCapability("appium:disableIdLocatorAutocompletion", true);
                options.setAppWaitPackage("com.spireon.atidriver.stage");
                if (System.getenv("BITRISE_APK_PATH") == null && System.getenv("BITRISE_SOURCE_DIR") == null) {
                    options.setApp(System.getProperty("user.dir") + properties.getProperty(Constants.ANDROID_APP_PATH));
                } else if (System.getenv("BITRISE_APK_PATH") != null) {
                    options.setApp(System.getenv("BITRISE_APK_PATH"));
                } else {
                    options.setApp(System.getenv("BITRISE_SOURCE_DIR") + properties.getProperty(Constants.ANDROID_APP_PATH));
                }
                options.setCapability(Constants.ANDROID_SERVER_INSTALL_TIMEOUT, 30000);
//                options.autoGrantPermissions();
                driver = new AndroidDriver(url, options);
            }
            case iOS -> {
                currentPlatform = Constants.Platform.iOS;
                XCUITestOptions option = new XCUITestOptions();
                option.setDeviceName(properties.getProperty(Constants.IOS_DEVICE_NAME));
                option.setPlatformName(Constants.IOS_PLATFORM_NAME);
                option.setAutomationName(properties.getProperty(Constants.IOS_AUTOMATION_DRIVER));
                option.setPlatformVersion(properties.getProperty(Constants.IOS_VERSION));
                option.setWdaLaunchTimeout(Duration.ofSeconds(30));
                //option.setApp(System.getProperty("user.dir") + "//App//Fleet Staging.app");
                option.setApp(properties.getProperty(Constants.IOS_APP_PATH));
                option.autoAcceptAlerts();
                driver = new IOSDriver(url, option);
            }
            default -> throw new Exception("Invalid platform! - " + platformName);
        }

        initPageObject();
    }

    protected abstract void initPageObject();

    public Constants.Platform getCurrentPlatform() {
        return currentPlatform;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public boolean isAndroidPlatform() {
        return currentPlatform == Constants.Platform.ANDROID;
    }

    public boolean isIosPlatform() {
        return currentPlatform == Constants.Platform.iOS;
    }

    public void disMissLocationPermission(BasePage basePage) {
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(basePage.locationPermission, driver);
            if (TestUtility.isElementPresent(basePage.locationPermission)) {
                basePage.locationPermission.click();
            }
        } else {
            // todo handle by iOS
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }



}
