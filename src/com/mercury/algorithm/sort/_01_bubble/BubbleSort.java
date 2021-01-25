package com.mercury.algorithm.sort._01_bubble;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
//        normalBubble();

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt();
        }
        long begin = System.currentTimeMillis();
        betterBubble(arr);
        long end = System.currentTimeMillis();

        long betweenMs = end - begin;
        long second = betweenMs / 1000;
        System.out.println("begin:" + begin + "\t end:" + end + "耗费时间：" + betweenMs + "ms;" + second + "秒");
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 优化后： 如果在循环中没有交换，那么可以直接退出
     */
    private static void betterBubble(int[] arr) {
        int temp = 0;
        /**
         * 优化：根据flag判断是否有过交换，false:没有交换，可以直接退出
         */
        boolean flag = false;
        // 大循环排序5次
        for (int i = 0; i < arr.length - 1; i++) {
            // 内循环次数依次减少
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的值大于后面的值，就交换==》把大的移到后面
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
            // 如果经过内层循环后，标志位还是false，说明已经可以结束外层循环了
            if (!flag) {
                break;
            } else {
                flag = false; // 注意要重置!!!!!
            }
        }
    }


    /**
     * 优化前冒泡
     * 在后面的循环中没有交换的时候，还是会进行内循环比较
     */
    private static void normalBubble() {
        int[] arr = {-2, -1, 5, 3, 1};
        int temp = 0;
        // 大循环排序5次
        for (int i = 0; i < arr.length - 1; i++) {
            // 内循环次数依次减少
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的值大于后面的值，就交换==》把大的移到后面
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }


        System.out.println(Arrays.toString(arr));
    }
}
