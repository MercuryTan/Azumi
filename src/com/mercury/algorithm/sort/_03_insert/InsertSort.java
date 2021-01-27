package com.mercury.algorithm.sort._03_insert;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        /* int[] arr = {-1, -5, 9, 8, 3};
        sort.deriveSelectSort(arr);
        System.out.println(Arrays.toString(arr));*/



        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        sort.trueSort(arr);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "\t 耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));
    }


    public void trueSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIdx = i - 1;
            // 如果后值比前值小，那么将前值移到后一位
            while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
                arr[insertIdx + 1] = arr[insertIdx];
                insertIdx--;
            }
            // 由于insertIdx在循环中--了，所以需要+1才是原来的位置
            arr[insertIdx + 1] = insertVal;
        }
    }


    /**
     * 推导过程
     *
     * @param arr
     */
    public void deriveSelectSort(int[] arr) {
        //1、第1次排序
        int insertVal = arr[1];
        int insertIdx = 0; // 要插入位置的前一位

        // 后值更小，那么把前值移到后一位
        while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
            arr[insertIdx + 1] = arr[insertIdx];
            insertIdx--;
        }
        // 由于insertIdx在结束循环时又减了一位，所以需要+1
        arr[insertIdx + 1] = insertVal;


        //2、第2次排序
        insertVal = arr[2];
        insertIdx = 2 - 1;

        while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
            arr[insertIdx + 1] = arr[insertIdx];
            insertIdx--;
        }
        arr[insertIdx + 1] = insertVal;

        //3、第3次排序
        insertVal = arr[3];
        insertIdx = 3 - 1;

        while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
            arr[insertIdx + 1] = arr[insertIdx];
            insertIdx--;
        }
        arr[insertIdx + 1] = insertVal;

        //4、第4次排序
        insertVal = arr[4];
        insertIdx = 4 - 1;

        while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
            arr[insertIdx + 1] = arr[insertIdx];
            insertIdx--;
        }
        arr[insertIdx + 1] = insertVal;
    }
}
