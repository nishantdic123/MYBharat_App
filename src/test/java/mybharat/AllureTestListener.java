package mybharat;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class AllureTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest base = (BaseTest) testClass;
            try {
                byte[] screenshot = base.saveScreenshotPNG();
                if (screenshot != null) {
                    Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
                }
            } catch (Exception ignored) {}
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
