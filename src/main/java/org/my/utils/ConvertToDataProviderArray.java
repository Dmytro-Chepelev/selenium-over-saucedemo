package org.my.utils;

public class ConvertToDataProviderArray {

    public static <E extends Enum<E>> Object[][] fromEnumClassValues(Class<E> enumClass) {
        E[] constants = enumClass.getEnumConstants();
        Object[][] data = new Object[constants.length][1];
        for (int i = 0; i < constants.length; i++)
            data[i][0] = constants[i];
        return data;
    }

}
