package com.mercury.algorithm.ten._06_prim;

import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        MinTree minTree = new MinTree();
        char[] nodes = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int size = nodes.length;
        // 邻接矩阵 10000表示两点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        Graph graph = minTree.createGraph(size, nodes, weight);
        minTree.print(graph);

        // 调用普利姆算法，获取最小边的集合
        minTree.prim(graph, 0);

    }


}

class MinTree {

    /**
     * 创建图
     *
     * @param size   节点个数
     * @param nodes  节点集合
     * @param matrix 邻接矩阵
     * @return
     */
    public Graph createGraph(int size, char[] nodes, int[][] matrix) {
        Graph graph = new Graph(size, nodes, matrix);
        return graph;
    }

    // 打印
    public void print(Graph graph) {
        for (int[] matrix : graph.matrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    /**
     * 普利姆算法：求最下生成树
     *
     * @param graph
     * @param v     节点对应在nodes集合中的下标  v=0 -> A 即从A开始
     */
    public void prim(Graph graph, int v) {
        int nodeSize = graph.size;
        // 保存当前节点是否被访问过  1：被访问
        int[] visited = new int[nodeSize];
        visited[v] = 1;

        int minWeight = 10000;
        int h1 = -1, h2 = -1 ;

        // size个节点，生成size-1条边
        for (int n = 1; n < nodeSize; n++) {
            for (int i = 0; i < nodeSize; i++) { // i: 已访问过的节点 ==> visited[i] == 1
                for (int j = 0; j < nodeSize; j++) { // j: 未访问过的节点 ==> visited[j] == 0
                    if (visited[i] == 1 && visited[j] == 0
                            && graph.matrix[i][j] < minWeight) { // graph.matrix[i][j] < minWeight  如果i节点到j节点的权重小于最小权重
                        minWeight = graph.matrix[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            // 打印当前边的关系
            System.out.printf("<%s,%s>,权重为：%s \t", graph.nodes[h1], graph.nodes[h2], minWeight);

            // 将node2标记为已访问
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}


class Graph {
    // 节点个数
    int size;
    // 节点集合
    char[] nodes;
    // 邻接矩阵--》表示节点和边的关系
    int[][] matrix;

    public Graph(int size, char[] nodes, int[][] matrix) {
        this.size = size;
        this.nodes = nodes;
        this.matrix = matrix;
    }
}