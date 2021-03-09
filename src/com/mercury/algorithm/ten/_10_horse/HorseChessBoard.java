
package com.mercury.algorithm.ten._10_horse;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 骑士周游算法： 深度遍历
 */
public class HorseChessBoard {
    /**
     * 思路：
     * 1、将当前点p置为已访问
     * 2、找到当前点p可以去的其他点list
     * 3、遍历list==> 对每个点进行访问 ==> 递归回溯
     * 4、根据当前步数判断是否全部走完，如果不是:那么将棋盘置为0，当前点置为未访问（false）
     */

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        long start = System.currentTimeMillis();
        System.out.println("骑士周游开始：");
        chessBoard.travel(0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + "毫秒");
        chessBoard.show();
    }


}

class ChessBoard {
    // 棋盘的行
    private static final int ROW = 8;

    // 棋盘的列
    private static final int COLUMN = 8;
    // 棋盘中每个点是否被访问过  true：已访问
    boolean[] visited;
    // 棋盘
    int[][] chessBoard;
    // 是否全部走完
    boolean finishFlag;

    public ChessBoard() {
        visited = new boolean[COLUMN * ROW];
        chessBoard = new int[ROW][COLUMN];
    }

    /**
     * 骑士周游问题具体实现
     *
     * @param row  当前点在棋盘的行  从0开始  ==> Point.y
     * @param col  当前点在棋盘的列  从0开始  ==> Point.x
     * @param step 步数
     */
    public void travel(int row, int col, int step) {
        // 1、将当前位置置为已访问  ==> 比如 col
        visited[row * COLUMN + col] = true;
        // 2、将当前位置置为步数
        chessBoard[row][col] = step;
        // 3、获取(x,y)可以访问的其他点
        List<Point> points = this.getNextPoints(new Point(col, row));

        // 贪心算法优化==>(从最少解的开始递归，减少回溯次数)
        this.sortPoints(points);

        while (!points.isEmpty()) {
            Point p = points.remove(0);
            // 如果当前点没有被访问过，那么递归
            if (!visited[p.y * COLUMN + p.x]) {
                travel(p.y, p.x, step + 1);
            }
        }

        // 4、判断是否全部走完
        if ((step < COLUMN * ROW) && !finishFlag) {
            visited[row * COLUMN + col] = false;
            chessBoard[row][col] = 0;
        } else {
            finishFlag = true;
        }


    }

    /**
     * 功能： 根据当前各点的下一级有多少点为标准，进行非递减操作 (贪心算法：每次选择当前的最优解)
     *  比如： points = {p1,p2}   ==> 判断p1和p2下各有多少个节点（也是list<point>）
     *
     * @param points
     */
    private void sortPoints(List<Point> points) {
        points.sort((p1, p2) -> {
            List<Point> points1 = getNextPoints(p1);
            List<Point> points2 = getNextPoints(p2);
            int p1Len = points1.size();
            int p2Len = points2.size();
            if (p1Len > p2Len) {
                return 1;
            } else if (p1Len == p2Len) {
                return 0;
            } else {
                return -1;
            }

        });
    }

    /**
     * 功能： 根据point的坐标获取可以去的其他point
     *
     * @param point (x,y)
     * @return
     */
    public List<Point> getNextPoints(Point point) {
        ArrayList<Point> points = new ArrayList<>();
        // 最多有八个点
        // 当前位置的行
        int y = point.y;
        // 当前位置的列
        int x = point.x;

        if (x - 2 >= 0 && y - 1 >= 0) {
            points.add(new Point(x - 2, y - 1));
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            points.add(new Point(x - 1, y - 2));
        }
        if (x + 1 < COLUMN && y - 2 >= 0) {
            points.add(new Point(x + 1, y - 2));
        }
        if (x + 2 < COLUMN && y - 1 >= 0) {
            points.add(new Point(x + 2, y - 1));
        }
        if (x + 2 < COLUMN && y + 1 < ROW) {
            Point Point1 = new Point(x + 2, y + 1);
            points.add(new Point(x + 2, y + 1));
        }
        if (x + 1 < COLUMN && y + 2 < ROW) {
            points.add(new Point(x + 1, y + 2));
        }
        if (x - 1 >= 0 && y + 2 < ROW) {
            points.add(new Point(x - 1, y + 2));
        }
        if (x - 2 >= 0 && y + 1 < ROW) {
            points.add(new Point(x - 2, y + 1));
        }
        return points;
    }

    public void show() {
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

}

class Point {
    // 点的横坐标 ==> 对应棋盘中的列
    int x;
    // 点的纵坐标 ==> 对应棋盘中的行
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

