package com.mercury.algorithm.ten._03_dp;

/**
 * 楼梯问题描述：
 * 一共有10个台阶，每次只能走1步或2步。求有多少种走法
 * <p>
 * 思路分析：
 * 1、将10个台阶的解法置为F(10)
 * 2、最小结构为： F(10) = F(8) + F(9)   =》 在第8个台阶最后走两步 + 在第9个台阶最后走一步 = 最后的走法
 * <p>
 * 状态转移方程：
 * 1、边界： F(1) = 1  F(2) = 2  =》1个台阶有1种走法  2个台阶有2种走法
 * 2、F(n) = F(n-1) + F(n-2)
 */
public class StairQuestion {
    public static void main(String[] args) {
        int ways = getWays(10);
        System.out.println(ways);
    }


    /**
     * 动态规划 =》利用上一次
     *
     * @param n 台阶数
     * @return
     */
    public static int getWays(int n) {
        // 上上个台阶的走法
        int preA = 1;
        // 上个台阶的走法
        int preB = 2;
        if (n == 1) {
            return preA;
        }
        if (n == 2) {
            return preB;
        }

        int result = 0;
        for (int i = 3; i <= n; i++) {
            // 当前台阶的结果
            result = preA + preB;

            // 保存上上个台阶和上个台阶的解法，以便下一次使用
            preA = preB;
            preB = result;

        }

        return result;
    }
}
