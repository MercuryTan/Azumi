package com.mercury.datastructure._07_binaryTree;

/**
 * 顺序存储二叉树 【测试】
 */
public class ArrayBinaryTreeMain {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        // 1、前序测试
        arrayBinaryTree.preOrder(0); // 1 2 4 5 3 6 7
        System.out.println();

        // 2、中序测试
        arrayBinaryTree.inffixOrder(0); // 2 4 5 1 3 6 7
        System.out.println();

        // 3、后序测试
        arrayBinaryTree.suffixOrder(0); //2 4 5 3 6 7 1
    }
}

/**
 * 顺序存储二叉树
 */
class ArrayBinaryTree {
    int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 1、前序遍历
     *
     * @param index 数组下标  0等价于二叉树的root节点
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("没得数据");
            return;
        }
        // 1、当前节点
        System.out.print(arr[index] + "\t");

        // 2、递归左子树
        int nextLeft = 2 * index +1;
        if(nextLeft < arr.length) {
            preOrder(nextLeft);
        }

        // 3、递归右子树
        int nextRight = 2 * index + 2;
        if(nextRight < arr.length) {
            preOrder(nextRight);
        }
    }

    /**
     * 二、中序遍历
     *
     * @param index 数组下标  0等价于二叉树的root节点
     */
    public void inffixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("没得数据");
            return;
        }
        // 1、递归左子树
        int nextLeft = 2 * index +1;
        if(nextLeft < arr.length) {
            preOrder(nextLeft);
        }


        // 2、当前节点
        System.out.print(arr[index] + "\t");

        // 3、递归右子树
        int nextRight = 2 * index + 2;
        if(nextRight < arr.length) {
            preOrder(nextRight);
        }
    }

    /**
     * 二、中序遍历
     *
     * @param index 数组下标  0等价于二叉树的root节点
     */
    public void suffixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("没得数据");
            return;
        }
        // 1、递归左子树
        int nextLeft = 2 * index +1;
        if(nextLeft < arr.length) {
            preOrder(nextLeft);
        }


        // 2、 递归右子树
        int nextRight = 2 * index + 2;
        if(nextRight < arr.length) {
            preOrder(nextRight);
        }

        // 3、当前节点
        System.out.print(arr[index] + "\t");
    }
}
