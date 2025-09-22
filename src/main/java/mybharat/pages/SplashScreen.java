package mybharat.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import mybharat.CommonFunctions;

public class SplashScreen extends CommonFunctions {
   
    private By splashScreen1Heading = AppiumBy.androidUIAutomator("new UiSelector().text(\"Register as Youth\")");
    private By splashScreen1ContentText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Shape your future with MY Bharat. Register as a Youth and join now\")");
    private By splashScreen1Next = AppiumBy.androidUIAutomator("new UiSelector().description(\"\uE61F\")");
    private By splashScreen2Heading = AppiumBy.androidUIAutomator("new UiSelector().text(\"Explore Opportunities\")");
    private By splashScreen2ContentText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Explore the list of opportunities as per your skills and location\")");
    private By splashScreen3Heading = AppiumBy.androidUIAutomator("new UiSelector().text(\"Get Rewarded\")");
    private By splashScreen3ContentText = AppiumBy.androidUIAutomator("new UiSelector().text(\"On successful completion your efforts are rewarded with certificates\")");
    private By getStarted = AppiumBy.androidUIAutomator("new UiSelector().text(\"Get Started\")");

    public SplashScreen(AndroidDriver driver) {
        super(driver);
    }
    
    
    public String getHeadingText1() {
        try {
            return driver.findElement(splashScreen1Heading).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen1 heading not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public String getContentText1() {
        try {
            return driver.findElement(splashScreen1ContentText).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen1 content not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public void clickNext() {
        try {
            waitForElementVisible(splashScreen1Next, 10);
            driver.findElement(splashScreen1Next).click();
        } catch (Exception e) {
            System.out.println("[DEBUG] 'Next' button not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }
    public String getHeadingText2() {
        try {
            waitForElementVisible(splashScreen2Heading, 10);
            return driver.findElement(splashScreen2Heading).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen2 heading not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public String getContentText2() {
        try {
            waitForElementVisible(splashScreen2ContentText, 10);
            return driver.findElement(splashScreen2ContentText).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen2 content not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    // Call this to swipe left on the screen
    public void swipeLeft() {
        swipeLeftOnScreen();
    }
    
    
    public String getHeadingText3() {
        try {
            waitForElementVisible(splashScreen3Heading, 10);
            return driver.findElement(splashScreen3Heading).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen3 heading not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public String getContentText3() {
        try {
            waitForElementVisible(splashScreen3ContentText, 10);
            return driver.findElement(splashScreen3ContentText).getText();
        } catch (Exception e) {
            System.out.println("[DEBUG] SplashScreen3 content not found. Dumping page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public void clickGetStarted() {
        driver.findElement(getStarted).click();
    }
    
    
    
    
    
}