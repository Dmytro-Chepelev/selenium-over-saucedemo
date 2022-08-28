package org.my.tests.authentication;

import org.my.tests.SeleniumTest;
import org.testng.annotations.BeforeMethod;

import org.my.pages.LoginPage;

import static org.my.data.URLData.LOGIN_URL;

public abstract class AuthenticationTestsBase extends SeleniumTest {

    protected LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        driver.navigate().to(LOGIN_URL);
        assertBrowserOpenURL(LOGIN_URL);
        loginPage = new LoginPage(driver);
    }

    protected static final String
        ERROR_MESSAGE_USERNAME_REQUIRED = "Epic sadface: Username is required",
        ERROR_MESSAGE_PASSWORD_REQUIRED = "Epic sadface: Password is required",
        ERROR_MESSAGE_AUTHENTICATION_FAIL = "Epic sadface: Username and password do not match any user in this service",
        ERROR_MESSAGE_ACCOUNT_LOCKED_OUT = "Epic sadface: Sorry, this user has been locked out.";
}
