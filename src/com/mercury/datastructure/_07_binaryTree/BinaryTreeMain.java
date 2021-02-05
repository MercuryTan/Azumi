package com.mercury.datastructure._07_binaryTree;


public class BinaryTreeMain {

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(5);
        BinaryTree node1 = new BinaryTree(3);
        BinaryTree node3 = new BinaryTree(2);
        BinaryTree node4 = new BinaryTree(4);
        BinaryTree node5 = new BinaryTree(1);

        BinaryTree node2 = new BinaryTree(8);
        BinaryTree node6 = new BinaryTree(6);
        BinaryTree node7 = new BinaryTree(9);

        root.insert(node1);
        root.insert(node2);
        root.insert(node3);
        root.insert(node4);
        root.insert(node5);
        root.insert(node6);
        root.insert(node7);

        root.prefixSearch();
        System.out.println();
        root.inffixSearch();
        System.out.println();
        root.suffixSearch();

    }

}

/**
 * 二叉树
 */
class BinaryTree {
    int data;

    // 左子树
    BinaryTree left;
    // 右子树
    BinaryTree right;

    public BinaryTree(int data) {
        this.data = data;
    }

    public void insert(BinaryTree node) {
        // 1、当前节点的值比插入节点的大，那么node放在左子树
        if (this.data > node.data) {
            // 如果左节点有值，那么将left当成根节点，继续插入node
            if (this.left != null) {
                this.left.insert(node);
                // 否则，left就为node节点
            } else {
                this.left = node;
            }
            // 2、放到右子树
        } else {
            if (this.right != null) {
                this.right.insert(node);
            } else {
                this.right = node;
            }
        }
    }

    /**
     * 1、前序查找： 先查父节点， 左子树，右子树
     */
    public void prefixSearch() {
        if(this!=null) {
            System.out.print(this.data + "\t");
            if(this.left != null) {
                this.left.prefixSearch();
            }
            if(this.right != null) {
                this.right.prefixSearch();
            }
        }
//        System.out.println();
    }

    /**
     * 2、中序查找： 左子树， 父节点， 右子树
     */
    public void inffixSearch() {
        if(this!=null) {
            if(this.left != null) {
                this.left.prefixSearch();
            }
            System.out.print(this.data + "\t");
            if(this.right != null) {
                this.right.prefixSearch();
            }
        }
//        System.out.println();
    }

    /**
     * 3、后序查找： 左子树， 右子树， 父节点
     */
    public void suffixSearch() {
        if(this!=null) {
            if(this.left != null) {
                this.left.prefixSearch();
            }

            if(this.right != null) {
                this.right.prefixSearch();
            }

            System.out.print(this.data + "\t");
        }
//        System.out.println();
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
