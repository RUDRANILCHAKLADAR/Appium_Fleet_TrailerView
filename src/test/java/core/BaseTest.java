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

    private Constants.Platform currentPlatform = null;

    private AppiumDriver driver;

    private static boolean isRunTestRailSuite = true;

    private static TestRailAPI androidTestRailApi;
    private static TestRailAPI iOSTestRailApi;

    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeTest
    public void setUp(@Optional("androidOnly") String emulator, @Optional String platformName, @Optional String udid, @Optional String deviceName,
                           @Optional("androidOnly") String systemPort,
                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort, ITestContext ctx) throws Exception {

        Properties properties = new Properties();

        isRunTestRailSuite = true;
//        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
//        File logFile = new File(strFile);
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//        Log.info("log path: " + strFile);
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_PROPERTIES_PATH);
        properties.load(fis);

        URL url = new URL(properties.getProperty(Constants.APPIUM_URL));

//        platformName = "android";
        System.out.println("emulator = " + emulator + ", platformName = " + platformName);

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
                option.setApp(System.getProperty("user.dir") + properties.getProperty(Constants.IOS_APP_PATH));
                option.autoAcceptAlerts();
                driver = new IOSDriver(url, option);
            }
            default -> throw new Exception("Invalid platform! - " + platformName);
        }

        init();
        createTestRunSuite(ctx);
        System.out.println("rupak before test - 1  " + currentPlatform + " ctx: "+ctx);
    }

    /*@BeforeTest
    public void testBefore() {
        System.out.println("rupak before test " + currentPlatform);
    }

    @BeforeGroups
    public void testBeforeGroups() {
        System.out.println("rupak before groups "+  currentPlatform);
    }

    @AfterTest
    public void testAfterTest() {
        System.out.println("rupak after test "+  currentPlatform);
    }

    @AfterMethod
    public void testAfterMethod() {
        System.out.println("rupak after method "+  currentPlatform);
    }

    @AfterClass
    public void testAfterClass() {
        System.out.println("rupak after class "+  currentPlatform);
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("rupak after suite "+  currentPlatform);
    }

    @AfterGroups
    public void testAfterGroup() {
        System.out.println("rupak after group "+  currentPlatform);
    }

    @BeforeMethod
    public void testBeforeMethods() {
        System.out.println("rupak before methods " + currentPlatform);
    }

    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("rupak before suite " + currentPlatform);
    }

    @Parameterized.BeforeParam
    public void testBeforepaam() {
        System.out.println("rupak before param " + currentPlatform);
    }*/

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
            // todo handle by iOS
        }
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        deInit();
        if(driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(ITestContext ctx, Method testMethod) {
        if(isRunTestRailSuite) {
            getTestRailApi().beforeTest(ctx, testMethod, currentPlatform);
        }
    }

    @AfterMethod (alwaysRun = true)
    public void afterTestMethod(ITestResult testResult, ITestContext
            context, Method testMethod) {
        if(isRunTestRailSuite) {
            getTestRailApi().afterTest(context, testResult, testMethod);
        }
    }

    private TestRailAPI getTestRailApi() {
        if(isAndroidPlatform()) return androidTestRailApi;
        else return iOSTestRailApi;
    }

    //    @BeforeTest
    public void createTestRunSuite(ITestContext ctx) throws APIException, IOException {
        System.out.println("this = currentPlatform:" + currentPlatform);
        if(isRunTestRailSuite) {
            if(isAndroidPlatform()) {
                androidTestRailApi = new TestRailAPI();
                androidTestRailApi.createTestRunSuite(ctx, currentPlatform);
            } else if(isIosPlatform()) {
                iOSTestRailApi = new TestRailAPI();
                iOSTestRailApi.createTestRunSuite(ctx, currentPlatform);
            }
        }
    }

}
