package com.mercury.datastructure._10_avlTree;


/**
 * 二叉树测试类
 */
public class AvlTreeMain {
    public static void main(String[] args) {
        int[] arr = {100, 90, 80, 70, 60, 50};
        Node root = new Node(arr[0]);
        AvlTree tree = new AvlTree(root);
        testInsert(tree, arr);
        System.out.println("root高度为：" + root.height());
        System.out.println("root左子树高度为：" + root.heightLeft());
        System.out.println("root右子树高度为：" + root.heightRight());
        root.inOrderTraverse();
    }

    /**
     * 测试插入节点
     *
     * @param tree
     * @param arr
     */
    private static void testInsert(AvlTree tree, int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            tree.insertNode(arr[i]);
        }
    }
}


/**
 * AVL树
 */
class AvlTree {

    Node root;

    public AvlTree(Node root) {
        this.root = root;
    }

    /**
     * 插入节点
     */
    public void insertNode(int value) {
        if (root == null) {
            return;
        }
        root.insertNode(value);
    }

    /**
     * 中序遍历
     */
    public void inOrderTraverse() {
        if (root == null) {
            return;
        }
        root.inOrderTraverse();
    }

}


class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    /**
     * 【左旋】 当右子树高度-左子树高度 > 1
     *
     * @param unbalanceNode 非平衡节点
     * @return 平衡后的节点
     */
    public void routeLeft(Node unbalanceNode) {
        // 方法1（错误：会导致平衡后的节点不能放在正确位置）
        // 失衡节点的右节点
        /*Node rightNode = unbalanceNode.right;

        unbalanceNode.right = rightNode.left;
        rightNode.left = unbalanceNode;*/

        // 方法2： 创建新节点进行操作
        Node newNode = new Node(unbalanceNode.data);
        Node rightNode = unbalanceNode.right;
        newNode.left = unbalanceNode.left;
        newNode.right = rightNode.left;

        unbalanceNode.data = rightNode.data;
        unbalanceNode.right = rightNode.right;
        unbalanceNode.left = newNode;
    }

    /**
     * 【右旋】 当左子树高度-右子树高度 > 1
     *
     * @param unbalanceNode 非平衡节点
     * @return 平衡后的节点
     */
    public void routeRight(Node unbalanceNode) {
        // 失衡节点的左节点
        /*Node leftNode = unbalanceNode.left;

        // 失衡节点的left节点指向 原失衡节点左节点的right
        unbalanceNode.left = leftNode.right;
        leftNode.right = unbalanceNode;*/

        // 方法2： 创建新节点进行操作
        Node newNode = new Node(unbalanceNode.data);
        Node leftNode = unbalanceNode.left;

        newNode.right = unbalanceNode.right;
        newNode.left = leftNode.right;

        unbalanceNode.data = leftNode.data;
        unbalanceNode.left = leftNode.left;
        unbalanceNode.right = newNode;

    }


    // 高度相关

    /**
     * 当前节点左子树的高度
     *
     * @return
     */
    public int heightLeft() {
        int leftHeight = this.left == null ? 0 : this.left.height();
        return leftHeight;
    }

    /**
     * 当前节点右子树的高度
     *
     * @return
     */
    public int heightRight() {
        int rightHeight = this.right == null ? 0 : this.right.height();
        return rightHeight;
    }


    /**
     * 求当前节点的高 [取左右子树中最高值 + 1]
     *
     * @return
     */
    public int height() {
        // 找到当前节点左右子树中最大值
        return Math.max(heightLeft(), heightRight()) + 1; // +1是把当前节点的高度也算上
    }

    /**
     * 1、插入节点
     *
     * @param data
     */
    public void insertNode(int data) {
        if (this == null) {
            System.out.println("节点为空");
            return;
        }

        // 放左边
        if (this.data > data) {
            if (this.left != null) {
                this.left.insertNode(data);
            } else {
                this.left = new Node(data);
            }
        } else {
            if (this.right != null) {
                this.right.insertNode(data);
            } else {
                this.right = new Node(data);
            }
        }

        // 旋转

        // 1、左子树-右子树 >1 右旋
        if ((this.heightLeft() - this.heightRight() > 1)) {
            // 1.1 左子树的right >左子树的left ==> 先对左子树进行左旋，再对当前节点进行右旋
            if(this.left.heightRight() > this.left.heightLeft()) {
                routeLeft(this.left);
            }
            // 右旋
            routeRight(this);

        }

        // 2、右子树-左子树 >1 左旋
        if ((this.heightRight() - this.heightLeft() > 1)) {
            // 1.1 右子树的left > 右子树的right ==> 先对左子树进行右旋，再对当前节点进行左旋
            if(this.right.heightLeft() > this.right.heightRight()) {
                routeRight(this.right);
            }
            // 左旋
            routeLeft(this);
        }
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public void inOrderTraverse() {
        if (this == null) {
            System.out.println("没有节点");
            return;
        }

        if (this.left != null) {
            this.left.inOrderTraverse();
        }
        System.out.print(this.data + "\t");
        if (this.right != null) {
            this.right.inOrderTraverse();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}