package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;
import static com.daytick.util.SortUtils.swap;

/**
 * 【冒泡排序】<p>
 * 每一轮遍历将最大的元素放在当前排序序列末尾, 并且当前数组个数从末端减一，一般不使用。
 *
 * @author ly
 * @since 2021/1/7 9:31 AM
 */
public class BubbleSort {

    /**
     * 最初版
     *
     * @param arr
     */
    public static void sort1(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) { // 排序 len - 1 轮
            for (int j = 0; j < len - i - 1; j++) { // 有序区每轮扩大 1 个长度
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1); // 将无序区的最大元素移动至有序区前端
                }
            }
        }
    }

    /**
     * 初步优化版
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) { // 当一轮比较没有交换元素时，表明数组已经有序，此时直接结束排序
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort1(array);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));

        int[] array2 = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort2(array2);
        System.out.println("array2 = " + Arrays.toString(array2) + ", sorted = " + isSorted(array2));
    }

}
