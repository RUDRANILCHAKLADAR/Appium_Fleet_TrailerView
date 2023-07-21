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

    @AndroidFindBy(xpath = ".//android.widget.Button[@text='Forgot Password?']")
    public WebElement forgotPwd;

}
