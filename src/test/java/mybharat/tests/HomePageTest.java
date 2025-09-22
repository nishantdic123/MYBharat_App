package mybharat.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import mybharat.BaseTest;
import mybharat.pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test(priority = 1 ,  description = "Move to Experiential Learning Section and Swipe Down")
   
    public void homePage_experientialLearningListing() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
       
        homePage.experientialLearningSection();
        Thread.sleep(2000);
        homePage.swipeUpOnScreen();
        homePage.swipeUpOnScreen();
        homePage.swipeUpOnScreen();
        homePage.swipeUpOnScreen();
        homePage.swipeDownOnScreen();
        homePage.swipeDownOnScreen();
        homePage.swipeDownOnScreen();
        homePage.swipeDownOnScreen();
       }
    
    
    
    
    

   
}