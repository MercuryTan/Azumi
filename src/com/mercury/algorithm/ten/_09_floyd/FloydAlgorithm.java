package com.mercury.algorithm.ten._09_floyd;

import java.util.Arrays;

/**
 * 弗洛伊算法： 最短路径问题（各点到其他点最短路径问题）
 */
public class FloydAlgorithm {
    /**
     * 思路：
     * 1、dis[][]: 保存各点到其他点的最短距离，pre[][]: 保存各点的前驱节点（比如： A->B->C  C的前驱节点就是B）
     * 2、通过【中间节点】比如k，判断：
     *              len =（开始节点i到k的距离 + k到终止节点j的距离）
     *              dis[i][j] = 开始节点i直接到终止节点j的距离     ==>（这里也可能是通过上述方式到的最短距离）
     *
     *      ==>    if len < dis[i][j] then {
     *                  dis[i][j] = len
     *              }
     */

    public static void main(String[] args) {
        //创建顶点
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //创建邻接矩阵
        int[][] matrix = new int[nodes.length][nodes.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};


        Graph graph = new Graph(nodes.length, nodes, matrix);

        ShortestDistance shortestDistance = new ShortestDistance(graph);
        shortestDistance.floyd();
        shortestDistance.show();

    }
}

/**
 * 弗洛伊算法实现类
 */
class ShortestDistance {
    // 保存节点距离
    int[][] dis;

    // 保存节点的前驱节点下标  A-->0
    int[][] pre;

    Graph graph;

    /**
     * 构造方法： 初始化dis和pre数组
     *
     * @param graph
     */
    public ShortestDistance(Graph graph) {
        this.graph = graph;
        // 这里我们初始化为图的邻接矩阵
        dis = graph.matrix;
        pre = new int[graph.size][graph.size];

        // 初始化pre: 前驱节点就为自己
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 具体实现
     */
    public void floyd() {
        // 中间节点 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
        for (int k = 0; k < dis.length; k++) {
            // 起始点：  {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
            for (int i = 0; i < dis.length; i++) {
                // 终点： {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                for (int j = 0; j < dis.length; j++) {
                    // 如果 i到k的距离+k到j的距离 < i到j的距离
                    int len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        // 更新前驱节点: 比如 A->D的路线是： A G F D  那么D的前驱节点就是F（下标即为5）
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }


    public void show() {
        for (int[] di : dis) {
            System.out.println(Arrays.toString(di));
        }

        System.out.println();
        for (int[] ints : pre) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class Graph {
    // 节点个数
    int size;
    // 节点值
    char[] nodes;
    // 邻接矩阵： 节点之间的关联关系
    int[][] matrix;

    public Graph(int size, char[] nodes, int[][] matrix) {
        this.size = size;
        this.nodes = nodes;
        this.matrix = matrix;
    }

}
