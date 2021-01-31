package com.mercury.algorithm.sort._06_merge;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
       /* int[] arr = {4, 7, 6, 0, 5, 3, 2, 8, 1, 9};
        int[] tmpArr = new int[arr.length];
        mergeSort(arr, 0, 9, tmpArr);

        System.out.println(Arrays.toString(arr));
*/

        int[] arr = new int[80000];
        int[] tmpArr = new int[arr.length];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, tmpArr);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "\t 耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分治法递归调用方法
     *
     * @param arr
     * @param left
     * @param right
     * @param tmpArr
     */
    public static void mergeSort(int[] arr, int left, int right, int[] tmpArr) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, tmpArr);
            mergeSort(arr, mid + 1, right, tmpArr);
            merge(arr, left, mid, right, tmpArr);
        }
    }

    /**
     * 合并数组
     *
     * @param arr    原数组
     * @param left   左边数组第一个元素指针
     * @param mid    左边数组最后一个元素指针
     * @param right  右边数组最后一个元素指针
     * @param tmpArr 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tmpArr) {
        int i = left;
        int j = mid + 1;
        int t = 0; //临时数组指针
        // 1、比较i，j值，如果小的就放到临时数组中
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmpArr[t] = arr[i];
                t++;
                i++;
            }

            if (arr[i] > arr[j]) {
                tmpArr[t] = arr[j];
                t++;
                j++;
            }
        }

        // 2、将两数组中剩余元素放到临时数组中
        //如果左边数组多
        while (i <= mid) {
            tmpArr[t] = arr[i];
            t++;
            i++;
        }
        //如果右边数组多
        while (j <= right) {
            tmpArr[t] = arr[j];
            t++;
            j++;
        }

        // 3、将临时数组中的值移到原数组中
        int tmpLeft = left;
        t = 0;
//        System.out.println("left=" + left + " right=" + right);
        while (tmpLeft <= right) {
            arr[tmpLeft++] = tmpArr[t++];
        }
    }
}