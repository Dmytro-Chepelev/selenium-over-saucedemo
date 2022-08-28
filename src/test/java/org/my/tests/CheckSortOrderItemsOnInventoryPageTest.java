package org.my.tests;

import java.util.Comparator;

import org.my.items.Goods;
import org.my.utils.ConvertToDataProviderArray;
import org.my.utils.ListChecking;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.my.pages.InventoryPage.SortOrder;

import static org.testng.Assert.assertTrue;

public class CheckSortOrderItemsOnInventoryPageTest extends DefaultLoginTest {

    private enum TasksForTest {
        SortByPriceLowToHi
                (SortOrder.BY_PRICE_LOW_TO_HI, Goods.BY_PRICE_LOW_TO_HI_COMPARATOR),
        SortByPriceHiToLow
                (SortOrder.BY_PRICE_HI_TO_LOW, Goods.BY_PRICE_HI_TO_LOW_COMPARATOR),
        SortedByNameAtoZ
                (SortOrder.BY_NAME_A_TO_Z, Goods.BY_NAME_A_TO_Z_COMPARATOR),
        SortedByNameZtoA
                (SortOrder.BY_NAME_Z_TO_A, Goods.BY_NAME_Z_TO_A_COMPARATOR);

        TasksForTest(SortOrder selectSortOrderForItemsOnPage, Comparator<Goods> checkSortOrderByComparator) {
            this.sortOrder = selectSortOrderForItemsOnPage;
            this.comparator = checkSortOrderByComparator;
        }

        SortOrder sortOrder;
        Comparator<Goods> comparator;
    }

    @DataProvider(name = "CheckSortOrderItemsOnInventoryPageTest")
    public Object[][] generateTasks() {
        return ConvertToDataProviderArray.fromEnumClassValues(TasksForTest.class);
    }

    @Test(dataProvider = "CheckSortOrderItemsOnInventoryPageTest")
    public void resortingItemsAndCheckOrder(TasksForTest task) {
        inventoryPage.changeItemsSortOrderTo(task.sortOrder);
        assertTrue(
                ListChecking.isSorted(inventoryPage.getGoodsSnapshot(), task.comparator),
                "The sort order is fail in mode " + task.sortOrder.name() + ".\n");
    }
}