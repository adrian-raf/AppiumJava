package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
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

    public BaseTest() {
        // Inisialisasi PageFactory jika diperlukan

    }

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
//            caps.setCapability("appium:platformVersion", "12L");
            caps.setCapability("appium:deviceName", "Pixel 4");
            caps.setCapability("appium:avd", "Pixel_4");
            caps.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
            caps.setCapability("appium:udid", "emulator-5554");
//            caps.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
//            caps.setCapability("appium:appActivity", props.getProperty("AndroidAppActivity"));
            caps.setCapability("appium:App", props.getProperty("androidAppLocation"));


            // Timeout untuk emulator dan adb
            caps.setCapability("appium:avdLaunchTimeout", 180000);
            caps.setCapability("appium:adbExecTimeout", 60000);

            URL url = new URL(props.getProperty("appiumURL"));
            driver = new AndroidDriver(url, caps);
//            PageFactory.initElements(new AppiumFieldDecorator(driver), this);


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
