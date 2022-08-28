package org.my.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(id = "password")
    private WebElement webPasswordField;

    @FindBy(id = "user-name")
    private WebElement webUserNameField;

    @FindBy(id = "login-button")
    private WebElement webLoginButton;

    @FindBy(className = "error-message-container")
    private WebElement webErrorMessageContainer;

    public void login(String userName, String password) {
        webUserNameField.sendKeys(userName);
        webPasswordField.sendKeys(password);
        webLoginButton.click();
    }

    public String getErrorMessage() {
        return webErrorMessageContainer.getText();
    }

}