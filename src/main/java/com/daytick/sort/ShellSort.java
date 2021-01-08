package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;
import static com.daytick.util.SortUtils.swap;

/**
 * 【希尔排序】<p>
 * 希尔排序法又叫“缩小增量排序法”, 是对直接插入排序法的优化:<p>
 * 通过设置一个增量，对原始序列进行分组，对每组用直接插入排序法排序再整合，再缩小增量，周而复始直至增量为1，完成排序。<p>
 * 最后增量变为1的时候，它就是直接插入排序，这时候整个序列基本上是有序的，需要交换的数据已经非常少了。<p>
 *
 * @author ly
 * @since 2021/1/7 10:44 AM
 */
public class ShellSort {

    public static void sort(int[] arr) {
        // 步长系数 step
        int step = 3;
        // 确定最大步长 h
        int h = 1, len = arr.length;
        while (h < len / step) {
            h = h * step + 1;
        }
        // 依次取不同的步长，做插入排序
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h /= step;
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));
    }

}
