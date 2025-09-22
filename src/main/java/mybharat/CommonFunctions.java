package mybharat;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.By;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class CommonFunctions {
	protected AndroidDriver driver;

	public CommonFunctions(AndroidDriver driver) {
		this.driver = driver;
	}

	// Example: Long press action
	public void longPress(WebElement element, int durationMs) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", durationMs));
	}

	// Example: Scroll to end
	public void scrollToEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	// Example: Swipe action
	public void swipe(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}

	// Example: Get formatted amount from string (e.g., "$123.45" -> 123.45)
	public Double getFormattedAmount(String amount) {
		return Double.parseDouble(amount.replaceAll("[^0-9.]", ""));
	}

	/**
	 * Gets text from an element by locator.
	 */
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	/**
	 * Checks if an element is displayed.
	 */
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Checks if an element is enabled.
	 */
	public boolean isEnabled(By locator) {
		try {
			return driver.findElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Checks if an element is selected.
	 */
	public boolean isSelected(By locator) {
		try {
			return driver.findElement(locator).isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Waits for an element to be present.
	 */
	public void waitForElementPresent(By locator, int timeoutSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Waits for an element to be visible.
	 */
	public void waitForElementVisible(By locator, int timeoutSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits for an element to be clickable.
	 */
	public void waitForElementClickable(By locator, int timeoutSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Tap on an element by locator.
	 */
	public void tap(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
	}

	// Swipe the screen to the left (W3C Actions API)
	public void swipeLeftOnScreen() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.width * 0.8);
		int endX = (int) (size.width * 0.2);
		int y = size.height / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}

	/**
	 * Swipe the screen to the right (W3C Actions API)
	 */
	public void swipeRightOnScreen() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.width * 0.2);
		int endX = (int) (size.width * 0.8);
		int y = size.height / 2;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}

	/**
	 * Swipe the screen up (W3C Actions API)
	 */
	public void swipeUpOnScreen() {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}

	/**
	 * Swipe the screen down (W3C Actions API)
	 */
	public void swipeDownOnScreen() {
		Dimension size = driver.manage().window().getSize();
		int x = size.width / 2;
		int startY = (int) (size.height * 0.2);
		int endY = (int) (size.height * 0.8);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}

	/**
	 * Scrolls to an element by locator.
	 */
	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", "down", "percent", 1.0));
	}

	/**
	 * Takes a screenshot and saves to the given file path.
	 */
	public void takeScreenshot(String filePath) {
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(filePath));
		} catch (IOException e) {
			throw new RuntimeException("Failed to save screenshot: " + e.getMessage());
		}
	}

	/**
	 * Hides the keyboard if visible.
	 */
	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			// Ignore if keyboard is not present
		}
	}

	/**
	 * Gets an attribute value from an element by locator.
	 */
	public String getAttribute(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute);
	}

	/**
	 * Clears a text field by locator.
	 */
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	/**
	 * Prints current activity, package, and saves a screenshot for debugging.
	 * 
	 * @param screenshotName The filename for the screenshot (e.g.,
	 *                       "debug_screen.png").
	 */
	public void debugState(String screenshotName) {
		try {
			System.out.println("Current activity: " + driver.currentActivity());
			System.out.println("Current package: " + driver.getCurrentPackage());
			File screenshot = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotName));
			System.out.println("Screenshot saved as " + screenshotName);
		} catch (Exception e) {
			System.out.println("Debug info failed: " + e.getMessage());
		}
	}

	/**
	 * Returns true if the element is present in the DOM, false otherwise.
	 */
	public boolean isElementPresent(By locator) {
		try {
			return driver.findElements(locator).size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Saves the current page source to a file for debugging.
	 */
	public void printPageSource(String filename) {
		try {
			String pageSource = driver.getPageSource();
			java.nio.file.Files.write(java.nio.file.Paths.get(filename), pageSource.getBytes());
			System.out.println("[DEBUG] Page source saved to " + filename);
		} catch (Exception e) {
			System.out.println("[DEBUG] Failed to save page source: " + e.getMessage());
		}
	}

}