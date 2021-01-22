package com.mercury.datastructure._05_recursion;

/**
 * 递归： 迷宫问题
 */
public class Maze {

    /**
     * 1、定义迷宫中的值 0：没有走过  1：墙  2：走过且可以走通  3：走不通
     * 2、走尝试的顺序为 下右上左
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1、初始化迷宫
        int[][] map = initMap();
        System.out.println("起始迷宫为：");
        printMap(map);
        // 2、调用递归方法 ==> 即从（1,1）处开始找，直到出口位置（6,6）
        setNext(map, 1, 1);
        System.out.println("递归后迷宫为：");
        printMap(map);
    }

    /**
     * @param map 地图
     * @param i   起始值的行数
     * @param j   起始值的列数
     * @return  true: 下一步可以走，false：下一步错误
     */
    private static boolean setNext(int[][] map, int i, int j) {
        //1、如果出口位置值为2，说明成功找到出口，即可退出
        if (map[6][6] == 2) {
            return true;

            //2、 如果（i,j）为0,说明可以按 下右上左 的顺序继续移动
        } else if (map[i][j] == 0){
            // 先设置当前（i,j）为2，表示可走
            map[i][j] = 2;
            if(setNext(map,i+1,j) || setNext(map,i,j+1) || setNext(map,i-1,j) || setNext(map,i,j-1)) {
                return true;
            }
            // 如果按顺序都不能移动，那么说明走不通
            map[i][j] = 3;
            return false;
        }else{
            return false;
        }
    }

    /**
     * 初始化迷宫
     *
     * @return
     */
    private static int[][] initMap() {
        int[][] map = new int[8][8];
        // 设置第一行  和 最后一行为墙
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 设置第一列 和 最后一列为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }

        // 设置第四行 第二，三列为墙
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        return map;
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }
}
