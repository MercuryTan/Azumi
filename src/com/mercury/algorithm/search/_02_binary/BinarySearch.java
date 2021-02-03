package com.mercury.algorithm.search._02_binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        /**
         * 找到一个值
         */
        int[] arr = {1, 3, 6, 7, 16, 80};
        int index = search(arr, 16, 0, arr.length - 1);
        System.out.println(index);

        /**
         * 找到所有的值
         */
        int[] arr2 = {1, 5, 8, 10, 13, 13, 13, 17};
        List list = searchAll(arr2, 13, 0, arr2.length - 1);
        System.out.println(Arrays.toString(list.toArray()));
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


    /**
     * 找到所有的value值
     *
     * @param arr
     * @param value
     * @param left
     * @param right
     * @return
     */
    public static List searchAll(int[] arr, int value, int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        // 向左递归
        if (arr[mid] > value) {
            return searchAll(arr, value, left, mid - 1);
            //向右递归
        } else if (arr[mid] < value) {
            return searchAll(arr, value, mid + 1, right);
        } else if (arr[mid] == value) {
            ArrayList<Object> list = new ArrayList<>();
            list.add(mid);

            // 往左看看还有没有相同的值
            int temp = mid - 1;
            while (left <= temp) {
                if (arr[temp] == value) {
                    list.add(temp);
                }
                temp--;
            }

            // 往右看看还有没有相同的值
            temp = mid + 1;
            while (right >= temp) {
                if (arr[temp] == value) {
                    list.add(temp);
                }
                temp++;
            }

            return list;
        }

        return null;
    }
}
