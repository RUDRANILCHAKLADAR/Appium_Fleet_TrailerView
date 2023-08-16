package testscenarios;

import core.BaseTest;
import core.TestUtility;
import core.testrail.TestRailIdAndroid;
import core.testrail.TestRailIdIos;
import objects.SignInPage;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignInScreenTest extends BaseTest {

    private SignInPage signInPage;

    @Override
    protected void init(ITestContext context) {
        signInPage = new SignInPage(getDriver());
    }

    @Override
    protected void deInit() {
        logOutUser();
    }

    @Test(priority = 1)
    @TestRailIdAndroid( androidTags = {"146958", "146962"})
    @TestRailIdIos( iOSTags = {"147708", "147709"})
    public void testUiValidation(){
        assertTrue(signInPage.userNameEditText.isDisplayed());
        TestUtility.waitForVisibility(signInPage.userNameEditText, getDriver());
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        assertTrue(signInPage.userNameEditText.isDisplayed());
        signInPage.passWordEditText.sendKeys("Spireon@123");
        assertTrue(signInPage.pwdEye.isDisplayed());
        signInPage.pwdEye.click();
        assertTrue(signInPage.logInButton.isDisplayed());

//        String expectedErrTxt = getStrings().get("err_invalid_username_or_password");

//        System.out.println("expectedErrTxt: "+expectedErrTxt);

        /*HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-Nspire-AppToken", "f07740dc-1252-48f3-9165-c5263bbf373c");
        UserToken userToken = IdentityService.getUserToken(headers,"tv_rs_admin", "Password@1", envProperties.getIdentityBaseUrl());
        System.out.println("userToken: " + userToken);*/
    }

    @Test(priority = 2)
    public void testLoginErrorValidation(){
        assertTrue(signInPage.userNameEditText.isDisplayed());

        signInPage.userNameEditText.clear();
        signInPage.passWordEditText.clear();
        signInPage.userNameEditText.sendKeys("123");
        signInPage.passWordEditText.sendKeys("123");
        assertTrue(signInPage.logInButton.isEnabled());
        signInPage.logInButton.click();
        // validation of invalid credentials
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(signInPage.invalidCrednetialErrorTitle, getDriver());
            assertEquals(signInPage.invalidCrednetialErrorMsg.getText(), "The credentials entered do not match our records. Verify your username and password.");
            assertTrue(signInPage.dialogOkButton.isDisplayed());
            signInPage.dialogOkButton.click();
        } else {
            // todo handle iOS related code
        }

        // validation of No Network
        TestUtility.turnOffInternet(getDriver());
        signInPage.logInButton.click();
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(signInPage.noNetworkErrorTitle, getDriver());
            assertEquals(signInPage.noNetworkErrorMsg.getText(), "Please check your network connection and try again.");
            assertTrue(signInPage.dialogOkButton.isDisplayed());
            signInPage.dialogOkButton.click();
        } else {
            // todo handle iOS related code
        }
        TestUtility.turnOnInternet(getDriver());

        // validate network error
        signInPage.userNameEditText.clear();
        signInPage.passWordEditText.clear();
        signInPage.userNameEditText.sendKeys("tv_rs_25");
        signInPage.passWordEditText.sendKeys("Spireon@123");

        signInPage.logInButton.click();
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(signInPage.otherErrorTitle, getDriver());
            assertEquals(signInPage.otherErrorMsg.getText(), "The user must reset their password");
            assertTrue(signInPage.dialogOkButton.isDisplayed());
            signInPage.dialogOkButton.click();
        } else {
            // todo handle iOS related code
        }
    }

    @Test(priority = 3)
    public void testLogInFlowSuccess() {

        TestUtility.logInUser(signInPage, "tv_rs_27", "Spireon@1234");

        disMissLocationPermission(signInPage);
        TestUtility.waitForVisibility(signInPage.assetListScreenTitle, getDriver());
    }

    private void logOutUser() {
        TestUtility.waitForVisibility(signInPage.homeMoreButton, getDriver());
        TestUtility.logOutUser(signInPage, getDriver());
    }


}
