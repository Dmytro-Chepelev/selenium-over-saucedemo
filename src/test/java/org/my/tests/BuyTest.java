package org.my.tests;

import org.my.data.URLData;
import org.my.items.Goods;
import org.my.pages.*;
import org.my.utils.ListChecking;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BuyTest extends DefaultLoginTest {

    private CartPage cartPage;
    private CheckoutPersonalPage checkoutPersonalPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    @DataProvider(name = "BuyTest")
    public Object[][] generateTasks() {
        return new Object [][] { {5.0}, {10.0}, {15.0},{20.0},{30.0},{55.0}};
    }

    @Test(dataProvider = "BuyTest")
    public void buyGoodsWithPriceLowThen(double maxPrice)
    {
        // login in default account, goto InventoryPage
        // -- implementation in DefaultLoginTest.class --

        // select to cart goods with price less maxPrice
        inventoryPage.getGoodsSnapshot().forEach( x-> {
            if (x.price < maxPrice) {
                inventoryPage.addGoodsToCart(x);
            } else {
                inventoryPage.removeGoodsFromCart(x);
            }
        });

        // fix the list of selected products
        List<Goods> inventoryList = inventoryPage.getGoodsAddedToCartSnapshot();

        // goto CartPage
        inventoryPage.gotoCartPage();
        cartPage = new CartPage(driver);

        // fix the list of selected products
        List<Goods> cartList = cartPage.getGoodsSnapshot();

        assertTrue (
                ListChecking.isIdentical(inventoryList, cartList, Goods.BY_NAME_AND_PRICE),
                "List goods from InventoryPage and CartPage is not identical."
        );

        // goto CheckoutPage-1
        cartPage.gotoCheckout();
        checkoutPersonalPage = new CheckoutPersonalPage (driver);

        // enter personal data
        checkoutPersonalPage.enterPersonalInformation("Dmytro", "Chepelev", "12345678");

        // goto CheckoutPage-2
        checkoutPersonalPage.gotoContinue();
        checkoutOverviewPage = new CheckoutOverviewPage(driver);

        // fix the list of selected products
        List<Goods> checkoutList = checkoutOverviewPage.getGoodsSnapshot();

        // goto CheckoutFinishPage, check it
        checkoutOverviewPage.gotoFinish();
        assertBrowserOpenURL(URLData.FINISH_URL);

        // check that all lists are identical
        assertTrue (
                ListChecking.isIdentical(cartList, checkoutList, Goods.BY_NAME_AND_PRICE),
                "List goods from CartPage and CheckoutPage is not identical."
        );
    }
}