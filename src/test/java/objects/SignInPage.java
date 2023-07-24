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
    @iOSXCUITFindBy(id = "textfield_username")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.EditText[2]")
    @iOSXCUITFindBy(id = "textfield_password")
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

}
