package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SignInPage extends BasePage {
    public SignInPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Sign In']")
    public WebElement signInTitle;

    @AndroidFindBy(xpath = ".//android.widget.EditText[1]")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.EditText[2]")
    public WebElement passWordEditText;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='0']")
    public WebElement pwdEye;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='1']")
    public WebElement logInButton;

    @AndroidFindBy(xpath = ".//android.widget.Button[@text='LOGIN']")
    public WebElement logInButtonTxt;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Forgot Password?']")
    public WebElement forgotPwd;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    public WebElement permission_access;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Invalid Credentials']")
    public WebElement invalidCrednetialErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='The credentials entered do not match our records. Verify your username and password.']")
    public WebElement invalidCrednetialErrorMsg;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='1']")
    public WebElement dialogOkButton;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Network Error']")
    public WebElement noNetworkErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Please check your network connection and try again.']")
    public WebElement noNetworkErrorMsg;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Error']")
    public WebElement otherErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='The user must reset their password']")
    public WebElement otherErrorMsg;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Assets']")
    public WebElement assetListScreenTitle;
}
