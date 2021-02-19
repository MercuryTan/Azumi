package com.mercury.algorithm.sort._08_heap;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {3, 6, 8, 5, 9};
        heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        //1、将无序数组转换成大顶堆
        transformBig(arr);

        //2、交换树顶值和当前大顶堆最后一个值；并且重新构造大顶堆
        for (int i = arr.length - 1; i > 1; i--) {
            // 2.1 交换 第一位和新大顶堆最后一位的值
            swap(arr, 0, i);
            // 2.2 重新构造大顶堆
            heapify(arr, 0, i - 1);
        }

    }

    /**
     * 重新构造大顶堆 (下沉)
     *
     * @param arr
     * @param index 新大顶堆起始位置
     * @param size  新大顶堆结束位置
     */
    private static void heapify(int[] arr, int index, int size) {
        // 起始位置的左右节点
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largeIndex = getLargeIndex(arr, left, right, index);
            // 说明当前父节点就是最大值，就退出循环
            if (largeIndex == index) {
                break;
            }
            // 子节点大，那么交换父节点和子节点
            swap(arr, index, largeIndex);

            index = largeIndex;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }
    /**
     * 获取当前三个节点中最大的索引值
     *
     * @param arr
     * @param left
     * @param right
     * @param index
     * @return
     */
    private static int getLargeIndex(int[] arr, int left, int right, int index) {
        int largeIndex;
        if (arr[left] > arr[right]) {
            largeIndex = left;
        } else {
            largeIndex = right;
        }

        if (arr[index] > arr[largeIndex]) {
            largeIndex = index;
        }

        return largeIndex;
    }


    /**
     * 1、将无需数组转换成大顶堆
     * 按照插入的值和父节点判断
     * 【退出条件】： 父节点的值大于等于当前值
     *
     * @param arr
     */
    private static void transformBig(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currIndex = i;
            int fatherIndex = (currIndex - 1) / 2;

            while (arr[currIndex] > arr[fatherIndex]) {
                swap(arr, currIndex, fatherIndex);
                // 移动当前索引和父索引，继续比较
                currIndex = fatherIndex;
                fatherIndex = (fatherIndex - 1) / 2;
            }
        }
    }

    /**
     * 交换值: 交换one和two的值
     *
     * @param arr 数组
     * @param one 原位置
     * @param two 当前位置
     */
    private static void swap(int[] arr, int one, int two) {
        int temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }


}
