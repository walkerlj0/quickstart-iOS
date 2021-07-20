package tests;


//import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
//import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.List;

public class BasicTest {

    private static final String APP = "/Users/lindsaywalker/Documents/Example_Tests/Android.SauceLabs.Mobile.Sample.app.2.7.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private IOSDriver driver;
//    private static ThreadLocal<IOSDriver> iosDriver = new ThreadLocal<IOSDriver>();

    String usernameID = "test-Username";
    String passwordID = "test-Password";
    String submitButtonID = "test-LOGIN";
    By ProductTitle = By.xpath("//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]");


    @BeforeMethod
       public void setup(Method method) throws MalformedURLException {
        System.out.println("Local iOS Native - BeforeMethod hook");
//        String username = System.getenv("SAUCE_USERNAME");
//        String accesskey = System.getenv("SAUCE_ACCESS_KEY");
//        String sauceUrl = "@ondemand.us-west-1.saucelabs.com:443";
//        String SAUCE_REMOTE_URL = "https://" + username + ":" + accesskey + sauceUrl +"/wd/hub";
        String appName = "/Users/lindsaywalker/Git/Example_Tests/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.zip"; // updated for local

//        String methodName = method.getName();
//        URL url = new URL(SAUCE_REMOTE_URL);
        URL url = new URL("http://localhost:4723/wd/hub"); //added for local

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","iPhone 12");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion","14.4");
        capabilities.setCapability("automationName", "XCuiTest");
        capabilities.setCapability("app", appName); //modified for local
//        capabilities.setCapability("name", methodName);

        try {
//           new IOSDriver(url, capabilities);
            driver = new IOSDriver(url, capabilities);
        } catch (Exception e) {
            System.out.println("*** Problem creating the iOS driver " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @AfterMethod
//     public void teardown(ITestResult result) {
//        System.out.println("Sauce - AfterMethod hook");
      //  ((JavascriptExecutor)getiosDriver()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
//        getiosDriver().quit();
    public void tearDown() {
//        if (getiosDriver() != null) {
//            getiosDriver().quit();
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void loginToSwagLabsTestValid() {
        System.out.println("Sauce - Start loginToSwagLabsTestValid test");
        login("standard_user", "secret_sauce");

        // Verification
        Assert.assertTrue(isOnProductsPage());

    }

//    @Test
    public void loginToSwagLabsTestValid2() {
        System.out.println("Sauce - Start loginToSwagLabsTestValid2 test");
        login("standard_user", "secret_sauce");

        // Verification
        Assert.assertTrue(isOnProductsPage());

    }

    public void login(String user, String pass){
//        IOSDriver driver = getiosDriver();

        WebElement usernameEdit = (WebElement) driver.findElementByAccessibilityId(usernameID);

        usernameEdit.click();
        usernameEdit.sendKeys(user);

        WebElement passwordEdit = (WebElement) driver.findElementByAccessibilityId(passwordID);
        passwordEdit.click();
        passwordEdit.sendKeys(pass);

        WebElement submitButton = (WebElement) driver.findElementByAccessibilityId(submitButtonID);
        submitButton.click();
    }

    public boolean isOnProductsPage() {
//        IOSDriver driver = getiosDriver();
        return driver.findElement(ProductTitle).isDisplayed();
    }

}