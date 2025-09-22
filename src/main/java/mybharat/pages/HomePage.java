package mybharat.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class HomePage extends mybharat.CommonFunctions {

	protected By experientialLearningSection = AppiumBy
			.xpath("//android.view.ViewGroup[@content-desc=\"Experiential Learning\"]/android.widget.ImageView\n" + "");

	protected By upcoming_ViewAll = AppiumBy
			.xpath("	\n"
					+ "//android.widget.TextView[@text=\"View All (20)\"]");
	
	

	protected By targetViewGroup = AppiumBy.androidUIAutomator(
			"new UiSelector().className(\"android.view.ViewGroup\").instance(34)");

	public HomePage(AndroidDriver driver) {
		super(driver);
	}

	public void experientialLearningSection() {
		driver.findElement(experientialLearningSection).click();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}