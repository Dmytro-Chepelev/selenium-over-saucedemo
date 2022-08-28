package org.my.items;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOverviewItem extends WebItem {

    public WebOverviewItem(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(className = "inventory_item_name")
    WebElement webName;

    @FindBy(className = "inventory_item_price")
    WebElement webPrice;

    public String getName() {
        return webName.getText();
    }

    public double getPrice() {
        String textWithoutDollarSign = webPrice.getText().substring(1);
        return Double.parseDouble(textWithoutDollarSign);
    }
}
