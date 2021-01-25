package com.mercury.datastructure._05_recursion;

/**
 * 八皇后问题
 */
public class EightQueens {

    int total = 0;

    int count = 0;

    // 定义有多少个皇后
    int max = 8;
    /**
     * 定义数组保存皇后，比如有：arr={0,4}
     * 1、数组下标值： 第几个皇后；第几行
     * 2、数组值：第几列
     * 比如arr[1] = 4 ==> 第2个皇后在 2行5列
     */
    int[] arr = new int[max];

    /**
     * 检查第n个皇后，是否可以放置
     *
     * @param n
     * @return
     */
    public boolean check(int n) {
        total++;
        // 只需要判断数组前n位，是否存在冲突的皇后
        for (int i = 0; i < n; i++) {
            //1、检查是否在同一列 或者 同一斜线
            // 1.1 arr[i] == arr[n]   是否在同一列
            // 1.2  Math.abs(n-i) == Math.abs(arr[n]-arr[i]  当前行-目标行 == 当前列-目标列 --》【等腰三角形】--》是否在同一斜线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 放置下一个皇后
     * 1、退出条件：
     * n==8
     * 当次for循环结束
     *
     * @param n
     */
    public void next(int n) {
        // 已经到第九个皇后，说明都成功了
        if (n == max) {
            print();
            return;
        }

        // 从第0列放到第7列
        for (int i = 0; i < max; i++) {

            arr[n] = i;
            // 如果当前皇后放置成功，那么接着放下一个皇后
            if (check(n)) {
                next(n + 1);
            }
        }
    }

    /**
     * 打印成功的结果
     */
    public void print() {
        System.out.print("第" + ++count + "种：\t" );
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

    }


    public static void main(String[] args) {
        EightQueens queen = new EightQueens();
        queen.next(0);
        System.out.println("循环次数：" + queen.total);
    }

}
