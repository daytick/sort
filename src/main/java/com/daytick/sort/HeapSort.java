package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;

/**
 * 【堆排序】<p>
 * 将一个数组构造成为一个最大堆，然后以类似于每次在堆中将头元素(当前堆中最大值)放入末尾并删除的操作完成排序<p>
 *
 * @author ly
 * @since 2021/1/7 10:44 AM
 */
public class HeapSort {

    public static void sort(int[] arr) {

    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));
    }

}
