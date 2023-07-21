package testscenarios;

import core.BaseTest;
import core.TestUtility;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import objects.SignInPage;
import org.testng.annotations.Test;

public class SignInScreenTest extends BaseTest {

    private SignInPage signInPage;

    @Override
    protected void initPageObject() {
        signInPage = new SignInPage(getDriver());
    }

    @Test(priority = 1)
    public void testInvalidDialog(){
//        TestUtility.waitForVisibility(signInPage.signInTitle, getDriver());
        signInPage.userNameEditText.isDisplayed();
        TestUtility.waitForVisibility(signInPage.userNameEditText, getDriver());
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        signInPage.userNameEditText.isDisplayed();
        signInPage.passWordEditText.sendKeys("Spireon@123");
        signInPage.pwdEye.isDisplayed();
        signInPage.pwdEye.click();
        signInPage.logInButton.isDisplayed();
//        signInPage.logInButton.click();
//        Activity activity = new Activity("com.spireon.atidriver.stage", "com.spireon.atidriver.cognito.forgotpassword.ForgotPasswordActivity");
//        activity.setAppWaitPackage("com.spireon.atidriver");
//        activity.setAppWaitActivity("com.spireon.atidriver.cognito.forgotpassword.ForgotPasswordActivity");
//        activity.setStopApp(false);
//        ((AndroidDriver)getDriver()).startActivity(activity);
//        TestUtility.waitFor(60, getDriver());

    }


}
