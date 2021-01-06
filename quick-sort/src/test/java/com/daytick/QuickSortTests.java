package com.daytick;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.daytick.QuickSort.*;

/**
 * @author ly
 * @since 2021/1/5 9:32 AM
 */
public class QuickSortTests {

    @Test
    public void test() {
        System.out.println("----------------随机数组-----------------");
        testSort(QuickSort.class, generateRandomArray(100000, -500, 500));
        System.out.println("========================================");

        System.out.println("----------------总体升序-----------------");
        testSort(QuickSort.class, generateNearlyOrderedArray(100000, 50, true));
        System.out.println("========================================");

        System.out.println("----------------基本降序-----------------");
        testSort(QuickSort.class, generateNearlyOrderedArray(100000, 50, false));
        System.out.println("========================================");

        System.out.println("----------------大量重复-----------------");
        testSort(QuickSort.class, generateDuplicatedArray(50000, 20));
        System.out.println("========================================");
    }

    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        return arr;
    }

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

    public static int[] generateDuplicatedArray(int n, int swapTimes) {
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

    public static boolean isSorted(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > (arr[i + 1]))
                return false;
        return true;
    }

    public static void testSort(Class<?> sortClass, int[] arr) {
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        int[] arr4 = Arrays.copyOf(arr, arr.length);
        int[] arr5 = Arrays.copyOf(arr, arr.length);

        long startTime = System.currentTimeMillis();
        sort1(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        assert isSorted(arr);
        System.out.println(sortClass.getSimpleName() + ".sort1()" + " : " + (endTime - startTime) + "ms");

        long startTime2 = System.currentTimeMillis();
        sort2(arr2, 0, arr2.length - 1);
        long endTime2 = System.currentTimeMillis();
        assert isSorted(arr);
        System.out.println(sortClass.getSimpleName() + ".sort2()" + " : " + (endTime2 - startTime2) + "ms");

        long startTime3 = System.currentTimeMillis();
        sort3(arr3, 0, arr3.length - 1);
        long endTime3 = System.currentTimeMillis();
        assert isSorted(arr);
        System.out.println(sortClass.getSimpleName() + ".sort3()" + " : " + (endTime3 - startTime3) + "ms");

        long startTime4 = System.currentTimeMillis();
        sort4(arr4, 0, arr4.length - 1);
        long endTime4 = System.currentTimeMillis();
        assert isSorted(arr);
        System.out.println(sortClass.getSimpleName() + ".sort4()" + " : " + (endTime4 - startTime4) + "ms");

        long startTime5 = System.currentTimeMillis();
        sort5(arr5, 0, arr5.length - 1);
        long endTime5 = System.currentTimeMillis();
        assert isSorted(arr);
        System.out.println(sortClass.getSimpleName() + ".sort5()" + " : " + (endTime5 - startTime5) + "ms");
    }

}
