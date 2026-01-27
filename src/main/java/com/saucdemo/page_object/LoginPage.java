package com.saucdemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriverWait wait;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {//konstructor
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait =new WebDriverWait(driver, Duration.ofSeconds(2));//inicil
    }

    //PageFactory
    @FindBy(id ="user-name")
    public WebElement usernameField;

    @FindBy(id ="password")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@data-test=\"login-button\"]")
    public WebElement loginButton;

    //Authorize metod
    public void authorize(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));//explicitly wait  - явное одидание, element to be clickable
        //wait.until(ExpectedConditions.invisibilityOf(loginButton));//check if invisibility Button
        loginButton.click();

    }
}
