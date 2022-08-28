package org.my.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPersonalPage {

    public CheckoutPersonalPage(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(className = "cart_cancel_link")
    private WebElement cancelButton;

    @FindBy(className = "cart_button" )
    private WebElement continueButton;

    public void enterPersonalInformation(String firstName, String lastName, String postalCode) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
    }

    public void gotoContinue () {
        this.continueButton.click();
    }
}
