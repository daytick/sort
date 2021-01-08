package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;
import static com.daytick.util.SortUtils.swap;

/**
 * 【插入排序】<p>
 * 适用于小数组、部分有序的数组
 *
 * @author ly
 * @since 2021/1/7 9:29 AM
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));
    }

}
