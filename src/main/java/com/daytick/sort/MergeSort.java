package com.daytick.sort;

import java.util.Arrays;

/**
 * 【归并排序】<p>
 * 归并排序的核心是: 归并操作<p>
 * 归并操作将两个有序的数组归并为更大的一个有序数组<p>
 * 要将一个数组排序, 可以先(递归的)将它分为两半分别排序, 然后将结果归并起来
 *
 * @author ly
 * @since 2021/1/6 8:58 AM
 */
public class MergeSort {

    /**
     * 自顶向下（基于递归）
     * todo 可优化
     * 1. 对小规模子数组使用插入排序
     * 2. 测试待排序序列中左右半边是否已有序
     * 3. 去除原数组序列到辅助数组的拷贝
     *
     * @param arr 待排序数组
     */
    public static void sort1(int[] arr) {
        int[] aux = new int[arr.length]; // 辅助数组
        mergeSort(arr, 0, arr.length - 1, aux);
    }

    /**
     * 自底向上（基于循环）
     * 两两归并
     *
     * @param arr 待排序数组
     */
    public static void sort2(int[] arr) {
        int[] aux = new int[arr.length];
        for (int i = 1; i < arr.length; i += i) { // i 代表"两两归并"中"两"序列的长度，初始值为1，每一轮加倍
            for (int j = 0; j < arr.length - i; j += 2 * i) { // j 代表前一个"两"序列的头元素索引
                merge(arr, j, j + i - 1, Math.min(j + 2 * i - 1, arr.length - 1), aux); // 每轮归并，最后一个子序列可能小于与其归并的序列长度，此时 hi 取 arr.length - 1
            }
        }
    }

    private static void mergeSort(int[] arr, int lo, int hi, int[] aux) {
        if (lo >= hi) { // 终止递归的条件
            return;
        }
        int mid = (lo + hi) >> 1; // 取得序列中间的元素
        mergeSort(arr, lo, mid, aux); // 对左半边递归
        mergeSort(arr, mid + 1, hi, aux); // 对右半边递归
        merge(arr, lo, mid, hi, aux); // 单趟合并
    }

    private static void merge(int[] arr, int lo, int mid, int hi, int[] aux) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1); // 将待排序序列a[lo..hi]拷贝到辅助数组的相同位置
        int i = lo; // 游标i,开始时指向待排序序列中左半边的头元素
        int j = mid + 1; // 游标j,开始时指向待排序序列中左半边的头元素
        for (int k = lo; k <= hi; k++) {
            if (i > mid) { // 左半边用尽
                arr[k] = aux[j++];
            } else if (j > hi) { // 右半边用尽
                arr[k] = aux[i++];
            } else { // 比较游标 i、j 当前指向的元素，取较小者
                arr[k] = aux[i] < aux[j] ? aux[i++] : aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        int[] array2 = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};

        sort1(array);
        System.out.println("top-down sorted array = " + Arrays.toString(array));

        sort2(array2);
        System.out.println("bottom-up sorted array = " + Arrays.toString(array2));
    }

}
