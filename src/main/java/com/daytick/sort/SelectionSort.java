package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;
import static com.daytick.util.SortUtils.swap;

/**
 * 【选择排序】<p>
 * 每一轮遍历选择最小的的元素放在当前排序序列开头, 同时未排序数组个数减一
 *
 * @author ly
 * @since 2021/1/4 9:15 AM
 */
public class SelectionSort {

    /**
     * 精简版
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // 进行 arr.length-1 轮最小元素选择
            int minIndex = getMinIndex(arr, i); // 一一查看从 i 往后的元素，选出其中最小元素索引
            swap(arr, i, minIndex); // 交换最小元素到已排序序列末尾
        }
    }

    /**
     * 初步优化版
     *
     * @param arr
     */
    public static void sortOptimized(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = getMinIndex(arr, i);
            if (minIndex != i) { // 当前元素不是最小元素时才进行交换
                swap(arr, i, minIndex);
            }
        }
    }

    private static int getMinIndex(int[] arr, int i) {
        int minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 6, 12, 26, 21, 19, 12};
        sort(arr);
        System.out.println("arr = " + Arrays.toString(arr) + ", sorted = " + isSorted(arr));

        int[] arr2 = {9, 8, 6, 12, 26, 21, 19, 12};
        sortOptimized(arr2);
        System.out.println("arr2 = " + Arrays.toString(arr2) + ", sorted = " + isSorted(arr2));
    }
}
