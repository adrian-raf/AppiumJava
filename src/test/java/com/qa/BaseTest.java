package com.qa;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;



    @BeforeTest
    public void beforeTest() throws Exception {
        try {
            props = new Properties();
            String propFileName = "config.properties";

            // Menggunakan getResourceAsStream untuk membaca file properties
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream == null) {
                throw new RuntimeException("File config.properties tidak ditemukan");
            }
            props.load(inputStream);
            inputStream.close(); // Menutup InputStream setelah digunakan

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:platformName", "android");
            caps.setCapability("appium:deviceName", "pixel_4");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:udid", "emulator-5554");

            // for launch emulator automatically
            caps.setCapability("appium:avdLaunchTimeout", 180000);
            caps.setCapability("appium:adbExecTimeout", 60000); // 60 seconds
            String appUrl = "D:\\appiumJavaUdemy\\mda-2.2.0-25.apk";
            caps.setCapability("appium:app", appUrl);

            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(url, caps);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }


    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getDomAttribute(attribute);
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
