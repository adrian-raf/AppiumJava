package com.qa.tests;
import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    LoginPage loginPage;
    ProductPage productPage;


    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
    }

    @Test
    public void emptyUsernameField() {
        loginPage.moveToLoginPage();
        loginPage.enterUsername("");
        loginPage.enterPassword("satu");
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrTxtEmptyName();
        String expectedErrTxt = "Username is required";
        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

//    @Test
//    public void validUsernamePassword() throws InterruptedException {
//        productPage = loginPage.moveToLoginPage()
//                .enterUsername("validUser")
//                .enterPassword("validPassword")
//                .pressLoginBtn();  // Menyimpan return value karena login sukses
//
//        String actualProductTitle = productPage.getTitle();
//        String expectedProductTitle = "PRODUCTS";
//        System.out.println("product title = " + actualProductTitle);
//        Assert.assertEquals(actualProductTitle, expectedProductTitle);
//
//        Thread.sleep(3000);
//    }



//    @Test
//    public void emptyPasswordField() {
//        loginPage.enterUsername("validUser");
//        loginPage.enterPassword("");
//        loginPage.pressLoginBtn();
//
//        String actualErrTxt = loginPage.getErrTxtEmptyPassword();
//        String expectedErrTxt = "Enter Password";
//        Assert.assertEquals(actualErrTxt, expectedErrTxt);
//
//    }
//
//    @Test
//    public void validUsernamePassword() throws InterruptedException {
//        loginPage.enterUsername("validUser");
//        loginPage.enterPassword("validPassword");
//        productPage =loginPage.pressLoginBtn();
//
//        String actualProductTitle = productPage.getTitle();
//        String expectedProudctTitle = "PRODUCTS";
//        System.out.println("product title = " + actualProductTitle);
//        Assert.assertEquals(actualProductTitle,expectedProudctTitle);
//
//        Thread.sleep(3000);
//
//    }
}
