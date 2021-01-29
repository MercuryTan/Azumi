package com.mercury.algorithm.sort._05_quick;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
       /* int[] arr = {4, 7, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, 7);
        System.out.println(Arrays.toString(arr));
*/

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "\t 耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 分治法：递归进行快排
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归退出条件
        if (startIndex >= endIndex) {
            return;
        }

        int pivotIndex = pivot(arr, startIndex, endIndex);
        // 分治法
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }


    /**
     * 基准值:arr[startIndex]
     * <p>
     * 1、交换left，right的值
     * left指针。从第一位开始比较，arr[left]小于小于等于基准值，那么left++。直到找到比基准值大的为止
     * right指针。从最后一位开始比较，arr[right]大于基准值，那么right--。直到找到比基准值小的为止
     * <p>
     * 2、最后交换基准值和left的值
     * arr[startIndex] = arr[left]
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int pivot(int[] arr, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivotVal = arr[startIndex];
        int temp = 0;
        while (left != right) {
            while (left < right && arr[right] > pivotVal) {
                right--;
            }

            while (left < right && arr[left] <= pivotVal) {
                left++;
            }

            // 只有left<right时，才交换left,right的值
            if (left < right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // 交换基准值
        arr[startIndex] = arr[left];
        arr[left] = pivotVal;

        return left;
    }
}
