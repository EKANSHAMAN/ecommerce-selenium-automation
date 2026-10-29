
package com.ekansh.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {

    private WebDriver driver;

    // New User Signup section
    private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");

    private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");

    private By signupButton = By.cssSelector("button[data-qa='signup-button']");

    // Account Information section
    private By accountInformationHeading = By.xpath("//b[contains(text(),'Enter Account Information')]");

    private By titleMr = By.id("id_gender1");

    private By titleMrs = By.id("id_gender2");

    private By passwordInput = By.id("password");

    private By dayDropdown = By.id("days");

    private By monthDropdown = By.id("months");

    private By yearDropdown = By.id("years");

    private By newsletterCheckbox = By.id("newsletter");

    private By offersCheckbox = By.id("optin");

    // Address Information
    private By firstNameInput = By.id("first_name");

    private By lastNameInput = By.id("last_name");

    private By companyInput = By.id("company");

    private By address1Input = By.id("address1");

    private By address2Input = By.id("address2");

    private By countryDropdown = By.id("country");

    private By stateInput = By.id("state");

    private By cityInput = By.id("city");

    private By zipcodeInput = By.id("zipcode");

    private By mobileNumberInput = By.id("mobile_number");

    private By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    // Signup methods
    public void enterName(String name) {
        driver.findElement(signupNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(signupEmailInput).sendKeys(email);
    }

    public void clickSignup() {
        driver.findElement(signupButton).click();
    }

    public boolean isNameFieldDisplayed() {
        return driver.findElement(signupNameInput).isDisplayed();
    }

    public boolean isEmailFieldDisplayed() {
        return driver.findElement(signupEmailInput).isDisplayed();
    }

    // Account Information methods
    public boolean isAccountInformationDisplayed() {
        return driver.findElement(accountInformationHeading).isDisplayed();
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(titleMr).click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            driver.findElement(titleMrs).click();
        } else {
            throw new IllegalArgumentException(
                    "Title must be Mr or Mrs.");
        }
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void selectDateOfBirth(
            String day, String month, String year) {

        new Select(driver.findElement(dayDropdown))
                .selectByValue(day);

        new Select(driver.findElement(monthDropdown))
                .selectByValue(month);

        new Select(driver.findElement(yearDropdown))
                .selectByValue(year);
    }

    public void subscribeToNewsletter() {
        if (!driver.findElement(newsletterCheckbox).isSelected()) {
            driver.findElement(newsletterCheckbox).click();
        }
    }

    public void optInForOffers() {
        if (!driver.findElement(offersCheckbox).isSelected()) {
            driver.findElement(offersCheckbox).click();
        }
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterCompany(String company) {
        driver.findElement(companyInput).sendKeys(company);
    }

    public void enterAddress1(String address) {
        driver.findElement(address1Input).sendKeys(address);
    }

    public void enterAddress2(String address) {
        driver.findElement(address2Input).sendKeys(address);
    }

    public void selectCountry(String country) {
        new Select(driver.findElement(countryDropdown))
                .selectByVisibleText(country);
    }

    public void enterState(String state) {
        driver.findElement(stateInput).sendKeys(state);
    }

    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        driver.findElement(zipcodeInput).sendKeys(zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

}
