package com.mercury.datastructure._07_binaryTree;

public class ThreadedBinaryTreeMain {
    public static void main(String[] args) {
        Node root = new Node(5);
        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);

        Node n1 = new Node(3);
        Node n2 = new Node(8);
        Node n3 = new Node(2);
        Node n4 = new Node(4);
        Node n5 = new Node(1);
        Node n6 = new Node(6);
        Node n7 = new Node(9);
        tree.insert(root, n1);
        tree.insert(root, n2);
        tree.insert(root, n3);
        tree.insert(root, n4);
        tree.insert(root, n5);
        tree.insert(root, n6);
        tree.insert(root, n7);

        tree.inffixTraverse(root);

        System.out.println();
        // 测试中序线索化二叉树
        tree.inffixClues(root);
        System.out.println(n4.getLeft()); // 期望为3
        System.out.println(n4.getRight()); //期望为5
    }

}

class ThreadedBinaryTree {
    Node root;

    ////////////////线索化二叉树相关开始//////////////////////////
    /**
     * 保存线索化前一个节点
     */
    Node pre;
    ////////////////线索化二叉树相关结束//////////////////////////

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    /**
     * 1、中序线索化二叉树
     */
    public void inffixClues(Node node) {
        if (node == null) {
            return;
        }
        // 1、线索化左子树
        inffixClues(node.left);

        // 2、线索化当前值
        // 2.1 设置node的前驱节点
        if(node.left == null) {
            node.left = pre;
            node.setLeftType(1);
        }

        // 2.2 !!!! 设置node的后继节点，借助pre来实现 (因为二叉树是单向的，如果要指向父节点，需要借助指针)
        if(pre != null && pre.right == null) {
            pre.right = node;
            pre.setRightType(1);
        }
        //!!!!!!!! pre 跟着移动
        pre = node;

        // 3、线索化右子树
        inffixClues(node.right);
    }


    /**
     * 插入方法
     *
     * @param parentNode 父节点
     * @param node       当前插入节点
     */
    public void insert(Node parentNode, Node node) {
        if (root == null) {
            this.root = node;
        } else {
            // 放左边
            if (parentNode.data > node.data) {
                // 父节点的左节点不为空
                if (parentNode.left != null) {
                    // 把左节点当做父节点，进行递归
                    insert(parentNode.left, node);
                } else {
                    parentNode.left = node;
                }
            } else {
                if (parentNode.right != null) {
                    insert(parentNode.right, node);
                } else {
                    parentNode.right = node;
                }
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void inffixTraverse(Node node) {
        if (node != null) {
            if (node.left != null) {
                inffixTraverse(node.left);
            }
            System.out.print(node.data + "\t");
            if (node.right != null) {
                inffixTraverse(node.right);
            }

        }
    }

}


/**
 * 二叉树节点
 */
class Node {

    int data;

    Node left;
    Node right;
    ////////////////线索化二叉树相关开始//////////////////////////
    /**
     * 左节点类型 0:左节点  1：前驱节点
     */
    int leftType;

    /**
     * 右节点类型 0:右节点  1：后继节点
     */
    int rightType;
    ////////////////线索化二叉树相关结束//////////////////////////

    public Node(int data) {
        this.data = data;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
