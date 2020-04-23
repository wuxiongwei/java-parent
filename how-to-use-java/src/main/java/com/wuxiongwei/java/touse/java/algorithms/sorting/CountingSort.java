package com.wuxiongwei.java.touse.java.algorithms.sorting;

/**
 计数排序！

 */
public class CountingSort {
    // 在O（n + maxVal-maxVal）中对[minVal，maxVal]范围内的值进行排序
    public static void countingSort(int[] ar, int minVal, int maxVal) {
        int sz = maxVal - minVal + 1;
        int[] B = new int[sz];
        for (int i = 0; i < ar.length; i++) B[ar[i] - minVal]++;
        for (int i = 0, k = 0; i < sz; i++) while (B[i]-- > 0) ar[k++] = i + minVal;
    }

    public static void main(String[] args) {

//        我们正在排序的数字的最大值和最小值。
//         您需要提前知道上下限
//         您要计算的要排序的数字可以正常工作。
        final int MIN_VAL = -10;
        final int MAX_VAL = +10;

        int[] nums = {+4, -10, +0, +6, +1, -5, -5, +1, +1, -2, 0, +6, +8, -7, +10};
        countingSort(nums, MIN_VAL, MAX_VAL);

        // prints [-10, -7, -5, -5, -2, 0, 0, 1, 1, 1, 4, 6, 6, 8, 10]
        System.out.println(java.util.Arrays.toString(nums));
    }
}
