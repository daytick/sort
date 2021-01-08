package com.daytick.util;

/**
 * @author ly
 * @since 2021/1/7 9:10 AM
 */
public class SortUtils {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    public static boolean less(int a, int b) {
        return a < b;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
