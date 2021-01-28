package com.mercury.algorithm.sort._04_shell;

import java.util.Arrays;
import java.util.Random;

public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSort sort = new ShellSort();
//        sort.shellSort(arr);
//        sort.trueShellSort(arr);


        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        sort.trueShellSort(arr);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "\t 耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));
    }

    public void trueShellSort(int[] arr) {
        for (int inc = arr.length / 2; inc >= 1; inc /= 2) {
            // 每一次分组(为什么要等于inc，因为插入排序中，是把前面的元素当成有序列表，是从无序列表中选择第一个元素进行插入)
            for (int i = inc; i < arr.length; i++) {
                //j为待插入数据的前一位，j+inc为待插入数据
                for (int j = i - inc; j >= 0; j -= inc) {
                    if (arr[j + inc] < arr[j]) {
                        int insertVal = arr[j + inc];
                        arr[j + inc] = arr[j];
                        arr[j] = insertVal;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * 推导过程
     *
     * @param arr
     */
    public void shellSort(int[] arr) {
        /**
         * 每个分组不是一次执行完插入排序的，是A组做一次排序，B组再做一次排序。交替进行的
         */

        //第1轮: 5组
        for (int i = 5; i < arr.length; i++) {
            // j：要插入位置的前一位  j+5:当前要插入的位置 ==> 即j为有序序列 j+5是待插入的数据
            for (int j = i - 5; j >= 0; j -= 5) {
                //交换： 如果当前插入位置小于前一步长位置，那么就进行交换
                if (arr[j + 5] < arr[j]) {
                    // 交换
                    int insertVal = arr[j + 5];
                    arr[j + 5] = arr[j];
                    arr[j] = insertVal;
                }
            }
        }
        System.out.println(Arrays.toString(arr));


        //第2轮: 2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j + 2] < arr[j]) {
                    int insertVal = arr[j + 2];
                    arr[j + 2] = arr[j];
                    arr[j] = insertVal;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //第3轮: 1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j + 1] < arr[j]) {
                    int insertVal = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = insertVal;
                }
            }
        }
        System.out.println(Arrays.toString(arr));


    }
}
