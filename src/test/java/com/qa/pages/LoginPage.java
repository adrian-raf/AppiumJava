package com.qa.pages;
import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class LoginPage extends BaseTest {

    @AndroidFindBy(accessibility = "View menu")
    public WebElement menuView;
    @AndroidFindBy(accessibility = "Login Menu Item")
    public WebElement loginMenu;
    @AndroidFindBy(accessibility = "Tap to login with given credentials")
    public WebElement loginBtn;
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    public WebElement usernameField;
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    public WebElement passwordField;
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameErrorTV")
    public WebElement errTxtEmptyName;
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    public WebElement errTxtEmptyPassword;

    public WebDriverWait wait;

    public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    // Method untuk berpindah ke halaman login
    public LoginPage moveToLoginPage() {
        try {
            System.out.println("Menunggu dan mencari menuView...");
            click(menuView);
//            menuView.get().click();
            System.out.println("menuView ditemukan dan diklik!");

            System.out.println("Menunggu dan mencari loginMenu...");
            click(loginMenu);
//            loginMenu.get().click();
            System.out.println("loginMenu ditemukan dan diklik!");
        } catch (Exception e) {
            System.out.println("⚠ Error di moveToLoginPage: " + e.getMessage());
        }
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public ProductPage pressLoginBtn() {
        try {
            System.out.println("Trying to click login button...");
            waitForVisibility(loginBtn);
            loginBtn.click();
            return new ProductPage();
        } catch (Exception e) {
            System.out.println("Login button not found: " + e.getMessage());
            return null;
        }
    }

    public String getErrTxtEmptyName() {
        return getAttribute(errTxtEmptyName, "text");
    }

    public String getErrTxtEmptyPassword() {
        return getAttribute(errTxtEmptyPassword, "text");
    }

}


