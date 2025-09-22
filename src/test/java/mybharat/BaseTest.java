package mybharat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.qameta.allure.Attachment;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    protected static AndroidDriver driver;
    protected static AppiumDriverLocalService service;
    protected static Properties config;
    // Add your reporting utility here (e.g., ExtentReports)
    // protected ExtentReports extent;

    @BeforeSuite
    public void globalSetUp() throws MalformedURLException {
        loadConfig();
        if (service == null) {
            String appiumMainJs = getConfig("APPIUM_MAIN_JS");
            File appiumMainJsFile = new File(appiumMainJs);
            if (!appiumMainJsFile.exists()) {
                throw new RuntimeException("The APPIUM_MAIN_JS path does not exist: " + appiumMainJs);
            }
            service = new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                    .withAppiumJS(appiumMainJsFile)
                    .withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();
        }
        if (driver == null) {
            String deviceName = getConfig("DEVICE_NAME");
            String appPath = getConfig("APP_PATH");
            String chromeDriverPath = getConfig("CHROMEDRIVER_PATH");
            String version = getConfig("VERSION");
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(deviceName);
            options.setApp(appPath);
            options.setPlatformName("Android");
            options.setPlatformVersion(version);
            options.setNoReset(false);
            options.setAutomationName("UiAutomator2");
            options.setAppPackage("com.gov.mybharat");
            options.setAppActivity("com.gov.mybharat.MainActivity");
            options.setNewCommandTimeout(Duration.ofSeconds(300));
            options.setAdbExecTimeout(Duration.ofMillis(120000));
            if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
                File chromeDriverFile = new File(chromeDriverPath);
                if (!chromeDriverFile.exists()) {
                    throw new RuntimeException("The CHROMEDRIVER_PATH does not exist: " + chromeDriverPath);
                }
                options.setChromedriverExecutable(chromeDriverPath);
            }
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void ensureDriverInitialized() throws MalformedURLException {
        if (driver == null) {
            System.out.println("[BaseTest] Driver was null before test, re-initializing.");
            globalSetUp();
        } else {
            System.out.println("[BaseTest] Driver is already initialized.");
        }
    }

//    @AfterSuite
//    public void globalTearDown() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//        if (service != null) {
//            service.stop();
//            service = null;
//        }
//    }

    
    
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        if (driver == null) return null;
        try {
            File srcFile = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            return FileUtils.readFileToByteArray(srcFile);
        } catch (Exception e) {
            return null;
        }
    }

    private void loadConfig() {
        config = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            config.load(fis);
        } catch (Exception ignored) {}
    }

    protected static String getConfig(String key) {
        String env = System.getenv(key);
        if (env != null && !env.isEmpty()) return env;
        if (config != null && config.getProperty(key) != null) return config.getProperty(key);
        throw new RuntimeException("Missing required config: " + key);
    }
}