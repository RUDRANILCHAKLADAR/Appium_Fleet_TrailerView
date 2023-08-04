package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {


    public BasePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    @iOSXCUITFindBy(accessibility = "Allow While Using App")
    public WebElement locationPermission;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")
//    @iOSXCUITFindBy(accessibility = "Allow While Using App")
    public WebElement locationAccuracy;

    @AndroidFindBy(xpath = ".//android.widget.EditText[1]")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.EditText[2]")
    public WebElement passWordEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='LOGIN']")
    public WebElement logInButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='More Icon']")
    public WebElement homeMoreButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Logout Icon\"]")
    public WebElement logOutButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CONFIRM\"]")
    public WebElement logOutConfirm;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Sign In']")
    public WebElement signInTitle;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Forgot Password?']")
    public WebElement forgotPwd;

}
