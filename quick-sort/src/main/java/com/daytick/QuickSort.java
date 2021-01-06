package com.daytick;

/**
 * 快速排序（从小到大）
 *
 * @author ly
 * @since 2021/1/5 9:18 AM
 */
public class QuickSort {

    /**
     * 单路（直接选取左端点作为 pivot）
     *
     * @param arr   待排序数组
     * @param start 起始索引
     * @param end   终止索引
     */
    static void sort1(int[] arr, int start, int end) {
        // 特殊情况提前返回
        if (arr == null || arr.length <= 1 || start >= end) {
            return;
        }

        // 选择左端点作为pivot
        int pivot = arr[start];
        // 定义边界
        int bound = start;

        for (int i = start + 1; i <= end; i++) {
            // 小于 pivot 的放左边
            if (arr[i] < pivot) {
                swap(arr, i, ++bound);
            }
        }

        // 交换 pivot（左端点） 和 bound
        swap(arr, start, bound);

        // 递归左右子序列
        sort1(arr, start, bound - 1);
        sort1(arr, bound + 1, end);
    }

    /**
     * 单路优化版（随机选取 pivot）
     * 防止当数据接近有序时退化到O(n2)
     */
    static void sort2(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 1 || start >= end) {
            return;
        }

        // 伪随机 pivot
        int r = (int) (start + Math.random() * (end - start + 1));
        // 交换左端点数和 r 所在的数
        swap(arr, r, start);

        int pivot = arr[start];
        int bound = start;


        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, ++bound);
            }
        }

        swap(arr, start, bound);

        sort2(arr, start, bound - 1);
        sort2(arr, bound + 1, end);
    }

    /**
     * 双路【掌握】
     * 防止当数据重复值很多时退化到O(n2)
     */
    static void sort3(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 1 || start >= end) {
            return;
        }

        int r = (int) (start + Math.random() * (end - start + 1));
        swap(arr, start, r);

        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (true) {
            // 遇到相等的情况时while循环就会退出，使得对于包含大量相同元素的数组，双方相等的数据就会交换，可以一定程度保证两路的数据量平衡。
            // 从 left 开始向后遍历，如果遍历的元素 e < pivot，则继续向后遍历，直到遍历的元素 e >= pivot，则停止遍历。
            while (left <= end && arr[left] < pivot)
                left++;
            // 从 right 开始向前遍历，如果遍历的元素 e > pivot，则继续向前遍历，直到遍历的元素 e <= pivot，则停止遍历。
            while (right >= start + 1 && arr[right] > pivot)
                right--;
            if (left >= right)
                break;
            swap(arr, left++, right--);
        }
        // 【特别注意】：此时 right 指向的元素是数组中最后一个小于 pivot 的元素, left 指向的元素是数组中第一个大于 pivot 的元素，故使用 right。
        swap(arr, start, right);
        sort3(arr, start, right - 1);
        sort3(arr, right + 1, end);
    }

    /**
     * 双路优化版【重点掌握】
     * 插入排序在小数组的排序上是非常高效的，在快速排序递归的子序列，如果序列规模足够小（数组大小小于7），可以使用插入排序替代快速排序
     */
    static void sort4(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 1 || start >= end) {
            return;
        }
        // 在数组大小小于7的情况下使用插入排序
        if (end - start + 1 < 7) {// {15,6,12}
            for (int i = start + 1; i <= end; i++) {
                for (int j = i; j > start && arr[j - 1] > arr[j]; j--) {
                    swap(arr, j, j - 1);
                }
            }
            return;
        }

        int r = (int) (start + Math.random() * (end - start + 1));
        swap(arr, r, start);

        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (true) {
            while (left <= end && arr[left] < pivot)
                left++;
            while (right >= start + 1 && arr[right] > pivot)
                right--;
            if (right <= left)
                break;
            swap(arr, left++, right--);
        }
        swap(arr, right, start);
        sort4(arr, start, right - 1);
        sort4(arr, right + 1, end);
    }

    /**
     * 双路进一步优化版【可选】
     * 使用"中数选择法"替代"随机数产生器"
     * 1. 小于 7 的数组则直接使用插入排序
     * 2. 等于 7 的数组直接选择中数
     * 3. 在 7 到 40 之间的数组使用 median-of-three 选择中数
     * 4. 大于 40 的数组使用 median-of-nine 选择 pivot
     */
    static void sort5(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 1 || start >= end) {
            return;
        }

        // 计算数组长度
        int len = end - start + 1;
        // 在数组大小小于7的情况下使用插入排序
        if (len < 7) {// {15,6,12}
            for (int i = start + 1; i <= end; i++) {
                for (int j = i; j > start && arr[j - 1] > arr[j]; j--) {
                    swap(arr, j, j - 1);
                }
            }
            return;
        }

        // 大小等于7的数组直接选择中数
        int m = start + (len >> 1);
        // 大小大于7
        if (len > 7) {
            int l = start;
            int n = start + len - 1;
            if (len > 40) { // 大数组，采用median-of-nine选择
                int s = len / 8;
                l = med3(arr, l, l + s, l + 2 * s); // 取样左端点3个数并得出中数
                m = med3(arr, m - s, m, m + s); // 取样中点3个数并得出中数
                n = med3(arr, n - 2 * s, n - s, n); // 取样右端点3个数并得出中数
            }
            m = med3(arr, l, m, n); // 取中数中的中数,median-of-three
        }
        // 交换pivot到左端点
        swap(arr, start, m);

        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (true) {
            while (left <= end && arr[left] < pivot)
                left++;
            while (right >= start + 1 && arr[right] > pivot)
                right--;
            if (right <= left) {
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        swap(arr, right, start);
        sort5(arr, start, right - 1);
        sort5(arr, right + 1, end);
    }

    private static int med3(int[] x, int a, int b, int c) {
        return x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a)
                : x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
