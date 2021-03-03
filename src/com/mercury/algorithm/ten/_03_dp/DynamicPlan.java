package com.mercury.algorithm.ten._03_dp;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * dp
 */
public class DynamicPlan {

    public static void main(String[] args) {
        int n = 5; // 5个金矿
        int w = 10; // 现在有10人
        int[] g = {400, 500, 200, 300, 350}; // 每个金矿对应的产量
        int[] p = {5, 5, 3, 4, 3};  // 每个金矿对应需要的人数

        getMostGold(n, w, g, p);
    }

    /**
     * @param n 金矿个数
     * @param w 现有人数
     * @param g 每个金矿的产量
     * @param p 每个金矿需要的人数
     * @return
     */
    public static int getMostGold(int n, int w, int[] g, int[] p) {
        int[] preResult = new int[w];
        int[] result = new int[w];

        // 1、根据现有人数，初始化第一个金矿的值
        for (int i = 0; i < w; i++) {
            if ((i + 1) >= p[0]) { // 如果当前人数大于金矿1所需的人数，那么初始值就为金矿的产量
                preResult[i] = g[0];
            }
        }
        // 遍历金矿 ==》 表格中的行
        for (int i = 0; i < n; i++) {
            //遍历人数 ==> 表格中的列
            for (int j = 0; j < w; j++) {
                int psnNum = j + 1;

                // 如果当前人数比i金矿处的所需人数少,返回上一行j位置处的值
                if (psnNum < p[i]) {
                    result[j] = preResult[j];
                } else {
                    //上一行的值，比如当前是5金库，10人。那么这里就是 4金库，10人
                    int num1 = preResult[j];
                    // 否则取：  4金库，10-当前5金库所需人数 处的值 + 5金库的产量
                    int n1 = j - p[i];
                    int no = n1 < 0 ? 0 :  n1;
                    int num2 = preResult[no] + g[i];
                    result[j] = Math.max(num1, num2);
                }

            }

            print(result);
        }

        return result[n];
    }

    private static void print(int[] result) {
        for (int i : result) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
