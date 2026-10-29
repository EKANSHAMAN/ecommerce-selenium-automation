package com.ekansh.ecommerce;

import com.ekansh.ecommerce.base.BaseTest;
import com.ekansh.ecommerce.pages.HomePage;
import com.ekansh.ecommerce.pages.LoginPage;
import com.ekansh.ecommerce.pages.SignupPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

        private final String testEmail = System.getenv("TEST_EMAIL");
        private final String testPassword = System.getenv("TEST_PASSWORD");

        private void validateCredentials() {
                Assert.assertNotNull(testEmail, "TEST_EMAIL environment variable is missing.");
                Assert.assertFalse(testEmail.isBlank(), "TEST_EMAIL cannot be empty.");

                Assert.assertNotNull(testPassword, "TEST_PASSWORD environment variable is missing.");
                Assert.assertFalse(testPassword.isBlank(), "TEST_PASSWORD cannot be empty.");
        }

        @Test
        public void verifyHomePageTitle() {

                HomePage homePage = new HomePage(driver);

                String actualTitle = homePage.getPageTitle();

                System.out.println("Page Title: " + actualTitle);

                Assert.assertTrue(
                                actualTitle.toLowerCase().contains("automation exercise"),
                                "Home page title did not match the expected value.");
        }

        @Test
        public void verifySignupLoginNavigation() {
                HomePage homePage = new HomePage(driver);

                homePage.clickSignupLogin();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .urlContains("/login"));

                Assert.assertTrue(
                                driver.getCurrentUrl().contains("/login"),
                                "Signup/Login page did not open. Current URL:" + driver.getCurrentUrl());
        }

        @Test
        public void verifyLoginPageElements() {
                HomePage homePage = new HomePage(driver);

                homePage.clickSignupLogin();

                LoginPage loginPage = new LoginPage(driver);

                Assert.assertTrue(
                                driver.getCurrentUrl().contains("login"),
                                "Login page did not open.");
        }

        @Test
        public void verifyLoginFormFields() {
                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                LoginPage loginPage = new LoginPage(driver);

                Assert.assertTrue(
                                loginPage.isEmailFieldDisplayed(),
                                "Login email field is not displayed.");

                Assert.assertTrue(
                                loginPage.isPasswordFieldDisplayed(),
                                "Login password field is not displayed.");
        }

        @Test
        public void verifyInvalidLoginErrorMessage() {
                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                LoginPage loginPage = new LoginPage(driver);

                loginPage.enterEmail("invaliduser123@example.com");
                loginPage.enterPassword("WrongPassword123");
                loginPage.clickLogin();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//*[contains(text(),'Your email or password is incorrect!')]")));

                Assert.assertEquals(
                                loginPage.getLoginErrorMessage(),
                                "Your email or password is incorrect!",
                                "Expected invalid login error message was not displayed.");
        }

        @Test
        public void verifyValidLogin() {
                validateCredentials();
                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                LoginPage loginPage = new LoginPage(driver);

                loginPage.enterEmail(testEmail);
                loginPage.enterPassword(testPassword);
                loginPage.clickLogin();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//*[contains(text(),'Logged in as')]")));

                Assert.assertTrue(
                                driver.getPageSource().contains("Logged in as"),
                                "Valid login was not successful.");
        }

        @Test
        public void verifyLogout() {

                validateCredentials();
                // Step 1: Open login page
                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                // Step 2: Login with your registered credentials
                LoginPage loginPage = new LoginPage(driver);

                loginPage.enterEmail(testEmail);
                loginPage.enterPassword(testPassword);
                loginPage.clickLogin();

                // Step 3: Wait until login is successful
                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//*[contains(text(),'Logged in as')]")));

                // Step 4: Verify Logout link is visible
                Assert.assertTrue(
                                homePage.isLogoutLinkDisplayed(),
                                "Logout link is not displayed after login.");

                // Step 5: Click Logout
                homePage.clickLogout();

                // Step 6: Verify user is redirected to login page
                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .urlContains("/login"));

                Assert.assertTrue(
                                driver.getCurrentUrl().contains("/login"),
                                "User was not redirected to the login page after logout.");
        }

        @Test
        public void verifySignupFormNavigation() {

                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                SignupPage signupPage = new SignupPage(driver);

                Assert.assertTrue(
                                signupPage.isNameFieldDisplayed(),
                                "Signup name field is not displayed.");

                Assert.assertTrue(
                                signupPage.isEmailFieldDisplayed(),
                                "Signup email field is not displayed.");

                signupPage.enterName("Ekansh Test");
                signupPage.enterEmail(
                                "ekansh.test." + System.currentTimeMillis() + "@example.com");

                signupPage.clickSignup();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .urlContains("/signup"));

                Assert.assertTrue(
                                driver.getCurrentUrl().contains("/signup"),
                                "Account creation form did not open.");
        }

        @Test
        public void verifyAccountInformationForm() {

                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                SignupPage signupPage = new SignupPage(driver);

                String uniqueEmail = "ekansh.test." + System.currentTimeMillis()
                                + "@example.com";

                signupPage.enterName("Ekansh Test");
                signupPage.enterEmail(uniqueEmail);
                signupPage.clickSignup();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//b[contains(text(),'Enter Account Information')]")));

                Assert.assertTrue(
                                signupPage.isAccountInformationDisplayed(),
                                "Account Information heading is not displayed.");

                System.out.println(
                                "Account Information form opened successfully.");
        }

        @Test
        public void verifyAccountRegistration() {

                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                SignupPage signupPage = new SignupPage(driver);

                String uniqueEmail = "ekansh.test." + System.currentTimeMillis()
                                + "@example.com";

                signupPage.enterName("Ekansh Test");
                signupPage.enterEmail(uniqueEmail);
                signupPage.clickSignup();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.id("password")));

                // Account Information
                signupPage.selectTitle("Mr");
                signupPage.enterPassword("TestPass123!");
                signupPage.selectDateOfBirth("10", "8", "2002");

                signupPage.subscribeToNewsletter();
                signupPage.optInForOffers();

                // Address Information
                signupPage.enterFirstName("Ekansh");
                signupPage.enterLastName("Test");
                signupPage.enterCompany("Test Company");
                signupPage.enterAddress1("123 Test Street");
                signupPage.enterAddress2("Sector 1");
                signupPage.selectCountry("India");
                signupPage.enterState("Uttar Pradesh");
                signupPage.enterCity("Ghaziabad");
                signupPage.enterZipcode("201001");
                signupPage.enterMobileNumber("9876543210");

                // Submit registration
                signupPage.clickCreateAccount();

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By
                                                                                .cssSelector(
                                                                                                "[data-qa='account-created']")));

                Assert.assertTrue(
                                driver.findElement(
                                                org.openqa.selenium.By.cssSelector(
                                                                "[data-qa='account-created']"))
                                                .isDisplayed(),
                                "Account creation success message was not displayed.");

                System.out.println("Account registration successful.");
        }

        @Test
        public void verifyRegistrationContinueAndLoginState() {

                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                SignupPage signupPage = new SignupPage(driver);

                String uniqueEmail = "ekansh.test." + System.currentTimeMillis()
                                + "@example.com";

                signupPage.enterName("Ekansh Test");
                signupPage.enterEmail(uniqueEmail);
                signupPage.clickSignup();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.id("password")));

                signupPage.selectTitle("Mr");
                signupPage.enterPassword("TestPass123!");
                signupPage.selectDateOfBirth("10", "8", "2002");

                signupPage.enterFirstName("Ekansh");
                signupPage.enterLastName("Test");
                signupPage.enterAddress1("123 Test Street");
                signupPage.selectCountry("India");
                signupPage.enterState("Uttar Pradesh");
                signupPage.enterCity("Ghaziabad");
                signupPage.enterZipcode("201001");
                signupPage.enterMobileNumber("9876543210");

                signupPage.clickCreateAccount();

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.cssSelector(
                                                                                "[data-qa='account-created']")));

                Assert.assertTrue(
                                driver.findElement(
                                                org.openqa.selenium.By.cssSelector(
                                                                "[data-qa='account-created']"))
                                                .isDisplayed(),
                                "Account Created confirmation was not displayed.");

                signupPage.clickContinue();

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//*[contains(text(),'Logged in as')]")));

                Assert.assertTrue(
                                driver.getPageSource().contains("Logged in as"),
                                "User was not logged in after registration.");

                System.out.println(
                                "Registration, Continue and logged-in state verified.");
        }

        @Test
        public void verifyTestAccountCleanup() {

                HomePage homePage = new HomePage(driver);
                homePage.clickSignupLogin();

                SignupPage signupPage = new SignupPage(driver);

                String uniqueEmail = "cleanup." + System.currentTimeMillis()
                                + "@example.com";

                signupPage.enterName("Cleanup Test");
                signupPage.enterEmail(uniqueEmail);
                signupPage.clickSignup();

                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                                driver,
                                java.time.Duration.ofSeconds(10));

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.id("password")));

                // Fill account details
                signupPage.selectTitle("Mr");
                signupPage.enterPassword("CleanupPass123!");
                signupPage.selectDateOfBirth("10", "8", "2002");
                signupPage.enterFirstName("Cleanup");
                signupPage.enterLastName("Test");
                signupPage.enterAddress1("123 Test Street");
                signupPage.selectCountry("India");
                signupPage.enterState("Uttar Pradesh");
                signupPage.enterCity("Ghaziabad");
                signupPage.enterZipcode("201001");
                signupPage.enterMobileNumber("9876543210");

                signupPage.clickCreateAccount();

                // Verify account creation
                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.cssSelector(
                                                                                "[data-qa='account-created']")));

                Assert.assertTrue(
                                driver.findElement(
                                                org.openqa.selenium.By.cssSelector(
                                                                "[data-qa='account-created']"))
                                                .isDisplayed(),
                                "Test account was not created.");

                // Continue to the logged-in session
                signupPage.clickContinue();

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.xpath(
                                                                                "//*[contains(text(),'Logged in as')]")));

                Assert.assertTrue(
                                homePage.isDeleteAccountLinkDisplayed(),
                                "Delete Account link is not visible.");

                // Delete only the account created by this test
                homePage.clickDeleteAccount();

                wait.until(
                                org.openqa.selenium.support.ui.ExpectedConditions
                                                .visibilityOfElementLocated(
                                                                org.openqa.selenium.By.cssSelector(
                                                                                "[data-qa='account-deleted']")));

                Assert.assertTrue(
                                homePage.isAccountDeletedMessageDisplayed(),
                                "Account deletion confirmation was not displayed.");

                System.out.println(
                                "Test account created and deleted successfully.");
        }

}