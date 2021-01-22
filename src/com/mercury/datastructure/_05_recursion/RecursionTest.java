package com.mercury.datastructure._05_recursion;

/**
 * 递归案例： 打印和阶乘
 */
public class RecursionTest {
    public static void main(String[] args) {
        //
//        print(5);

        System.out.println(fn(3));
    }

    /**
     * 打印
     *
     * @param n
     */
    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        }
        System.out.println("n = " + n);
    }

    /**
     * 阶乘
     */

    public static int fn(int n) {
        if (n == 1) {
            return 1;
        } else {
            return fn(n - 1) * n;
        }
    }
}
