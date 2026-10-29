# E-Commerce Web Application Testing | Selenium Automation

A web UI automation testing project built using **Java, Selenium WebDriver, TestNG, and Maven** to automate key user flows on the Automation Exercise website.

## Overview

This project automates essential e-commerce website workflows using Selenium WebDriver and the Page Object Model (POM) design pattern. It includes test scenarios for user registration, login, logout, account management, and UI verification.

## Tech Stack

- **Language:** Java
- **Automation Tool:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Driver Management:** WebDriverManager
- **Design Pattern:** Page Object Model (POM)
- **Browser:** Google Chrome

## Project Structure

```text
ecommerce-automation/
├── .mvn/
├── src/
│   ├── main/java/com/ekansh/ecommerce/
│   │   └── App.java
│   └── test/java/com/ekansh/ecommerce/
│       ├── AppTest.java
│       ├── HomePageTest.java
│       ├── base/
│       │   └── BaseTest.java
│       └── pages/
│           ├── HomePage.java
│           ├── LoginPage.java
│           └── SignupPage.java
├── .gitignore
├── pom.xml
└── README.md
```

## Automated Test Coverage

The test suite contains **13 automated tests** covering workflows such as:

- Home page and page-title verification
- Signup and account registration
- Login with valid and invalid credentials
- Logout functionality
- Account deletion and cleanup
- Form and account-related UI verification

## Prerequisites

Install the following before running the tests:

- Java JDK 17 or later
- Apache Maven
- Google Chrome
- Git

## Setup and Execution

### 1. Clone the repository

```bash
git clone https://github.com/EKANSHAMAN/ecommerce-selenium-automation.git
cd ecommerce-selenium-automation
```

### 2. Configure test credentials

The valid-login and logout tests read credentials from environment variables named `TEST_EMAIL` and `TEST_PASSWORD`.

**PowerShell (Windows):**

```powershell
$env:TEST_EMAIL = Read-Host "Enter your test account email"
$securePassword = Read-Host "Enter your test account password" -AsSecureString
$env:TEST_PASSWORD = [System.Net.NetworkCredential]::new("", $securePassword).Password
```

Use a test account for the target website. Do not commit real credentials to the repository.

### 3. Run the test suite

```bash
mvn clean test
```

### 4. Run a specific test

```bash
mvn -Dtest=HomePageTest#verifyTestAccountCleanup test
```

## Test Results

The complete test suite has successfully executed locally with:

- **Tests run:** 13
- **Failures:** 0
- **Errors:** 0
- **Build status:** BUILD SUCCESS

Test results can vary depending on website availability and server load.

## Design Approach

The project uses the **Page Object Model (POM)** to separate page interactions from test logic. Page classes encapsulate web elements and reusable actions, while test classes verify application workflows.

## Future Improvements

- Add automated test reporting
- Integrate tests with GitHub Actions CI
- Expand test coverage for additional e-commerce workflows
- Improve failure diagnostics with screenshots and logs

## Author

**Ekansh Narayan Mishra**

GitHub: [EKANSHAMAN](https://github.com/EKANSHAMAN)

## Application Under Test

[Automation Exercise](https://automationexercise.com/)

---

*This project was developed for learning and demonstrating web UI automation testing with Selenium WebDriver.*
