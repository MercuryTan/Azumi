package com.mercury.datastructure._08_huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    /**
     * 1、排序
     * 2、取前两个，组成新二叉树
     * 3、排序
     */

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
//        System.out.println(root);

        root.preOrder();
    }

    /**
     * 【创建赫夫曼树】
     *
     * @param arr
     */
    private static Node createHuffmanTree(int[] arr) {
        // 数组转换成node节点
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while(nodes.size() > 1) {
            // 1、排序
            Collections.sort(nodes);
            // 2、取前两个节点，组成新二叉树 【list中移除原节点，添加新节点】
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
            // 循环 ==> 结束条件：list中只有根节点root

        }
        return nodes.get(0);
    }
}


class Node implements Comparable<Node> {
    int value; // 权值

    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 按从小到大排列
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}