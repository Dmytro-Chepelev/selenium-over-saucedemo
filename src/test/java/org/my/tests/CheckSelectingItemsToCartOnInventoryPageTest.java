package org.my.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckSelectingItemsToCartOnInventoryPageTest extends DefaultLoginTest {

    @Test
    public void testAddAllItemsToCart() {
        inventoryPage.getGoodsSnapshot().forEach(inventoryPage::addGoodsToCart);
        assertTrue(
                inventoryPage.getGoodsNotAddedToCartSnapshot().isEmpty(),
                "Fail adding all items to cart.\n");
    }

    @Test
    public void testRemoveAllItemsFromCart() {
        inventoryPage.getGoodsSnapshot().forEach(inventoryPage::removeGoodsFromCart);
        assertTrue(
                inventoryPage.getGoodsAddedToCartSnapshot().isEmpty(),
                "Fail removing all items to cart.\n");
    }

    @DataProvider(name = "CheckSelectingItemsToCartOnInventoryPageTest")
    public Object[][] generateTasks() {
        return new Object [][] { {5.0}, {10.0}, {15.0},{20.0},{30.0},{55.0}};
    }

    @Test(dataProvider = "CheckSelectingItemsToCartOnInventoryPageTest")
    private void addToCartItemsWithPriceLowThenMaxPriceAndCheckIt(double maxPrice) {
        inventoryPage.getGoodsSnapshot().forEach( x -> {
            if (x.price <= maxPrice) {
                inventoryPage.addGoodsToCart(x);
            }
            else {
                inventoryPage.removeGoodsFromCart(x);
            }
        });

        inventoryPage.getGoodsAddedToCartSnapshot().forEach( x -> {
            assertTrue (x.price <= maxPrice, "Goods with a price greater than maxPrice was added unexpectedly to the cart.\n");
        });
        inventoryPage.getGoodsNotAddedToCartSnapshot().forEach( x -> {
            assertTrue ( x.price > maxPrice, "Goods with a price less than maxPrice was not added unexpectedly to the cart.\n");
        });
    }
}
