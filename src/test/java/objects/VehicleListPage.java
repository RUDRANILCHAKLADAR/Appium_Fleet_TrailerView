package objects;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VehicleListPage extends BasePage {
    public VehicleListPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = ".//android.widget.TextView[@text='Assets']")
    public WebElement assetListTitle;

    @AndroidFindBy(accessibility = "search")
    public WebElement searchIcon;

    @AndroidFindBy(accessibility = "More Icon")
    public WebElement moreIcon;

    @AndroidFindBy(accessibility = "assetListInfo")
    public WebElement listInfo;

    @AndroidFindBy(accessibility = "assetList")
    public List<WebElement> assetList;


}
