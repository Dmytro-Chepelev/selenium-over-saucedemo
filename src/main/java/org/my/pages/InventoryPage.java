package org.my.pages;

import org.my.items.Goods;
import org.my.items.WebInventoryItem;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.Predicate;

public class InventoryPage {

    public InventoryPage(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(className = "product_sort_container")
    WebElement selectElement;

    @FindBy(className = "shopping_cart_link" )
    WebElement webCartIcon;

    @FindBy(className = "inventory_list")
    WebElement webListInventory;

    private List<WebElement> webListInventoryItems() {
        return webListInventory.findElements(By.className("inventory_item"));
    }

    public void changeItemsSortOrderTo(SortOrder sortOption) {
        new Select(selectElement).selectByValue(sortOption.code);
    }

    public enum SortOrder {
        BY_NAME_A_TO_Z ("az"),
        BY_NAME_Z_TO_A ("za"),
        BY_PRICE_LOW_TO_HI ("lohi"),
        BY_PRICE_HI_TO_LOW ("hilo");

        final String code;
        SortOrder(String option) { code = option; }
    }

    public void gotoCartPage () {
        webCartIcon.click();
    }

    public List<Goods> getGoodsSnapshot() {
        return webListInventoryItems().stream()
                .map(WebInventoryItem::new)
                .map(Goods::of)
                .toList();
    }

    public List<Goods> getGoodsAddedToCartSnapshot() {
        return webListInventoryItems().stream()
                .map(WebInventoryItem::new)
                .filter(WebInventoryItem::isInCart)
                .map(Goods::of)
                .toList();
    }

    public List<Goods> getGoodsNotAddedToCartSnapshot() {
        return webListInventoryItems().stream()
                .map(WebInventoryItem::new)
                .filter(Predicate.not(WebInventoryItem::isInCart))
                .map(Goods::of)
                .toList();
    }

    public void addGoodsToCart(Goods goods) {
        WebInventoryItem item = (WebInventoryItem) goods.webItem;
        if (! item.isInCart()) {
            item.addOrRemoveToCart();
        }
    }

    public void removeGoodsFromCart(Goods goods) {
        WebInventoryItem item = (WebInventoryItem) goods.webItem;
        if (item.isInCart()) {
            item.addOrRemoveToCart();
        }
    }
}
