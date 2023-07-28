package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Recover Credentials']")
    public WebElement forgotPasswordTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Your access code has been sent to the email on file. If you have not received the email, please check your spam folder. You may resend the email if you still have not received it.']")
    public WebElement forgotPasswordValidationScreenDescription;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='A 6-digit access code that’s required to reset your password will be sent to the email associated with your account.']")
    public WebElement forgotPasswordDescription;

    @AndroidFindBy(xpath = ".//android.widget.EditText")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Username']")
    public WebElement userNameEditTextHint;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='REQUEST ACCESS CODE']")
    public WebElement requestAccessCodeText;

    @AndroidFindBy(xpath = ".//android.view.View[@index='4']")
    public WebElement requestAccessCodeButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"back\"]")
    public WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Error']")
    public WebElement errorTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OK']")
    public WebElement errorDialogOk;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Unable to send password reset.']")
    public WebElement userNotExistsErrorMsg;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Network Error']")
    public WebElement noNetworkErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Please check your network connection and try again.']")
    public WebElement noNetworkErrorMsg;

    // Forgot password access code validation screen elements

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Recover Credentials']")
    public WebElement recoverCredentialTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Your access code has been sent to the email on file. If you have not received the email, please check your spam folder. You may resend the email if you still have not received it.']")
    public WebElement recoverCredentialSubTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Username']")
    public WebElement recoverCredentialUserName;

    @AndroidFindBy(id = "userNameEditText")
    public WebElement recoverCredentialUserNameEditText;

    @AndroidFindBy(id = "accessCodeEditText")
    public WebElement recoverCredentialAccessCodeEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Access Code']")
    public WebElement recoverCredentialAccessCode;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Resend']")
    public WebElement recoverCredentialResend;

    @AndroidFindBy(id = "resendButton")
    public WebElement recoverCredentialResendBtn;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Password Requirements (Minimum 8 Characters)']")
    public WebElement recoverCredentialPwdRequirements;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='At least 1 of each:']")
    public WebElement recoverCredentialAtLeastOneOfEach;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='•\t\tUpper case•\t\tLower case']")
    public WebElement recoverCredentialUpperLowerCase;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='•\t\tNumber•\t\tSpecial character(#?!@\\$%^&*-)']")
    public WebElement recoverCredentialNumberSpecialCharacters;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Create New Password']")
    public WebElement recoverCredentialCreateNewPwd;

    @AndroidFindBy(id = "newPwdEditText")
    public WebElement recoverCredentialCreateNewPwdEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Confirm New Password']")
    public WebElement recoverCredentialConfirmNewPwd;

    @AndroidFindBy(id = "confirmPwdEditText")
    public WebElement recoverCredentialConfirmNewPwdEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='RESET PASSWORD']")
    public WebElement recoverCredentialResetPwd;

    @AndroidFindBy(id = "resetPwdButton")
    public WebElement recoverCredentialResetPwdBtn;




}
