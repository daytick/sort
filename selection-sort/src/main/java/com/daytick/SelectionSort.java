package com.daytick;

/**
 * 选择排序（从小到大）
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
    void sort(int[] arr) {
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
    void sortOptimized(int[] arr) {
        if (arr == null || arr.length <= 1) { // 考虑特殊情况
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = getMinIndex(arr, i);
            if (minIndex != i) { // 当前元素不是最小元素时才进行交换
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    private int getMinIndex(int[] arr, int i) {
        int minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        return minIndex;
    }
}
