package com.mercury.algorithm.search._01_sequence;

/**
 * 顺序查找
 */
public class SequenceSearch {

    public static void main(String[] args) {
        int[] arr = {-1, 2, -5, 99, 67};
        int index = search(arr, 67);
        if (index == -1) {
            System.out.println("不存在该值");
            return;
        }
        System.out.println("下标为：" + index);
    }

    /**
     * 顺序查找，只要和value匹配就返回数组下标值
     *
     * @param arr   数组
     * @param value 需要查询的值
     * @return 数组下标 -1代表不存在
     */
    public static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
