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
        TestUtility.waitForVisibility(vehicleListPage.listInfo, getDriver());
        assertTrue(vehicleListPage.assetListTitle.isDisplayed());
        assertTrue(vehicleListPage.searchIcon.isDisplayed());
        assertTrue(vehicleListPage.moreIcon.isDisplayed());
        assertTrue(vehicleListPage.listInfo.isDisplayed());

        JSONObject assetList = getAssetList(context);

        assert assetList != null;
        assertTrue(vehicleListPage.listInfo.getText().contains(assetList.get("total").toString()));
    }

    private JSONObject getAssetList(ITestContext context) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("x-nspire-usertoken", context.getAttribute(Constants.USER_TOKEN).toString()); // received from Identity service
        headers.put("Content-Type", "application/json");

        return AtiAvsService.getAssetList(headers, JSONObject.class);
    }



    public void logOutUser() {
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
        if (isAndroidPlatform()) {
            TestUtility.logOutUser(vehicleListPage, getDriver());
        } else {
//                TestUtility.logOutUseriOS(vehicleListPage, getDriver());
        }
    }

}
