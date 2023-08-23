package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VehicleListPage extends BasePage {
    public VehicleListPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Assets']")
    @iOSXCUITFindBy(id = "home_nav_title")
    public WebElement assetListTitle;

    @AndroidFindBy(accessibility = "search")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Search Assets, Serials, VINS\" AND type == \"XCUIElementTypeSearchField\"")
    public WebElement searchIcon;

    @AndroidFindBy(accessibility = "More Icon")
    @iOSXCUITFindBy(id = "ic_dots")
    public WebElement moreIcon;

    @AndroidFindBy(xpath = "//*[contains(@text,'Showing')]")
    @iOSXCUITFindBy(iOSNsPredicate = "label BEGINSWITH 'Showing' AND type == 'XCUIElementTypeStaticText'")
    public WebElement listInfo;

    @AndroidFindBy(accessibility = "assetList")
    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeCollectionView\"")
    public List<WebElement> assetList;


}
