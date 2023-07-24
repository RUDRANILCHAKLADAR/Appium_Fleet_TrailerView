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
}
