package core;

import core.testrail.APIException;
import core.testrail.TestRailAPI;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.testng.*;
import org.testng.annotations.*;
import utils.Utils;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public abstract class BaseTest {

    private Constants.Platform currentPlatform = null;

    protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();

    private static boolean isRunTestRailSuite = false;

    private static boolean shouldCaptureVideo = true;
    private static boolean shouldCaptureVideoOnlyFailure = false;

    private static TestRailAPI androidTestRailApi;
    private static TestRailAPI iOSTestRailApi;

    public EnvProperties envProperties;

    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass
    public void setUp(@Optional("androidOnly") String emulator, @Optional String platformName, @Optional String udid, @Optional String deviceName,
                           @Optional("androidOnly") String systemPort,
                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort, ITestContext context) throws Exception {

        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_PROPERTIES_PATH);
        properties.load(fis);

        URL url = new URL(properties.getProperty(Constants.APPIUM_URL));
        AppiumDriver driver;
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
                option.setAutoAcceptAlerts(false);
                driver = new IOSDriver(url, option);
            }
            default -> throw new Exception("Invalid platform! - " + platformName);
        }
        setDriver(driver);
        init(context);
    }

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    @Parameters({"platformName"})
    @BeforeTest
    public void beforeTest(@Optional String platformName, ITestContext ctx) throws Exception {
        switch (Constants.Platform.getPlatformFromName(platformName)) {
            case ANDROID -> {
                currentPlatform = Constants.Platform.ANDROID;
            }
            case iOS -> {
                currentPlatform = Constants.Platform.iOS;
            }
        }
        createTestRunSuite(ctx);

        String xmlFileName = "strings/strings.xml";

        InputStream stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        setStrings(Utils.parseStringXML(stringsis));
    }

    public static String DefaultTestVideoFileName() {
        return  "testVideo_" + Utils.dateTime();
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

    protected abstract void init(ITestContext context);

    protected abstract void deInit();

    public Constants.Platform getCurrentPlatform() {
        return currentPlatform;
    }

    public boolean isAndroidPlatform() {
        return currentPlatform == Constants.Platform.ANDROID;
    }

    public boolean isIosPlatform() {
        return currentPlatform == Constants.Platform.iOS;
    }

    public void disMissLocationPermission(BasePage basePage) {
        if (!isAndroidPlatform()) {
            TestUtility.waitForVisibility(basePage.iOSLocationPermissionAlert, getDriver());
        }
        TestUtility.waitForVisibility(basePage.locationPermission, getDriver());
        if (TestUtility.isElementPresent(basePage.locationPermission)) {
            basePage.locationPermission.click();
        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        deInit();
        if(getDriver() != null) {
            getDriver().quit();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(ITestContext ctx, Method testMethod) {
        if(isRunTestRailSuite) {
            getTestRailApi().beforeTest(ctx, testMethod, currentPlatform);
        }
        Utils.startVideo(shouldCaptureVideo, getDriver());
    }

    @AfterMethod (alwaysRun = true)
    public void afterTestMethod(ITestResult testResult, ITestContext
            context, Method testMethod) {
        if(isRunTestRailSuite) {
            getTestRailApi().afterTest(context, testResult, testMethod);
        }
        try {
            Utils.stopVideo(testMethod, testResult, shouldCaptureVideo, shouldCaptureVideoOnlyFailure, getDriver(), context);
        }catch (Exception e){
            System.out.println("Stop Video Error :"+e.getLocalizedMessage());
        }
    }

    private TestRailAPI getTestRailApi() {
        if(isAndroidPlatform()) return androidTestRailApi;
        else return iOSTestRailApi;
    }

    public void createTestRunSuite(ITestContext ctx) throws APIException, IOException {
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

    @Parameters("environment")
    @BeforeSuite
    public void beforeSuite(ITestContext ctx, String environment) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + Constants.ENVIRONMENT_CONFIG_PATH));

            JSONObject jsonObject =  (JSONObject) obj;
            ctx.setAttribute(Constants.ENVIRONMENT_CONFIG, jsonObject.get(environment));
            ctx.setAttribute(Constants.ENVIRONMENT_NAME, environment);
            envProperties = new EnvProperties(ctx);
        } catch (Exception exception) {
            System.out.println("error reading config file --: "+exception);
        }
    }

    public HashMap<String, String> getStrings() {
        return strings.get();
    }

    public void setStrings(HashMap<String, String> strings2) {
        strings.set(strings2);
    }

}
