package org.my.pages;

import org.my.items.*;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    public CartPage(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(id = "checkout")
    private WebElement webCheckoutButton;

    @FindBy(className = "cart_list")
    private WebElement webCartList;

    private List<WebElement> webListCartItems() {
        return webCartList.findElements(By.className("cart_item"));
    }

    public void gotoCheckout() {
        webCheckoutButton.click();
    }

    public List<Goods> getGoodsSnapshot() {
        return webListCartItems().stream()
                .map(WebCartItem::new)
                .map(Goods::of)
                .toList();
    }
}
