package com.ekansh.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By loginEmailInput = By.cssSelector(
            "input[data-qa='login-email']");

    private By loginPasswordInput = By.cssSelector(
            "input[data-qa='login-password']");

    private By loginButton = By.cssSelector(
            "button[data-qa='login-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(loginEmailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isEmailFieldDisplayed() {
        return driver.findElement(loginEmailInput).isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return driver.findElement(loginPasswordInput).isDisplayed();
    }

    public String getLoginErrorMessage() {
        return driver.findElement(
                By.xpath("//*[contains(text(),'Your email or password is incorrect!')]")).getText();
    }


}
