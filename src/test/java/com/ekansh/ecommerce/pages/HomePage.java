package com.ekansh.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By signupLoginLink = By.linkText("Signup / Login");

    private By logoutLink = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickSignupLogin() {
        driver.findElement(signupLoginLink).click();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isLogoutLinkDisplayed() {
        return driver.findElement(logoutLink).isDisplayed();
    }

    // Delete Account navigation link
    private By deleteAccountLink = By.linkText("Delete Account");

    public void clickDeleteAccount() {
        driver.findElement(deleteAccountLink).click();
    }

    public boolean isDeleteAccountLinkDisplayed() {
        return driver.findElement(deleteAccountLink).isDisplayed();
    }

    private By accountDeletedMessage =
            By.cssSelector("[data-qa='account-deleted']");

    public boolean isAccountDeletedMessageDisplayed() {
        return driver.findElement(accountDeletedMessage).isDisplayed();
    }
}