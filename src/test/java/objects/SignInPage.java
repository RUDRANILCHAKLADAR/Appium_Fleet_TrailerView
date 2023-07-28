package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SignInPage extends BasePage {
    public SignInPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Sign In']")
    @iOSXCUITFindBy(id = "text_signin_title")
    public WebElement signInTitle;

    @AndroidFindBy(xpath = ".//android.widget.EditText[1]")
//    @iOSXCUITFindBy(id = "textfield_username")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'textfield_username' AND type == 'XCUIElementTypeTextField'")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.EditText[2]")
//    @iOSXCUITFindBy(id = "textfield_password")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'textfield_password' AND type == 'XCUIElementTypeSecureTextField'")
    public WebElement passWordEditText;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='0']")
    @iOSXCUITFindBy(id = "textfield_password")
    public WebElement pwdEye;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='1']")
    @iOSXCUITFindBy(id = "button_signin")
    public WebElement logInButton;

    @AndroidFindBy(xpath = ".//android.widget.Button[@text='LOGIN']")
    @iOSXCUITFindBy(xpath = "**/XCUIElementTypeButton[`label == \"Login\"`]")
    public WebElement logInButtonTxt;

    @AndroidFindBy(xpath = ".//android.widget.Button[@text='Forgot Password?']")
    @iOSXCUITFindBy(id = "button_signin_forgot_password")
    public WebElement forgotPwd;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    public WebElement permission_access;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Invalid Credentials']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Error\" AND name == \"Error\" AND type == \"XCUIElementTypeAlert\"")
    public WebElement invalidCrednetialErrorTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='The credentials entered do not match our records. Verify your username and password.']")
    @iOSXCUITFindBy(id = "The credentials entered do not match our records. Verify your username and password.")
    public WebElement invalidCrednetialErrorMsg;

    @AndroidFindBy(xpath = ".//android.widget.Button[@index='1']")
    @iOSXCUITFindBy(id = "OK")
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
    @iOSXCUITFindBy(id = "home_nav_title")
    public WebElement assetListScreenTitle;
}
