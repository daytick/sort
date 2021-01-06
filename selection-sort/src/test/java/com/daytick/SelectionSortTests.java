package com.daytick;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author ly
 * @since 2021/1/4 9:23 AM
 */
public class SelectionSortTests {

    @Test
    public void test() {
        int[] raw = {9, 8, 6, 12, 26, 21, 19, 12};
        int[] expect = {6, 8, 9, 12, 12, 19, 21, 26};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(raw);
        Assertions.assertArrayEquals(expect, raw);
        System.out.println("sorted raw = " + Arrays.toString(raw));
    }

}
