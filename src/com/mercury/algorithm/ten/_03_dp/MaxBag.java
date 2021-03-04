package com.mercury.algorithm.ten._03_dp;

/**
 * 01背包问题： 与金矿问题类似
 */
public class MaxBag {
    public static void main(String[] args) {
        // 吉他，音响，笔记本
        int[] value = new int[]{1500, 3000, 2000};
        int[] weight = new int[]{1, 4, 3};
        int c = 4;
        int n = 3;
        int maxValue = getMaxValue(n, c, value, weight);
        System.out.println(maxValue);

    }

    /**
     * @param n      物品个数
     * @param c      背包容量
     * @param value  每个物品的价值
     * @param weight 每个物品的重量
     * @return
     */
    public static int getMaxValue(int n, int c, int[] value, int[] weight) {
        int[] preResult = new int[c];
        int[] result = new int[c];
        //1、初始化第一行数据 ==> 即吉他行
        for (int i = 0; i < c; i++) {
            // 如果当前背包容量大于等于 吉他的重量，那么总价值就为吉他的价值
            if ((i + 1) >= weight[0]) {
                preResult[i] = value[0];
            }
        }
        print(preResult);

        // 2、遍历表格
        // 物品行
        for (int i = 1; i < n; i++) {
            // 包容量列
            for (int j = 0; j < c; j++) {
                // 上一个的价值
                int preValue = preResult[j];
                // 当前容量
                int currentC = j + 1;
                // 当前物品需要的容量
                int iNeedC = weight[i];

                // 1、如果当前容量 小于 i物品的容量，那么返回 i-1个物品 j容量下的价值
                if (currentC < iNeedC) {
                    result[j] = preValue;
                } else {
                    // 剩余容量
                    int remainC = currentC - iNeedC;
                    // 剩余容量index  ==> 容量之前+1了，取索引需要-1 =》index为0，说明剩余容量为1
                    int index = remainC - 1;

                    // 除i物品容量外的价值 + i物品的价值 ==> 如果index<0,说明没有剩余容量，直接取当前物品的价值即可
                    int remainValue = index >= 0 ? value[index] + value[i] : value[i];

                    result[j] = Math.max(preValue, remainValue);
                }
            }

            preResult = result.clone();

            print(result);
        }


        return result[c - 1];
    }

    private static void print(int[] result) {
        for (int value : result) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }


}
