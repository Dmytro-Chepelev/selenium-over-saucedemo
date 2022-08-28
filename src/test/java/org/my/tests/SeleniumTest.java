package org.my.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.assertEquals;

public abstract class SeleniumTest {

    @BeforeSuite
    public void setupSeleniumDriver() {
        WebDriverManager.chromedriver().setup();
    }

    protected WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    protected void assertBrowserOpenURL(String mustBeURL, String errorMessage) {
        String currentURL = driver.getCurrentUrl();
        assertEquals(currentURL, mustBeURL, "Unexpected URL opened in browser. " + errorMessage + "\n");
    }
    protected void assertBrowserOpenURL(String mustBeURL) {
        assertBrowserOpenURL(mustBeURL, "");
    }


}
