package com.daytick.util;

/**
 * @author ly
 * @since 2021/1/5 10:04 AM
 */
public class ArrayUtils {

    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     */
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     * swapTimes定义了数组的无序程度:
     * swapTimes == 0 时, 数组完全有序
     * swapTimes 越大, 数组越趋向于无序
     *
     * @param n
     * @param swapTimes
     * @param ascend
     * @return
     */
    public static int[] generateNearlyOrderedArray(int n, int swapTimes, boolean ascend) {

        int[] arr = new int[n];
        if (ascend) {
            for (int i = 0; i < n; i++)
                arr[i] = i;
        } else {
            for (int i = 0; i < n; i++)
                arr[i] = n - i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    /**
     * 生成含大量重复元素的数组
     *
     * @param n
     * @param swapTimes
     * @return
     */
    public static int[] generateLotsDuplicatedArray(int n, int swapTimes) {
        int[] arr = new int[n];
        int i = 0;
        do {
            int rand = i + (int) (Math.random() * 1000);
            for (; i < n && i <= rand; i++) {
                arr[i] = rand;
            }
        } while (i < n);

        for (int j = 0; j < swapTimes; j++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

}
