package com.mercury.datastructure._11_graph;

public class GraphMain {
    public static void main(String[] args) {
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        Graph graph = new Graph(nodes, edges);
        graph.traverse();
    }
}

/**
 * 无向图
 */
class Graph {
    /**
     * 顶点集合:存储节点值
     * 例如： [A,B,C]等
     */
    char[] mVertexs;

    /**
     * 邻接矩阵：用于存储边的关系
     * 例如：   mEdges[i][j] = 1: 代表i和j处节点值 关联
     * mEdges[i][j] = 0: 代表i和j处节点值 不关联
     */
    int[][] mEdges;


    /**
     * 构造方法。
     * 1、初始化 mVertexs和mEdges（长度为节点个数）
     * 2、根据节点关联。初始化邻接矩阵
     *
     * @param mVertexs
     * @param edges    节点间对应的关联关系集合 ==> 例如 [{A,B},{C,D}] ==> 代表 AB相连，CD相连
     */
    public Graph(char[] mVertexs, char[][] edges) {
        this.mVertexs = mVertexs;
        int size = mVertexs.length;
        mEdges = new int[size][size];

        for (int i = 0; i < edges.length; i++) {
            // 通过char值找到对应位置
            int p1 = this.getPosition(edges[i][0]);
            int p2 = this.getPosition(edges[i][1]);
            // 建立节点间的关联关系
            mEdges[p1][p2] = 1;
            mEdges[p2][p1] = 1;
        }
    }


    /**
     * 获取字符在 mVertexs 中的下标位置
     *
     * @param c
     * @return
     */
    private int getPosition(char c) {
        for (int i = 0; i < mVertexs.length; i++) {
            if (mVertexs[i] == c) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 遍历 邻接矩阵值
     */
    public void traverse() {
        for (int i = 0; i < mEdges.length; i++) {
            for (int j = 0; j < mEdges[i].length; j++) {
                System.out.print(mEdges[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
