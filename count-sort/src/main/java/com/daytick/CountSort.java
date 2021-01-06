package com.daytick;

import java.util.Arrays;

/**
 * @author ly
 * @since 2021/1/6 9:26 AM
 */
public class CountSort {

    public static void sort(int[] arr) {
        // 找出数组的最大值和最小值
        int max = arr[0], min = arr[0];
        for (int e : arr) {
            if (max < e) {
                max = e;
            }
            if (min > e) {
                min = e;
            }
        }

        // 根据最大最小值计算出的长度创建辅助数组
        int[] aux = new int[max - min + 1];

        // 辅助数组用来记录原始数据中每个值出现的频率
        for (int e : arr) {
            aux[e - min] += 1;
        }

        // 写入原数组，完成排序
        int index = 0; // 记录当前写入下标
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i]; j++) {
                arr[index++] = i + min;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array);
        System.out.println("sorted array = " + Arrays.toString(array));
    }

}
