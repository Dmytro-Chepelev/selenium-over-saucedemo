package org.my.tests.authentication;

import org.testng.annotations.Test;

import static org.my.data.LoginData.DEFAULT_PASSWORD;
import static org.my.data.LoginData.REGISTERED_USERNAME;
import static org.my.data.URLData.INVENTORY_URL;

public class AuthenticationSuccessTests extends AuthenticationTestsBase {

    @Test
    public void authenticationSuccessful() {
        loginPage.login(REGISTERED_USERNAME, DEFAULT_PASSWORD);
        assertBrowserOpenURL(INVENTORY_URL, "'Inventory' page is not opened after login.\n");
    }

}
