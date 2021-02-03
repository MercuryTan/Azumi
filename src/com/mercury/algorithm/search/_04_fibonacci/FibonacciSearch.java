package com.mercury.algorithm.search._04_fibonacci;


import java.util.Arrays;

public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 1333, 1680, 5001};
        System.out.println(search(arr, 1234));
    }

    /**
     * 返回斐波拉切数值
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 通过斐波拉切查找对应数值
     *
     * @param arr
     * @param searchValue
     * @return
     */
    public static int search(int[] arr, int searchValue) {

        int left = 0;
        int right = arr.length - 1;

        // 1、获取斐波拉切数列
        int fibN = 20;
        int[] fibberArr = new int[fibN];
        for (int i = 0; i < fibN; i++) {
            fibberArr[i] = fibonacci(i + 1);
        }

        // 2、根据原数组的个数，找到对应的斐波拉切数值（大于或等于原数组个数） ==》 生成新数组
        int k = 0;
        while (fibberArr[k] < arr.length) {
            k++;
        }

        int newSize = fibberArr[k];
        int[] newArr = Arrays.copyOf(arr, newSize);

        // 3、填充数据，用原数组最大值填充新数组
        if (newArr.length != arr.length) {
            for (int i = right + 1; i < newArr.length; i++) {
                newArr[i] = arr[right];
            }
        }

        // 循环查值
        while (left <= right) {
            // 4、在新数组中得到对应的mid值
            int mid = left + fibberArr[k - 1] - 1;

            // 5、根据mid处的值，和searchValue做比较
            // 如果小于中间值，那么在f(k-1)那边找
            if (searchValue < newArr[mid]) {
                right = mid - 1;
                k--;
                // 如果大于中间值，那么在f(k-2)那边找
            } else if (searchValue > newArr[mid]) {
                left = mid + 1;
                k -= 2;
                // 如果等于中间值
            } else if (searchValue == newArr[mid]) {
                // 判断是否超出原数组
                if (mid > right) {
                    return right;
                } else {
                    return mid;
                }
            }
        }

        return -1;
    }


}
