package org.my.tests.authentication;

import static org.my.data.LoginData.*;
import static org.my.data.URLData.LOGIN_URL;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class AuthenticationFailTestsBySingle extends AuthenticationTestsBase {

    @Test
    public void authenticationFail_BadPassword() {
        loginPage.login(REGISTERED_USERNAME, BAD_PASSWORD);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_AUTHENTICATION_FAIL,
                "Unexpected message when authentication fail.\n");

    }

    @Test
    public void authenticationFail_LockedOutUserName() {
        loginPage.login(LOCKED_OUT_USERNAME, DEFAULT_PASSWORD);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_ACCOUNT_LOCKED_OUT,
                "Unexpected message when authentication fail.\n");
    }

    @Test
    public void authenticationFail_NoUserNameNoPassword() {
        loginPage.login("", "");
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_USERNAME_REQUIRED,
                "Unexpected message when authentication fail.\n");
    }

    @Test
    public void authenticationFail_NoUserName() {
        loginPage.login("", DEFAULT_PASSWORD);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_USERNAME_REQUIRED,
                "Unexpected message when authentication fail.\n");
    }

    @Test
    public void authenticationFail_NoPassword() {
        assertBrowserOpenURL(LOGIN_URL);
        loginPage.login(REGISTERED_USERNAME, "");
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_PASSWORD_REQUIRED,
                "Unexpected message when authentication fail.\n");
    }

    @Test
    public void authenticationFail_UnregisteredUserName() {
        loginPage.login(UNREGISTERED_USERNAME, DEFAULT_PASSWORD);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                ERROR_MESSAGE_AUTHENTICATION_FAIL,
                "Unexpected message when authentication fail.\n");
    }

}
