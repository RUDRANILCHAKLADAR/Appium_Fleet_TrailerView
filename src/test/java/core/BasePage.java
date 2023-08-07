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


    /* iOS System Location Permission dialog elements*/
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Allow “TrailerView STG” to use your location?\" AND name == \"Allow “TrailerView STG” to use your location?\" AND type == \"XCUIElementTypeAlert\"")
    public WebElement iOSLocationPermissionAlert;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    @iOSXCUITFindBy(id = "Allow While Using App")
    public WebElement locationPermission;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")
//    @iOSXCUITFindBy(accessibility = "Allow While Using App")
    public WebElement locationAccuracy;

    @AndroidFindBy(xpath = ".//android.widget.EditText[1]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'textfield_username' AND type == 'XCUIElementTypeTextField'")
    public WebElement userNameEditText;

    @AndroidFindBy(xpath = ".//android.widget.EditText[2]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'textfield_password' AND type == 'XCUIElementTypeSecureTextField'")
    public WebElement passWordEditText;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='LOGIN']")
    @iOSXCUITFindBy(id = "button_signin")
    public WebElement logInButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='More Icon']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'ic_dots' AND type == 'XCUIElementTypeButton'")
    public WebElement homeMoreButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Logout Icon\"]")
//    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Logout' AND type == 'XCUIElementTypeButton'")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Logout\"]")
    public WebElement logOutButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CONFIRM\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Logout' AND type == 'XCUIElementTypeButton'")
    public WebElement logOutConfirm;

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Sign In']")
    @iOSXCUITFindBy(id = "text_signin_title")
    public WebElement signInTitle;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Logout' AND type == 'XCUIElementTypeAlert'")
    public WebElement logoutAlert;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Are you sure you want to logout?'")
    public WebElement logoutAlertMessage;
    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Forgot Password?']")
    public WebElement forgotPwd;

}
