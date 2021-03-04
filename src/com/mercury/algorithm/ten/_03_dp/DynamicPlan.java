package com.mercury.algorithm.ten._03_dp;


/**
 * dp
 */
public class DynamicPlan {

    public static void main(String[] args) {
        int n = 5; // 5个金矿
        int w = 10; // 现在有10人
        int[] g = {400, 500, 200, 300, 350}; // 每个金矿对应的产量
        int[] p = {5, 5, 3, 4, 3};  // 每个金矿对应需要的人数

        int mostGolden = getMostGold(n, w, g, p);
        System.out.println(mostGolden);
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

        // 一、根据现有人数，初始化第一个金矿的值
        for (int i = 0; i < w; i++) {
            // 如果当前人数大于金矿1所需的人数，那么初始值就为金矿的产量 ==》 由于只有一个金矿,最大值就为金矿1的产量
            if ((i + 1) >= p[0]) {
                preResult[i] = g[0];
            }
        }
        // 打印金矿1的数据
        print(preResult);


        // 二、遍历金矿和人员表
        // 金矿==》 表格中的行(从1开始，即金矿2开始)
        for (int i = 1; i < n; i++) {
            //遍历人数 ==> 表格中的列(从0开始)
            for (int j = 0; j < w; j++) {
                // 当前实际人数
                int totalPsnNum = j + 1;
                // i金矿所需人数
                int currentGoldNeedPsn = p[i];
                // 上一行j处的总量 ==> 比如当前是5金库，10人。那么这里就是 4金库，10人
                int preGold = preResult[j];

                // ======主要部分！！！！======
                //1、如果当前人数小于i金矿所需人数，那么将返回上一行数据 （即j人i-1金矿处的值）
                if (totalPsnNum < currentGoldNeedPsn) {
                    result[j] = preGold;
                } else {
                    // 比如：totalPsnNum：10 当前总人数为10人
                    //  金矿1需要5人 那么remainPsnNum为5人
                    //  ==> index需要-1 即取index为4的数据
                    int remainPsnNum = totalPsnNum - currentGoldNeedPsn;
                    int index = (remainPsnNum - 1) < 0 ? 0 : (remainPsnNum - 1);
                    // 除i金矿所需的人数外，其余人数在i-1个金矿的产量   + i金矿的产量 == 最终产量
                    int num2 = preResult[index] + g[i];
                    result[j] = Math.max(preGold, num2);
                }

            }

            // !!!!! 注意：拷贝当前行数据，当前行之后就为上一行
            preResult = result.clone();

            print(result);
        }

        return result[w - 1];
    }

    private static void print(int[] result) {
        for (int i : result) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
