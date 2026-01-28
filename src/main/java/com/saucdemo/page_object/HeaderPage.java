package com.saucdemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
    private WebDriver driver;

    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // @FindBy(className = "shopping_cart_link");
     @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }

     public WebElement getShoppingCarBadge() {
        return shoppingCartBadge;
    }

}



