package org.my.tests.authentication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.my.data.LoginData.*;
import static org.my.data.URLData.LOGIN_URL;
import static org.testng.Assert.assertEquals;

public class AuthenticationFailTestsByDataProviderArray extends AuthenticationTestsBase{

    @DataProvider(name = "invalidLogins")
    public Object[][] generateInvalidLogins (){
        return new Object[][] {
                {REGISTERED_USERNAME, BAD_PASSWORD, ERROR_MESSAGE_AUTHENTICATION_FAIL},
                {LOCKED_OUT_USERNAME, DEFAULT_PASSWORD, ERROR_MESSAGE_ACCOUNT_LOCKED_OUT},
                {"", "", ERROR_MESSAGE_USERNAME_REQUIRED},
                {"", DEFAULT_PASSWORD, ERROR_MESSAGE_USERNAME_REQUIRED},
                {REGISTERED_USERNAME, "", ERROR_MESSAGE_PASSWORD_REQUIRED},
                {UNREGISTERED_USERNAME, DEFAULT_PASSWORD, ERROR_MESSAGE_AUTHENTICATION_FAIL}
        };
    }

    @Test(dataProvider = "invalidLogins")
    public void authenticationFail (String userName, String password, String validErrorMessage) {
        loginPage.login(userName, password);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                validErrorMessage,
                "Unexpected message when authentication fail.\n"
        );
    }
}
