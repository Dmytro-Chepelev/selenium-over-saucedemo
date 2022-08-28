package org.my.pages;

import org.my.items.Goods;
import org.my.items.WebOverviewItem;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class CheckoutOverviewPage {

    public CheckoutOverviewPage(SearchContext searchContext) {
        PageFactory.initElements(searchContext, this);
    }

    @FindBy(id = "finish")
    private WebElement webFinishButton;

    @FindBy(id = "cancel")
    private WebElement webCancelButton;

    @FindBy(className = "cart_list")
    private WebElement webCartList;

    private List<WebElement> webListCartItems() {
        return webCartList.findElements(By.className("cart_item"));
    }

    public void gotoFinish() {
        webFinishButton.click();
    }

    public void gotoCancel() {
        webCancelButton.click();
    }

    public List<Goods> getGoodsSnapshot() {
        return webListCartItems().stream()
                .map(WebOverviewItem::new)
                .map(Goods::of)
                .toList();
    }
}
