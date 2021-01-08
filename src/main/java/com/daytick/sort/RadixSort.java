package com.daytick.sort;

import java.util.Arrays;

import static com.daytick.util.SortUtils.isSorted;

/**
 * 【基数排序】<p>
 * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前<p>
 * 算法步骤:<p>
 * - 1.取得数组中的最大数，并取得位数<p>
 * - 2.arr为原始数组，从最低位开始取每个位组成radix数组<p>
 * - 3.对radix进行计数排序（利用计数排序适用于小范围数的特点）<p>
 *
 * @author ly
 * @since 2021/1/7 10:44 AM
 */
public class RadixSort {

    // 十进制基数
    private static final int DEC_RADIX = 10;

    /**
     * 基数排序
     *
     * @param arr 待排序的数组
     * @param max 数组中最大的数有几位
     */
    public static void sort(int[] arr, int max) {
        // 用来计数
        int[] count = new int[DEC_RADIX];
        // 用来当桶
        int[] bucket = new int[arr.length];
        // k: 1-个位, 2-十位, 3-百位...
        for (int k = 1; k <= max; k++) {
            // 初始化计数数组
            for (int i = 0; i < DEC_RADIX; i++) {
                count[i] = 0;
            }
            // 统计 k 位上的数字出现频率
            for (int e : arr) {
                count[getDigit(e, k)]++;
            }
            // 确定右边界（如，循环结束后，count[0] 表示 k 位数字为 0 的数字的右边界索引，不包含count[0]）
            for (int i = 1; i < DEC_RADIX; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从后往前放入桶中，从而保持相等元素的相对顺序
            for (int i = arr.length - 1; i >= 0; i--) {
                int d = getDigit(arr[i], k);
                bucket[count[d] - 1] = arr[i]; // 数字 d 对应的右边界索引 count[d] - 1
                count[d]--; // 未排序的右边界左移一位
            }
            // 将 bucket 中排好序的元素一一复制到原数组
            System.arraycopy(bucket, 0, arr, 0, bucket.length);
        }
    }

    /**
     * 获取整数 num 第 k 位的数字
     *
     * @param num 整数
     * @param k   1-个位, 2-十位, 3-百位...
     * @return
     */
    private static int getDigit(int num, int k) {
        int p = (int) Math.pow(10, k - 1);
        return num / p % 10;
    }

    public static void main(String[] args) {
        int[] array = {37, 0, 26, 46, 9, 27, 8, 50, 26, 4};
        sort(array, 2);
        System.out.println("array = " + Arrays.toString(array) + ", sorted = " + isSorted(array));
    }

}
