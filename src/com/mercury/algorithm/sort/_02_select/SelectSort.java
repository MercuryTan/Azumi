package com.mercury.algorithm.sort._02_select;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序： 先找到最小值的下标，再进行交换
 */
public class SelectSort {
    public static void main(String[] args) {
        SelectSort sort = new SelectSort();
        /*
        int[] arr = {-1, -5, 9, 8, 3};
        sort.deriveSelectSort(arr);
*/

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        sort.trueSelectSort(arr);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "\t 耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     *  找到最小值下标，再和前面的交换
     * @param arr
     */
    public void trueSelectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 先假定第一位为最小值
            int minIndex = i;
            int min = arr[i];
            // 从后一位开始比较
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }

            // 如果最小值索引，就是第一位，那么就不用再交换
            if(minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

        }

//        System.out.println(Arrays.toString(arr));
    }


    /**
     * 推导过程
     */
    public void deriveSelectSort(int[] arr) {
        // 假设数组为: arr = {-1, -5, 9, 8, 3}

        // 1、第一次排序
        // 1.1 假设第一个下标的是最小值
        int minIndex = 0;
        int min = arr[0];
        // 1.2 从第二个开始和min做比较,如果min比当前遍历的大：说明最小的竟还有其它值
        for (int i = 0 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                minIndex = i;
                min = arr[i];
            }
        }
        //1.3 经过上面的步骤，就找到了最小值的索引minIndex
        //1.4 将minIndex和第1个位置的进行交换
        int temp = arr[0];
        arr[0] = arr[minIndex];
        arr[minIndex] = temp;

        System.out.println("第1次循环：" + Arrays.toString(arr));


        // 2、第2次排序
        // 2.1 【假设第2个下标的是最小值】
        minIndex = 1;
        min = arr[1];
        // 2.2 从第3个开始和min做比较,如果min比当前遍历的大：说明最小的竟还有其它值
        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                minIndex = i;
                min = arr[i];
            }
        }
        //2.3 经过上面的步骤，就找到了最小值的索引minIndex
        //2.4 将minIndex和第2个位置的进行交换
        temp = arr[1];
        arr[1] = arr[minIndex];
        arr[minIndex] = temp;

        System.out.println("第2次循环：" + Arrays.toString(arr));


        // 3、第3次排序
        // 3.1 假设第3个下标的是最小值
        minIndex = 2;
        min = arr[2];
        // 3.2 从第3个开始和min做比较,如果min比当前遍历的大：说明最小的竟还有其它值
        for (int i = 2 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                minIndex = i;
                min = arr[i];
            }
        }
        //3.3 经过上面的步骤，就找到了最小值的索引minIndex
        //3.4 将minIndex和第3个位置的进行交换
        temp = arr[2];
        arr[2] = arr[minIndex];
        arr[minIndex] = temp;

        System.out.println("第3次循环：" + Arrays.toString(arr));

        // 4、第4次排序
        // 4.1 假设第4个下标的是最小值
        minIndex = 3;
        min = arr[3];
        // 3.2 从第5个开始和min做比较,如果min比当前遍历的大：说明最小的竟还有其它值
        for (int i = 3 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                minIndex = i;
                min = arr[i];
            }
        }
        //4.3 经过上面的步骤，就找到了最小值的索引minIndex
        //4.4 将minIndex和第4个位置的进行交换
        temp = arr[3];
        arr[3] = arr[minIndex];
        arr[minIndex] = temp;

        System.out.println("第4次循环：" + Arrays.toString(arr));

    }
}
