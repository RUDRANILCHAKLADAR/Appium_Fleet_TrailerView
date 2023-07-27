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
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Username or email\"")
    public WebElement userNameEditTextHint;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='REQUEST ACCESS CODE']")
    @iOSXCUITFindBy(id = "button_recover_credential_request_submit")
    public WebElement requestAccessCodeText;

    @AndroidFindBy(xpath = ".//android.view.View[@index='4']")
    @iOSXCUITFindBy(id = "button_recover_credential_request_submit")
    public WebElement requestAccessCodeButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"back\"]")
    @iOSXCUITFindBy(id = "button_back")
    public WebElement backButton;

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
}
