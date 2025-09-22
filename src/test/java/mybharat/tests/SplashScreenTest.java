package mybharat.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import mybharat.BaseTest;
import mybharat.pages.SplashScreen;

public class SplashScreenTest extends BaseTest {

	@Test(priority = 1, description = "Verify complete splash screen flow: headings, navigation, and Get Started")
	public void testSplashScreenFlow() {
		SplashScreen splash = new SplashScreen(driver);
		SoftAssert softAssert = new SoftAssert();

		// 1. First splash screen
		softAssert.assertEquals(splash.getHeadingText1(), "Register as Youth", "Heading text mismatch (screen 1)");
		softAssert.assertEquals(splash.getContentText1(),
				"Shape your future with MY Bharat. Register as a Youth and join now", "Content text mismatch (screen 1)");
		splash.clickNext();

		// 2. Second splash screen
		softAssert.assertEquals(splash.getHeadingText2(), "Explore Opportunities", "Heading text mismatch (screen 2)");
		softAssert.assertEquals(splash.getContentText2(),
				"Explore the list of opportunities as per your skills and location", "Content text mismatch (screen 2)");
		splash.swipeLeft();

		// 3. Third splash screen
		softAssert.assertEquals(splash.getHeadingText3(), "Get Rewarded", "Heading text mismatch (screen 3)");
		softAssert.assertEquals(splash.getContentText3(),
				"On successful completion your efforts are rewarded with certificates", "Content text mismatch (screen 3)");
		splash.clickGetStarted();

		softAssert.assertAll();
	}

}