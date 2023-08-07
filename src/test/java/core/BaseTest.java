package core;

import core.testrail.APIException;
import core.testrail.TestRailAPI;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.*;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;


public abstract class BaseTest {

    private Constants.Platform currentPlatform = Constants.Platform.ANDROID;

    private AppiumDriver driver;
    private TestRailAPI testRailAPI;
    private boolean isRunTestRailSuite = true;


    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass
    public void setUp(@Optional("androidOnly") String emulator, @Optional String platformName, @Optional String udid, @Optional String deviceName,
                           @Optional("androidOnly") String systemPort,
                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) throws Exception {

        Properties properties = new Properties();
        testRailAPI = new TestRailAPI();
        isRunTestRailSuite = false;
//        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
//        File logFile = new File(strFile);
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//        Log.info("log path: " + strFile);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_PROPERTIES_PATH);
        properties.load(fis);

        URL url = new URL(properties.getProperty(Constants.APPIUM_URL));

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
                if(System.getenv("BITRISE_APP_DIR_PATH")==null && System.getenv("BITRISE_SOURCE_DIR")==null){
                    option.setApp(System.getProperty("user.dir") + properties.getProperty(Constants.IOS_APP_PATH));
                }else if(System.getenv("BITRISE_APP_DIR_PATH")!=null) {
                    option.setApp(System.getenv("BITRISE_APP_DIR_PATH"));
                } else {
                    option.setApp(System.getenv("BITRISE_SOURCE_DIR") + "/src/test/java/binaries/FleetLocate TrailerView Staging.app");
                }
                option.autoAcceptAlerts();
                driver = new IOSDriver(url, option);
            }
            default -> throw new Exception("Invalid platform! - " + platformName);
        }

        init();
    }

    protected  void init(){};
    protected  void deInit(){};

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
            TestUtility.waitForVisibility(basePage.iOSLocationPermissionAlert, driver);
            if (TestUtility.isElementPresent(basePage.locationPermission)) {
                basePage.locationPermission.click();
            }
        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        deInit();
        if(driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(ITestContext ctx, Method testMethod) {
        if(isRunTestRailSuite) {
            testRailAPI.beforeTest(ctx, testMethod);
        }
    }

    @AfterMethod (alwaysRun = true)
    public void afterTest(ITestResult testResult, ITestContext
            context, Method testMethod) {
        if(isRunTestRailSuite) {
            testRailAPI.afterTest(context, testResult, testMethod);
        }
    }

    @BeforeSuite
    public void createTestRunSuite(ITestContext ctx) throws APIException, IOException {
        if(isRunTestRailSuite) {
            testRailAPI.createTestRunSuite(ctx);
        }
    }

}
