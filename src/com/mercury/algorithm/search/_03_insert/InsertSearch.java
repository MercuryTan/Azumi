package com.mercury.algorithm.search._03_insert;

/**
 * 插值查找
 * 与二分查找只是mid的取值区别
 */
public class InsertSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = search(arr, 9, 0, arr.length - 1);
        System.out.println(index);
    }


    /**
     * mid取值为： left + (value-arr[left])/(arr[right]-arr[left])  *(right-left)
     * 备注： 二分查找中mid = left +1/2 （right-left）
     *
     * @param arr
     * @param value
     * @param left
     * @param right
     * @return
     */
    public static int search(int[] arr, int value, int left, int right) {

        if (left > right) {
            return -1;
        }

        int mid = left + ((value - arr[left]) / (arr[right] - arr[left])) * (right - left);
        // 往左查询
        if (arr[mid] > value) {
            return search(arr, value, left, mid - 1);
        } else if (arr[mid] < value) {
            return search(arr, value, mid + 1, right);
        } else if (arr[mid] == value) {
            return mid;
        }

        return -1;
    }
}
