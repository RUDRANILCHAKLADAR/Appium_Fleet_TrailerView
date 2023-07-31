package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import objects.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class TestUtility {

    public static void waitFor(int waitTime, AppiumDriver driver) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
//            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
//            System.out.println("Element is not visible: " + element);
            throw e;
        }
    }

    public static void waitForVisibility(WebElement element, AppiumDriver driver) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element is not visible: " + element);
            throw e;
        }
    }

    public static boolean waitForInvisibility(WebElement element, AppiumDriver driver) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element is visible: " + element);
            throw e;
        }
        return true;
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void clickElement(WebElement element, AppiumDriver driver) {
        waitForVisibility(element, driver);
        element.click();
    }

    public static void sendKeys(WebElement element, String txt, AppiumDriver driver) {
        waitForVisibility(element, driver);
        element.sendKeys(txt);
    }

    private boolean existsElement(String id, AppiumDriver driver) {
        try {
            driver.findElement(By.id(id));
        } catch (Exception e) {
            System.out.println("id is not present ");
            return false;
        }

        return true;
    }

    public static void turnOffInternet(AppiumDriver driver){
        ( (AndroidDriver)driver).setConnection(new ConnectionStateBuilder().
                withWiFiDisabled().withDataDisabled().build());
    }

    public static void turnOnInternet(AppiumDriver driver){
        ( (AndroidDriver)driver).setConnection(new ConnectionStateBuilder().
                withWiFiEnabled().withDataEnabled().build());
    }


    public static void pullToRefresh(AppiumDriver driver) {
        int deviceWidth = driver.manage().window().getSize().getWidth();
        int deviceHeight = driver.manage().window().getSize().getHeight();
        int midX = deviceWidth / 2;
        int midY = deviceHeight / 2;
        int bottomEdge = (int)((float)deviceHeight * 0.85F);
        (new TouchAction((PerformsTouchActions) driver)).
                press(PointOption.point(midX, midY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10L))).moveTo(PointOption.point(midX, bottomEdge)).release().perform();
    }

    public static void scrollToEnd(AppiumDriver driver) {
        // Get the size of the device screen
        Dimension size = driver.manage().window().getSize();

        // Define the start and end coordinates for the scroll gesture
        int startX = (int) (size.width * 0.5);
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        // Perform the scroll gesture from start to end coordinates
        new TouchAction((PerformsTouchActions)(AppiumDriver) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release().perform();
    }

    public static void logInUser(SignInPage signInPage) {
        signInPage.userNameEditText.clear();
        signInPage.passWordEditText.clear();
        signInPage.userNameEditText.sendKeys("tv_rs_27");
        signInPage.passWordEditText.sendKeys("Spireon@1234");

        signInPage.logInButton.click();
    }

    public static void logOutUser(SignInPage signInPage, AppiumDriver driver) {
        signInPage.homeMoreButton.click();
        waitForVisibility(signInPage.logOutButton, driver);
        signInPage.logOutButton.click();
        waitForVisibility(signInPage.logOutConfirm, driver);
        signInPage.logOutConfirm.click();
        waitForVisibility(signInPage.signInTitle, driver);
    }

}