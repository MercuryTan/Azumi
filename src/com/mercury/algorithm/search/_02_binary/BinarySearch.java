package com.mercury.algorithm.search._02_binary;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 7, 16, 80};
        int index = search(arr, 16, 0, arr.length-1);
        System.out.println(index);
    }

    /**
     * 思路：
     * 1、如果 arr[mid]>value,那么查 left到 mid-1
     * 2、如果 arr[mid]<value,那么查 mid 到 right
     * 3、如果 arr[mid] = value,那么返回mid
     * <p>
     * 递归退出条件：
     * 1、找到了值 为mid
     * 2、数组都遍历完了
     *
     * @param arr   当前数组
     * @param value 需要查的值
     * @param left  左边指针
     * @param right 右边指针
     * @return
     */
    public static int search(int[] arr, int value, int left, int right) {

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
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
