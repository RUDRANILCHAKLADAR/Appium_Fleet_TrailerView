package testscenarios;

import core.BaseTest;
import core.TestUtility;
import objects.ForgotPasswordPage;
import objects.SignInPage;
import org.testng.ITestContext;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ForgotPasswordScreenTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;
    private SignInPage signInPage;

    @Override
    protected void init(ITestContext context) {
        signInPage = new SignInPage(getDriver());
        forgotPasswordPage = new ForgotPasswordPage(getDriver());
    }

    @Override
    protected void deInit() {
        redirectLoginScreen();
    }

    private void launchForgotPasswordScreen() {
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
        assertTrue(forgotPasswordPage.userNameEditTextHint.isDisplayed());
        assertTrue(forgotPasswordPage.requestAccessCodeText.isDisplayed());
        assertTrue(forgotPasswordPage.requestAccessCodeButton.isDisplayed());
    }

    @Test(priority = 2)
    public void testLoginErrorValidation(){

        forgotPasswordPage.userNameEditText.clear();

        forgotPasswordPage.userNameEditText.sendKeys("000");
        forgotPasswordPage.requestAccessCodeButton.click();

        // validation of invalid credentials
        if(isAndroidPlatform()) {
            TestUtility.waitForVisibility(forgotPasswordPage.errorTitle, getDriver());
            assertEquals(forgotPasswordPage.userNotExistsErrorMsg.getText(), "Unable to send password reset.");
            assertTrue(forgotPasswordPage.errorDialogOk.isDisplayed());
            forgotPasswordPage.errorDialogOk.click();
        } else {
            // todo handle iOS related code
            TestUtility.waitForVisibility(forgotPasswordPage.errorTitle, getDriver());
            assertEquals(forgotPasswordPage.userNotExistsErrorMsg.getText(), "Unable to send password reset.");
            assertTrue(forgotPasswordPage.errorDialogOk.isDisplayed());
            forgotPasswordPage.errorDialogOk.click();
        }

        // validation of No Network
        if(isAndroidPlatform()) {
            TestUtility.turnOffInternet(getDriver());
            forgotPasswordPage.requestAccessCodeButton.click();
            TestUtility.waitForVisibility(forgotPasswordPage.noNetworkErrorTitle, getDriver());
            assertEquals(forgotPasswordPage.noNetworkErrorMsg.getText(), "Please check your network connection and try again.");
            assertTrue(forgotPasswordPage.errorDialogOk.isDisplayed());
            forgotPasswordPage.errorDialogOk.click();
            TestUtility.turnOnInternet(getDriver());
        } else {
            // todo handle iOS related code
        }
    }

    @Test(priority = 3)
    public void testForgotPasswordFlowSuccess() {

        forgotPasswordPage.userNameEditText.clear();

        while (!forgotPasswordPage.userNameEditText.getText().isEmpty()) {
            forgotPasswordPage.userNameEditText.sendKeys(Keys.BACK_SPACE);
        }
        forgotPasswordPage.userNameEditText.sendKeys("tv_rs_23");

        forgotPasswordPage.requestAccessCodeButton.click();

        TestUtility.waitForVisibility(forgotPasswordPage.forgotPasswordValidationScreenDescription, getDriver());
    }

    @Test(priority = 4)
    public void testRecoverCredentialsScreenUi() {

        assertTrue(forgotPasswordPage.recoverCredentialTitle.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialSubTitle.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialUserName.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialUserNameEditText.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialAccessCodeEditText.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialAccessCode.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialResend.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialResendBtn.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialPwdRequirements.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialAtLeastOneOfEach.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialUpperLowerCase.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialNumberSpecialCharacters.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialCreateNewPwd.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialConfirmNewPwdEditText.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialResetPwdBtn.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialResetPwd.isDisplayed());
        assertTrue(forgotPasswordPage.recoverCredentialCreateNewPwdEditText.isDisplayed());
    }

    @Test(priority = 5)
    public void testEditTextErrorMsgValidation() {

        forgotPasswordPage.recoverCredentialAccessCodeEditText.sendKeys("123");
        assertTrue(forgotPasswordPage.invalidAccessCodeErrorMessage.isDisplayed());
        forgotPasswordPage.recoverCredentialAccessCodeEditText.sendKeys("123");

        if (isAndroidPlatform()) {
            forgotPasswordPage.recoverCredentialCreateNewPwdEditText.sendKeys("test");
            forgotPasswordPage.recoverCredentialConfirmNewPwdEditText.sendKeys("test12");
            assertTrue(forgotPasswordPage.passwordsDontMatchErrorMessage.isDisplayed());
        } else {
            assertTrue(!forgotPasswordPage.recoverCredentialResetPwd.isEnabled());
            forgotPasswordPage.recoverCredentialCreateNewPwdHideShowText.click();
            forgotPasswordPage.recoverCredentialCreateNewPwdUnsecured.sendKeys("test");
            forgotPasswordPage.recoverCredentialConfirmNewPwdHideShowText.click();
            forgotPasswordPage.recoverCredentialConfirmNewPwdUnsecured.sendKeys("test1");
            assertTrue(forgotPasswordPage.passwordsDontMatchErrorMessage.isDisplayed());
            forgotPasswordPage.recoverCredentialConfirmNewPwdUnsecured.sendKeys(Keys.RETURN);
            assertTrue(!forgotPasswordPage.recoverCredentialResetPwd.isEnabled());
            forgotPasswordPage.recoverCredentialConfirmNewPwdUnsecured.sendKeys(Keys.BACK_SPACE);
            forgotPasswordPage.recoverCredentialConfirmNewPwdUnsecured.sendKeys(Keys.RETURN);
            assertTrue(forgotPasswordPage.recoverCredentialResetPwd.isEnabled());
        }
    }

    private void redirectLoginScreen() {
        forgotPasswordPage.recoverCredentialBackBtn.click();
        forgotPasswordPage.forgotPasswordBackButton.click();
    }

}
