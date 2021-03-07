package com.mercury.algorithm.ten._07_kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 克鲁斯科尔算法： 形成最小生成树
 */
public class KruskalAlgorithm {

    /**
     * 思路：
     * 1、将所有边的权值进行排序
     * 2、遍历边，并判断边的两端节点是否指向同一终点（即是否形成回路），如果形成回路，那么选择下一条边
     */

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        //使用 INF 表示两个顶点不能连通
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        MinTree minTree = new MinTree();
        Graph graph = minTree.createGraph(nodes.length, nodes, matrix);
        List<Edge> kruskal = minTree.kruskal(graph);
        for (Edge edge : kruskal) {
            System.out.println(edge);
        }
    }


}

class MinTree {

    /**
     * 克鲁斯卡尔具体算法
     *
     * @param graph
     */
    public List<Edge> kruskal(Graph graph) {
        List<Edge> result = new ArrayList<>();
        // 一、获取所有排好序的边
        // 1、 获取所有存在的边
        List<Edge> edges = this.getEdges(graph);
        // 2、 排序
        this.sortEdges(edges);

        // 二、遍历所有的边，如果产生回路，那么继续遍历
        // 保存节点对应终点的关系
        int[] ends = new int[graph.size];
        for (Edge edge : edges) {
            int startIndex = this.getPosition(graph, edge.start);
            int endIndex = this.getPosition(graph, edge.end);

            // 获取当前边的起始点和终止点的最终点是否相同，如果不相同。那么保存起来
            int n1 = this.getEnd(ends, startIndex);
            int n2 = this.getEnd(ends, endIndex);
            if (n1 != n2) {
                // 设置n1的终点为n2
                ends[startIndex] = endIndex;
                result.add(edge);
            }
        }
        return result;
    }

    /**
     * 获取 当前节点 的最终点是哪个
     *
     * @param ends         {2,0,3,0 } ==> {A,B,C,D}   A==2 ，说明A的下一终点为2下标处（即为C） ==> A->C
     *                     C==2，说明C的下一终点为D ==> C->D
     *                     所以这里求的就是A->C->D ==> 最终A的终点就为D（即3）
     * @param currentIndex  当前元素的数组下标
     * @return
     */
    private int getEnd(int[] ends, int currentIndex) {
        // 获得当前数组下标中的值（即下一个终点的下标是多少）
        while (ends[currentIndex] != 0) {
            currentIndex = ends[currentIndex];
        }
        return currentIndex;
    }

    /**
     * 求字符在图中的索引
     *
     * @param graph
     * @param currentChar
     * @return
     */
    private int getPosition(Graph graph, char currentChar) {
        for (int i = 0; i < graph.nodes.length; i++) {
            if (graph.nodes[i] == currentChar) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 获取所有的边
     *
     * @param graph
     * @return
     */
    private List<Edge> getEdges(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        // 遍历邻接矩阵
        for (int i = 0; i < graph.matrix.length; i++) {
            // 相当于只遍历斜线以上的部分(我们只需要遍历一个方向即可 比如A->C C->A都是3。那么只需要保存<A,C> 3即可) => j=i+1,也排除了A和A的关系
            for (int j = i + 1; j < graph.matrix[i].length; j++) {
                if (graph.matrix[i][j] != Integer.MAX_VALUE) {
                    Edge edge = new Edge(graph.nodes[i], graph.nodes[j], graph.matrix[i][j]);
                    edges.add(edge);
                }
            }
        }

        return edges;
    }

    /**
     * 排序
     *
     * @param edges
     */
    private void sortEdges(List<Edge> edges) {
        edges.sort(Comparator.comparingInt(e -> e.weight));
    }


    public Graph createGraph(int size, char[] nodes, int[][] matrix) {
        Graph graph = new Graph(size, nodes, matrix);
        return graph;
    }

    public void print(Graph graph) {
        for (int[] matrix : graph.matrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }


}

/**
 * 边的类
 */
class Edge {
    // 边的起始点
    char start;
    // 边当前的终点
    char end;
    // 两点之间的权值
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                ">, weight=" + weight;
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