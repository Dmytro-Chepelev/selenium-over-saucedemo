package org.my.items;

import org.my.pages.InventoryPage;

import java.util.Comparator;

public class Goods {

    public final String name;
    public final double price;
    public final WebItem webItem;

    Goods(String name, double price, WebItem webItem) {
        this.name = name;
        this.price = price;
        this.webItem = webItem;
    }

    public static Goods of(WebItem wi) {
        return new Goods(wi.getName(), wi.getPrice(), wi);
    }

    public static final Comparator<Goods>
            BY_PRICE_LOW_TO_HI_COMPARATOR = (o1, o2) -> Double.compare(o1.price, o2.price),
            BY_PRICE_HI_TO_LOW_COMPARATOR = (o1, o2) -> Double.compare(o2.price, o1.price),
            BY_NAME_A_TO_Z_COMPARATOR = (o1, o2) -> o1.name.compareTo(o2.name),
            BY_NAME_Z_TO_A_COMPARATOR = (o1, o2) -> o2.name.compareTo(o1.name),
            BY_NAME_AND_PRICE = ((o1, o2) -> {
                int byName = o1.name.compareTo(o2.name);
                return byName != 0
                        ? byName
                        : Double.compare(o1.price, o2.price);
            } );
}
