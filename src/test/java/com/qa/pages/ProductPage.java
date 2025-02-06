package com.qa.pages;
import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class ProductPage extends BaseTest{
    @AndroidFindBy(accessibility = "App logo and name") public WebElement productTitleTxt;
    public String getTitle(){
        return getAttribute(productTitleTxt, "text");
    }
}
