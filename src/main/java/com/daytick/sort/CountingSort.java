package com.daytick.sort;

import java.util.Arrays;

/**
 * 【计数排序】<p>
 * 计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。<p>
 * 作为一种线性时间复杂度的排序，它要求输入的数据必须是有确定范围的整数。<p>
 * 算法步骤:<p>
 * - 1.找出待排序的数组中最大和最小的元素<p>
 * - 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项<p>
 * - 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加<p>
 * - 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
 *
 * @author ly
 * @since 2021/1/6 9:26 AM
 */
public class CountingSort {

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
