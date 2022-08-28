package org.my.tests;

import org.my.data.LoginData;
import org.my.pages.*;
import org.testng.annotations.BeforeMethod;

import static org.my.data.URLData.INVENTORY_URL;
import static org.my.data.URLData.LOGIN_URL;

public abstract class DefaultLoginTest extends SeleniumTest {

    protected InventoryPage inventoryPage;

    @BeforeMethod
    public void defaultLogin() {
        driver.navigate().to(LOGIN_URL);
        assertBrowserOpenURL(LOGIN_URL);
        new LoginPage(driver).login(LoginData.REGISTERED_USERNAME, LoginData.DEFAULT_PASSWORD);
        assertBrowserOpenURL(INVENTORY_URL);
        inventoryPage = new InventoryPage(driver);
    }

}
