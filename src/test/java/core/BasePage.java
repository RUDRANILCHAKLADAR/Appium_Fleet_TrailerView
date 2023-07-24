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
}
