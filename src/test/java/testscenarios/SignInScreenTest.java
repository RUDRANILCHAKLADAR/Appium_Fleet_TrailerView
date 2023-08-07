package testscenarios;

import core.BaseTest;
import core.TestUtility;
import core.testrail.TestRailIdAndroid;
import core.testrail.TestRailIdIos;
import objects.SignInPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignInScreenTest extends BaseTest {

    private SignInPage signInPage;

    @Override
    protected void init() {
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
        TestUtility.waitForVisibility(signInPage.signInTitle, getDriver());
        signInPage.signInTitle.isDisplayed();
        signInPage.userNameEditText.isDisplayed();
        assertTrue(signInPage.userNameEditText.isDisplayed());
        TestUtility.waitForVisibility(signInPage.userNameEditText, getDriver());
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        assertTrue(signInPage.userNameEditText.isDisplayed());
        signInPage.passWordEditText.sendKeys("Spireon@123");
        assertTrue(signInPage.pwdEye.isDisplayed());
        signInPage.pwdEye.click();
        assertTrue(signInPage.logInButton.isDisplayed());
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
//        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(signInPage.invalidCrednetialErrorTitle, getDriver());
            assertEquals(signInPage.invalidCrednetialErrorMsg.getText(), "The credentials entered do not match our records. Verify your username and password.");
            assertTrue(signInPage.dialogOkButton.isDisplayed());
            signInPage.dialogOkButton.click();
//        } else {
//            // todo handle iOS related code
//        }

        /* Not written in iOS as iOS driver doesn't support netowrk on/off events */
        if(isAndroidPlatform()) {
            // validation of No Network
            TestUtility.turnOffInternet(getDriver());
            signInPage.logInButton.click();
                TestUtility.waitForVisibility(signInPage.noNetworkErrorTitle, getDriver());
                assertEquals(signInPage.noNetworkErrorMsg.getText(), "Please check your network connection and try again.");
                assertTrue(signInPage.dialogOkButton.isDisplayed());
                signInPage.dialogOkButton.click();
            TestUtility.turnOnInternet(getDriver());
        }

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

    @AfterClass
    public void logOutUser() {
        if (signInPage.homeMoreButton.isDisplayed()) {
            TestUtility.waitForVisibility(signInPage.homeMoreButton, getDriver());
            if (isAndroidPlatform()) {
                TestUtility.logOutUser(signInPage, getDriver());
            } else {
                TestUtility.logOutUseriOS(signInPage, getDriver());
            }
        }
    }
}
