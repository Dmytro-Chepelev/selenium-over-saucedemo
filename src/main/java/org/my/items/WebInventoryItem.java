package org.my.items;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebInventoryItem extends WebItem {

    public WebInventoryItem(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(className = "inventory_item_name")
    WebElement webName;

    @FindBy(className = "inventory_item_price")
    WebElement webPrice;

    @FindBy(className = "btn_inventory")
    WebElement webAddRemoveButton;

    public String getName() {
        return webName.getText();
    }

    public double getPrice() {
        String textWithoutDollarSign = webPrice.getText().substring(1);
        return Double.parseDouble(textWithoutDollarSign);
    }

    public void addOrRemoveToCart() {
        webAddRemoveButton.click();
    }

    public boolean isInCart() {
        return webAddRemoveButton.getText().equalsIgnoreCase("Remove");
    }
}
