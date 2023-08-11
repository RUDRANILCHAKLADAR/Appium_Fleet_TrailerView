package testscenarios;

import core.BaseTest;
import core.Constants;
import core.TestUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import objects.VehicleListPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utils.model.UserToken;
import utils.nspireservice.AtiAvsService;
import utils.nspireservice.IdentityService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class VehicleListScreenTest extends BaseTest {

    private VehicleListPage vehicleListPage;

    @Override
    protected void init(ITestContext context) {
        vehicleListPage = new VehicleListPage(getDriver());
        AtiAvsService.setAtiAvsBaseUrl(envProperties.getAtiAvsBaseUrl());
        fetchNSetUserToken(context, "atistagetest", "Password1");
        TestUtility.logInUser(vehicleListPage, "atistagetest", "Password1");
    }

    private void fetchNSetUserToken(ITestContext context, String userName, String password) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-Nspire-AppToken", "f07740dc-1252-48f3-9165-c5263bbf373c");
        UserToken userToken = IdentityService.getUserToken(envProperties.getIdentityBaseUrl(), headers,userName, password);
        System.out.println("userToken: " + userToken);
        context.setAttribute(Constants.USER_TOKEN, userToken.getToken());
    }

    @Override
    protected void deInit() {
        logOutUser();
    }

    @Test(priority = 1)
    public void testValidateAssetListUi(ITestContext context) {
        disMissLocationPermission(vehicleListPage);
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
//        assertTrue(vehicleListPage.assetListTitle.isDisplayed());
//        assertTrue(vehicleListPage.searchIcon.isDisplayed());
//        assertTrue(vehicleListPage.moreIcon.isDisplayed());
//        assertTrue(vehicleListPage.listInfo.isDisplayed());
//        assertTrue(vehicleListPage.assetList.get(0).isDisplayed());

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("x-nspire-usertoken", context.getAttribute(Constants.USER_TOKEN).toString()); // received from Identity service
        headers.put("Content-Type", "application/json");

        JSONObject object = AtiAvsService.getAssetList(headers, JSONObject.class);

        System.out.println("assetlist  api: ");
        // verify asset list item
//        vehicleListPage.assetList.get(0).findElement(By.xpath("android.view.View[1]"));

        try {

//        startApp2();
        }catch (Exception e){
            System.out.println("Rupak error on launching second app");
        }

        //android.view.View[@content-desc="assetList"]/android.view.View[2]
    }



    public void logOutUser() {
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
        TestUtility.logOutUser(vehicleListPage, getDriver());
    }

    public static void startApp2() throws MalformedURLException {
        DesiredCapabilities cap2 = new DesiredCapabilities();
        cap2.setCapability(MobileCapabilityType.NO_RESET, true);
        cap2.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
        cap2.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5556");//emulator-5554
        cap2.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
//                options.setCapability("chromedriverExecutable","D:\\Automation_Project\\MobileAutomationBestPractices\\drivers\\chromedriver.exe");
        cap2.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
//        cap2.setCapability("appPackage", "com.android.chrome");
//        cap2.setCapability("appActivity", "your app1 package name");
        cap2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        URL url = new URL("http://127.0.0.1:4724/");
        AppiumDriver smsDriver = new AndroidDriver(url, cap2);
        smsDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

//        ((AppiumDriver)getDriver()).switchTo().
    }

}
