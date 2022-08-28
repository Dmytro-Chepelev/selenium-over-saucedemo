package org.my.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListChecking {

    public static <E> boolean isSorted(List<E> listObjects, Comparator<E> comparatorForObjects) {
        if (listObjects.size() <= 1) {
            return true; // for empty list and list with one element
        }

        Iterator<E> iterator = listObjects.iterator();
        E current;
        E previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (comparatorForObjects.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static <E> boolean isIdentical(List<E> firstList, List<E> secondList, Comparator<E> comparatorForObjects) {
        if (firstList.size() != secondList.size()) {
            return false;
        }

        E[] first = (E[]) firstList.toArray();
        Arrays.sort(first, comparatorForObjects);

        E[] second = (E[]) secondList.toArray();
        Arrays.sort(second, comparatorForObjects);

        return 0 == Arrays.compare(first, second, comparatorForObjects);
    }

}
