package com.daytick.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.daytick.util.SortUtils.isSorted;

/**
 * 【桶排序】<p>
 * 桶排序是计数排序的升级版，它将数据分到有限数量的桶里，每个桶再分别排序<p>
 * 算法过程:<p>
 * - 设置一个定量的数组当作空桶<p>
 * - 遍历输入数据，并且把数据一个一个放到对应的桶里去<p>
 * - 对每个不是空的桶进行排序<p>
 * - 从不是空的桶里把排好序的数据拼接起来
 *
 * @author ly
 * @since 2021/1/7 10:44 AM
 */
public class BucketSort {

    /**
     * 设置桶的默认数量为5
     */
    private static final int DEFAULT_BUCKET_SIZE = 5;

    public static void sort(int[] arr) {
        // 确定元素范围
        int min = arr[0], max = arr[0];
        for (int e : arr) {
            if (max < e) {
                max = e;
            }
            if (min > e) {
                min = e;
            }
        }
        int range = max - min;
        // 初始化桶
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>(DEFAULT_BUCKET_SIZE);
        for (int i = 0; i < DEFAULT_BUCKET_SIZE; i++) {
            buckets.add(new LinkedList<>());
        }
        // 将待排序元素放入桶中并排序
        for (int e : arr) {
            int index = (e - min) * (DEFAULT_BUCKET_SIZE - 1) / range; // 给元素分配桶（实际开发要根据场景设计分配算法，尽量接近均匀分配）
            insertAndSort(buckets.get(index), e); // 插入元素并排序
        }
        // 将桶中元素一一放入原数组
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            for (Integer e : bucket) {
                arr[index++] = e;
            }
        }
    }

    /**
     * 插入元素并排序
     *
     * @param bucket 桶
     * @param e      元素
     */
    private static void insertAndSort(LinkedList<Integer> bucket, int e) {
        ListIterator<Integer> it = bucket.listIterator();
        boolean hasInserted = false;
        while (it.hasNext()) {
            if (e <= it.next()) {
                it.previous(); // 返回上一个节点
                it.add(e);
                hasInserted = true;
                break;
            }
        }
        // e 为当前桶中最大元素，添加到桶末尾
        if (!hasInserted) {
            bucket.add(e);
        }
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));
    }

}
