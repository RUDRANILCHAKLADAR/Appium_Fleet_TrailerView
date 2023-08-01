package testscenarios;

import core.BaseTest;
import core.TestUtility;
import objects.VehicleListPage;
import org.testng.annotations.Test;

public class VehicleListScreenTest extends BaseTest {

    private VehicleListPage vehicleListPage;
    @Override
    protected void init() {
        vehicleListPage = new VehicleListPage(getDriver());
        TestUtility.logInUser(vehicleListPage, "atistagetest", "Password1");
    }

    @Override
    protected void deInit() {
        logOutUser();
    }

    @Test(priority = 1)
    public void testValidateUi() {
        disMissLocationPermission(vehicleListPage);
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
    }

    public void logOutUser() {
        TestUtility.waitForVisibility(vehicleListPage.homeMoreButton, getDriver());
        TestUtility.logOutUser(vehicleListPage, getDriver());
    }


}
