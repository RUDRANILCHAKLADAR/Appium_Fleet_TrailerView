package testscenarios;

import core.BaseTest;
import core.TestUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import objects.VehicleListPage;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class VehicleListScreenTest extends BaseTest {

    private VehicleListPage vehicleListPage;

    @Override
    protected void init() {
        vehicleListPage = new VehicleListPage(getDriver());
        TestUtility.logInUser(vehicleListPage, "atistagetest", "Password1");
    }

    @Override
    protected void deInit() {
        logOutUser();
    }

    @Test(priority = 1)
    public void testValidateAssetListUi() {
        disMissLocationPermission(vehicleListPage);
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
        assertTrue(vehicleListPage.assetListTitle.isDisplayed());
        assertTrue(vehicleListPage.searchIcon.isDisplayed());
        assertTrue(vehicleListPage.moreIcon.isDisplayed());
        assertTrue(vehicleListPage.listInfo.isDisplayed());
        assertTrue(vehicleListPage.assetList.get(0).isDisplayed());

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
