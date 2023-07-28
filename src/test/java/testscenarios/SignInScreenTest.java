package testscenarios;

import core.BaseTest;
import core.TestUtility;
import objects.SignInPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignInScreenTest extends BaseTest {

    private SignInPage signInPage;

    @Override
    protected void initPageObject() {
        signInPage = new SignInPage(getDriver());
    }

    @Test(priority = 1)
    public void testUiValidation(){
//        TestUtility.waitForVisibility(signInPage.signInTitle, getDriver());
        signInPage.signInTitle.isDisplayed();
        signInPage.userNameEditText.isDisplayed();
        assertTrue(signInPage.userNameEditText.isDisplayed());
        TestUtility.waitForVisibility(signInPage.userNameEditText, getDriver());
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        signInPage.userNameEditText.isDisplayed();
        signInPage.passWordEditText.sendKeys("Spireon@123");
        assertTrue(signInPage.pwdEye.isDisplayed());
        signInPage.pwdEye.click();
        assertTrue(signInPage.logInButton.isDisplayed());
//        signInPage.logInButton.click();
//        Activity activity = new Activity("com.spireon.atidriver.stage", "com.spireon.atidriver.cognito.forgotpassword.ForgotPasswordActivity");
//        activity.setStopApp(false);
//        ((AndroidDriver)getDriver()).startActivity(activity);
//        TestUtility.waitFor(60, getDriver());
    }

    @Test(priority = 2)
    public void testLoginErrorValidation(){
        assertTrue(signInPage.userNameEditText.isDisplayed());
        // assert login button is disabled if text boxes are empty
//        assertFalse(signInPage.logInButton.isEnabled());
        signInPage.userNameEditText.clear();
        signInPage.passWordEditText.clear();
        signInPage.userNameEditText.sendKeys("123");
        // assert login button is disabled if any text box is empty
//        assertFalse(signInPage.logInButton.isEnabled());
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
    public void testLogInFlowSuccess() throws InterruptedException {
        signInPage.userNameEditText.clear();
        signInPage.passWordEditText.clear();
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        signInPage.passWordEditText.sendKeys("Spireon@1234");

        signInPage.logInButton.click();
        disMissLocationPermission(signInPage);
        TestUtility.waitForVisibility(signInPage.assetListScreenTitle, getDriver());
    }

}
