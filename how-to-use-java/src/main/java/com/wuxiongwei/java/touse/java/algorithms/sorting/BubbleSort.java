package com.wuxiongwei.java.touse.java.algorithms.sorting;

/**
 使用冒泡排序对数组进行排序。背后的想法
      冒泡排序是为了寻找相邻的索引
      错位并交换元素
      直到整个数组被排序为止。
 */
import java.util.Random;

public class BubbleSort {


    public static void bubbleSort(int[] ar) {
        if (ar == null) return;

        final int N = ar.length;
        boolean sorted;

        do {
            sorted = true;
            for (int i = 1; i < N; i++) {
                if (ar[i] < ar[i - 1]) {
                    swap(ar, i - 1, i);
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {10, 4, 6, 8, 13, 2, 3};
        bubbleSort(array);
        System.out.println(java.util.Arrays.toString(array));
        runTests();
    }

    static Random RANDOM = new Random();

    public static void runTests() {
        final int NUM_TESTS = 1000;
        for (int i = 1; i <= NUM_TESTS; i++) {

            int[] array = new int[i];
            for (int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
            int[] arrayCopy = array.clone();

            bubbleSort(array);
            java.util.Arrays.sort(arrayCopy);

            if (!java.util.Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
        }
    }

    static int randInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
