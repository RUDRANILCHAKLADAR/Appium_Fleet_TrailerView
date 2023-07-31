package testscenarios;

import core.BaseTest;
import core.TestUtility;
import objects.SignInPage;
import objects.VehicleListPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class VehicleScreenTest extends BaseTest {

    private VehicleListPage vehicleListPage;
    @Override
    protected void initPageObject() {
        vehicleListPage = new VehicleListPage(getDriver());
        TestUtility.logInUser(vehicleListPage, "atistagetest", "Password1");
    }

    @Test(priority = 1)
    public void testValidateUi() {
        disMissLocationPermission(vehicleListPage);
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
    }

    @AfterClass
    public void logOutUser() {
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
        TestUtility.logOutUser(vehicleListPage, getDriver());
    }


}
