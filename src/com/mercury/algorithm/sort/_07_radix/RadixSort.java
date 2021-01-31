package com.mercury.algorithm.sort._07_radix;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {332, 653, 632, 755, 433, 1, 722, 48};
//        sort2(arr);

        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        /**
         * 10个桶，比如buckets[0]代表第一个桶，桶中元素为arr.length个（防止所有元素都在同一桶中）
         */
        int[][] buckets = new int[10][arr.length];
        /**
         * 每个桶中元素的个数,比如countArr[0] 代表第一个桶中元素的个数 位数
         */
        int[] countArr = new int[10];

        // 找到最大数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();
        for (int l = 0, n = 1; l < maxLength; l++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / n % 10;
                // 在第digit桶的 第countArr[digit] 位置加入当前值
                buckets[digit][countArr[digit]++] = arr[i];
            }

            // 2、将桶中元素放入到原数组中
            int index = 0;
            for (int j = 0; j < countArr.length; j++) {
                // 第j个桶中有元素
                int length = countArr[j];
                if (length != 0) {
                    // 将第j个桶的元素全部放置到原数组中
                    for (int k = 0; k < length; k++) {
                        arr[index++] = buckets[j][k];
                    }
                }
                countArr[j] = 0; // 置空！！！！！！
            }
        }
    }


    /**
     * 基数排序推导过程
     *
     * @param arr 原数组  比如： 332,653,632,755,433,1,722,48
     */
    public static void sort2(int[] arr) {
        /**
         * 10个桶，比如buckets[0]代表第一个桶，桶中元素为arr.length个（防止所有元素都在同一桶中）
         */
        int[][] buckets = new int[10][arr.length];
        /**
         * 每个桶中元素的个数,比如countArr[0] 代表第一个桶中元素的个数 位数
         */
        int[] countArr = new int[10];

        //一、第一次排序
        // 1、根据位数往桶中放值
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] / 1 % 10;
            // 在第digit桶的 第countArr[digit] 位置加入当前值
            buckets[digit][countArr[digit]++] = arr[i];
        }

        // 2、将桶中元素放入到原数组中
        int index = 0;
        for (int j = 0; j < countArr.length; j++) {
            // 第j个桶中有元素
            int length = countArr[j];
            if (length != 0) {
                // 将第j个桶的元素全部放置到原数组中
                for (int k = 0; k < length; k++) {
                    arr[index++] = buckets[j][k];
                }
            }
            countArr[j] = 0; // 置空！！！！！！
        }


        System.out.println(Arrays.toString(arr));

        //二、第2次排序
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] / 10 % 10;
            // 在第digit桶的 第countArr[digit] 位置加入当前值
            buckets[digit][countArr[digit]++] = arr[i];
        }

        // 2、将桶中元素放入到原数组中
        index = 0;
        for (int j = 0; j < countArr.length; j++) {
            // 第j个桶中有元素
            int length = countArr[j];
            if (length != 0) {
                // 将第j个桶的元素全部放置到原数组中
                for (int k = 0; k < length; k++) {
                    arr[index++] = buckets[j][k];
                }
            }
            countArr[j] = 0;
        }
        System.out.println(Arrays.toString(arr));

        //三、第3次排序
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] / 100 % 10;
            // 在第digit桶的 第countArr[digit] 位置加入当前值
            buckets[digit][countArr[digit]++] = arr[i];
        }

        // 2、将桶中元素放入到原数组中
        index = 0;
        for (int j = 0; j < countArr.length; j++) {
            // 第j个桶中有元素
            int length = countArr[j];
            if (length != 0) {
                // 将第j个桶的元素全部放置到原数组中
                for (int k = 0; k < length; k++) {
                    arr[index++] = buckets[j][k];
                }
            }
            countArr[j] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }
}