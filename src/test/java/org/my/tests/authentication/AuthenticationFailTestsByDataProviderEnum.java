package org.my.tests.authentication;

import org.my.utils.ConvertToDataProviderArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.my.data.LoginData.*;
import static org.my.data.URLData.LOGIN_URL;
import static org.testng.Assert.assertEquals;

public class AuthenticationFailTestsByDataProviderEnum extends AuthenticationTestsBase{

    private enum TasksForTest {
        BadPassword
                (REGISTERED_USERNAME, BAD_PASSWORD, ERROR_MESSAGE_AUTHENTICATION_FAIL),
        LockedOutUser
                (LOCKED_OUT_USERNAME, DEFAULT_PASSWORD, ERROR_MESSAGE_ACCOUNT_LOCKED_OUT),
        NoUserNameNoPassword
                ("", "", ERROR_MESSAGE_USERNAME_REQUIRED),
        NoUserName
                ("", DEFAULT_PASSWORD, ERROR_MESSAGE_USERNAME_REQUIRED),
        NoPassword
                (REGISTERED_USERNAME, "", ERROR_MESSAGE_PASSWORD_REQUIRED),
        UnregisteredUserName
                (UNREGISTERED_USERNAME, DEFAULT_PASSWORD, ERROR_MESSAGE_AUTHENTICATION_FAIL);

        TasksForTest(String enterUserNameForLogin, String enterPasswordForLogin, String checkErrorMessageAfterLoginFail) {
            this.userName = enterUserNameForLogin;
            this.password = enterPasswordForLogin;
            this.errorMessage = checkErrorMessageAfterLoginFail;
        }
        public final String userName, password, errorMessage;
    }

    @DataProvider(name = "AuthenticationFailTestsByDataProviderEnum")
    public Object[][] generateTasks (){
        return ConvertToDataProviderArray.fromEnumClassValues(TasksForTest.class);
    }

    @Test(dataProvider = "AuthenticationFailTestsByDataProviderEnum")
    public void authenticationFail (TasksForTest task) {
        loginPage.login(task.userName, task.password);
        assertBrowserOpenURL(LOGIN_URL);
        assertEquals(
                loginPage.getErrorMessage(),
                task.errorMessage,
                "Unexpected message when authentication fail.\n"
        );
    }
}
