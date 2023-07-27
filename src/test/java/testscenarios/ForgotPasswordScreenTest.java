package testscenarios;

import core.BaseTest;
import core.TestUtility;
import objects.ForgotPasswordPage;
import objects.SignInPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ForgotPasswordScreenTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;
    private SignInPage signInPage;

    @Override
    protected void initPageObject() {
        signInPage = new SignInPage(getDriver());
        forgotPasswordPage = new ForgotPasswordPage(getDriver());
    }

    private void launchForgotPasswordScreen() {
        if (forgotPasswordPage.forgotPasswordTitle.isDisplayed()) {
            return;
        }
        assertTrue(signInPage.forgotPwd.isDisplayed());
        signInPage.forgotPwd.click();
        TestUtility.waitForVisibility(forgotPasswordPage.forgotPasswordTitle, getDriver());
    }

    @Test(priority = 1)
    public void testUiValidation(){
        launchForgotPasswordScreen();
        assertTrue(forgotPasswordPage.forgotPasswordTitle.isDisplayed());
        assertTrue(forgotPasswordPage.forgotPasswordDescription.isDisplayed());
        assertTrue(forgotPasswordPage.userNameEditText.isDisplayed());
//        assertTrue(forgotPasswordPage.userNameEditTextHint.isDisplayed());
        assertTrue(forgotPasswordPage.requestAccessCodeText.isDisplayed());
        assertTrue(forgotPasswordPage.requestAccessCodeButton.isDisplayed());
    }

    @Test(priority = 2)
    public void testLoginErrorValidation(){
        launchForgotPasswordScreen();

        forgotPasswordPage.userNameEditText.clear();

        forgotPasswordPage.userNameEditText.sendKeys("123");
        forgotPasswordPage.requestAccessCodeButton.click();

        // validation of invalid credentials
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(forgotPasswordPage.errorTitle, getDriver());
            assertEquals(forgotPasswordPage.userNotExistsErrorMsg.getText(), "Unable to send password reset.");
            assertTrue(forgotPasswordPage.errorDialogOk.isDisplayed());
            forgotPasswordPage.errorDialogOk.click();
        } else {
            // todo handle iOS related code
        }

        // validation of No Network
        TestUtility.turnOffInternet(getDriver());
        forgotPasswordPage.requestAccessCodeButton.click();
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(forgotPasswordPage.noNetworkErrorTitle, getDriver());
            assertEquals(forgotPasswordPage.noNetworkErrorMsg.getText(), "Please check your network connection and try again.");
            assertTrue(forgotPasswordPage.errorDialogOk.isDisplayed());
            forgotPasswordPage.errorDialogOk.click();
        } else {
            // todo handle iOS related code
        }
        TestUtility.turnOnInternet(getDriver());
    }

    @Test(priority = 3)
    public void testForgotPasswordFlowSuccess() {
        launchForgotPasswordScreen();
        forgotPasswordPage.userNameEditText.clear();
        forgotPasswordPage.userNameEditText.sendKeys("tv_rs_25");

        forgotPasswordPage.requestAccessCodeButton.click();

        TestUtility.waitForVisibility(forgotPasswordPage.forgotPasswordValidationScreenDescription, getDriver());
    }

}
