package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Recover Credentials']")
    @iOSXCUITFindBy(id = "text_recover_credential_request_title")
    public WebElement forgotPasswordTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Your access code has been sent to the email on file. If you have not received the email, please check your spam folder. You may resend the email if you still have not received it.']")
    @iOSXCUITFindBy(id = "text_recover_credential_subtitle")
    public WebElement forgotPasswordValidationScreenDescription;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='A 6-digit access code that’s required to reset your password will be sent to the email associated with your account.']")
    @iOSXCUITFindBy(id = "text_recover_credential_request_subtitle")
    public WebElement forgotPasswordDescription;

    @AndroidFindBy(xpath = ".//android.widget.EditText")
    @iOSXCUITFindBy(id = "textfield_recover_credential_request_email")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Username']")
    @iOSXCUITFindBy(id = "textfield_recover_credential_request_email")
    public WebElement userNameEditTextHint;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='REQUEST ACCESS CODE']")
    @iOSXCUITFindBy(id = "textfield_recover_credential_request_email")
    public WebElement requestAccessCodeText;

    @AndroidFindBy(xpath = ".//android.view.View[@index='4']")
    @iOSXCUITFindBy(id = "button_recover_credential_request_submit")
    public WebElement requestAccessCodeButton;

    @AndroidFindBy(accessibility = "navBack")
    @iOSXCUITFindBy(id = "button_back")
    public WebElement forgotPasswordBackButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Error']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Error\" AND name == \"Error\" AND value == \"Error\"")
    public WebElement errorTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OK']")
    @iOSXCUITFindBy(id = "OK")
    public WebElement errorDialogOk;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Unable to send password reset.']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Unable to send password reset.\"")
    public WebElement userNotExistsErrorMsg;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Network Error']")
    public WebElement noNetworkErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Please check your network connection and try again.']")
    public WebElement noNetworkErrorMsg;

    // Forgot password access code validation screen elements

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Recover Credentials']")
    @iOSXCUITFindBy(id = "text_recover_credential_title")
    public WebElement recoverCredentialTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Your access code has been sent to the email on file. If you have not received the email, please check your spam folder. You may resend the email if you still have not received it.']")
    @iOSXCUITFindBy(id = "text_recover_credential_subtitle")
    public WebElement recoverCredentialSubTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Username']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_username\" AND type == \"XCUIElementTypeStaticText\"")
    public WebElement recoverCredentialUserName;

    @AndroidFindBy(accessibility = "userNameEditText")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_username\" AND type == \"XCUIElementTypeTextField\"")
    public WebElement recoverCredentialUserNameEditText;

    @AndroidFindBy(id = "accessCodeEditText")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_accesscode\" AND type == \"XCUIElementTypeTextField\"")
    public WebElement recoverCredentialAccessCodeEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Access Code']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_accesscode\" AND type == \"XCUIElementTypeTextField\"")
    public WebElement recoverCredentialAccessCode;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Resend']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_accesscode\" AND type == \"XCUIElementTypeButton\"")
    public WebElement recoverCredentialResend;

    @AndroidFindBy(id = "resendButton")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_accesscode\" AND type == \"XCUIElementTypeButton\"")
    public WebElement recoverCredentialResendBtn;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Password Requirements (Minimum 8 Characters)']")
    @iOSXCUITFindBy(id = "text_recover_credential_password_requirements")
    public WebElement recoverCredentialPwdRequirements;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='At least 1 of each:']")
    @iOSXCUITFindBy(id = "text_recover_credential_password_requirements")
    public WebElement recoverCredentialAtLeastOneOfEach;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='• Upper case letter• Lower case letter']")
    @iOSXCUITFindBy(id = "text_recover_credential_password_requirements")
    public WebElement recoverCredentialUpperLowerCase;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='• Number• Special character(#?!@\\$%^&*-)']")
    @iOSXCUITFindBy(id = "text_recover_credential_password_requirements")
    public WebElement recoverCredentialNumberSpecialCharacters;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Create New Password']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_new_password\" AND type == \"XCUIElementTypeSecureTextField\"")
    public WebElement recoverCredentialCreateNewPwd;

    @AndroidFindBy(id = "newPwdEditText")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_new_password\" AND type == \"XCUIElementTypeSecureTextField\"")
    public WebElement recoverCredentialCreateNewPwdEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Confirm New Password']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_confirm_password\" AND type == \"XCUIElementTypeSecureTextField\"")
    public WebElement recoverCredentialConfirmNewPwd;

    @AndroidFindBy(id = "confirmPwdEditText")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_confirm_password\" AND type == \"XCUIElementTypeSecureTextField\"")
    public WebElement recoverCredentialConfirmNewPwdEditText;

    @AndroidFindBy(xpath = ".//android.view.View[@content-desc=\"resetPwdButton\"]")
    @iOSXCUITFindBy(id = "button_recover_credential_submit")
    public WebElement recoverCredentialResetPwd;

    @AndroidFindBy(id = "resetPwdButton")
    @iOSXCUITFindBy(id = "button_recover_credential_submit")
    public WebElement recoverCredentialResetPwdBtn;


    @AndroidFindBy(id = "backButton")
    @iOSXCUITFindBy(id = "button_back")
    public WebElement recoverCredentialBackBtn;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Please enter valid 6 digits access code.']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Please enter valid 6 digits access code.\" AND type == \"XCUIElementTypeStaticText\"")
    public WebElement invalidAccessCodeErrorMessage;


    @AndroidFindBy(xpath = ".//*[contains(@text,'match')]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Passwords don't match.\" AND type == \"XCUIElementTypeStaticText\"")
    public WebElement passwordsDontMatchErrorMessage;


//    @AndroidFindBy(id = "") // ToDo - add android locator
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_new_password\" AND type == \"XCUIElementTypeButton\"")
    public WebElement recoverCredentialCreateNewPwdHideShowText;


//    @AndroidFindBy(id = "") // ToDo - add android locator
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_confirm_password\" AND type == \"XCUIElementTypeButton\"")
    public WebElement recoverCredentialConfirmNewPwdHideShowText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Create New Password']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_new_password\" AND type == \"XCUIElementTypeTextField\"")
    public WebElement recoverCredentialCreateNewPwdUnsecured;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Confirm New Password']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"textfield_recover_credential_confirm_password\" AND type == \"XCUIElementTypeTextField\"")
    public WebElement recoverCredentialConfirmNewPwdUnsecured;

}
