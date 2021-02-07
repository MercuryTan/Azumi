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

        root.prefixTraverse();
        System.out.println();
        root.inffixTraverse();
        System.out.println();
        root.suffixTraverse();

        // 3、二叉树查找
        System.out.println();
        BinaryTree node = root.preffixSearch(3);
        System.out.println("前序：" + node);

        node = root.inffixSearch(3);
        System.out.println("中序：" + node);

        node = root.suffixSearch(3);
        System.out.println("后序：" + node);

        root.deleteTree(root,3); // 期望结果：5,8,6,9
        root.prefixTraverse();
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

    // ============ 1、二叉树插入 ===========
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

    // ============ 2、二叉树遍历 ===========

    /**
     * 1、前序查找： 先查父节点， 左子树，右子树
     */
    public void prefixTraverse() {
        if (this != null) {
            System.out.print(this.data + "\t");
            if (this.left != null) {
                this.left.prefixTraverse();
            }
            if (this.right != null) {
                this.right.prefixTraverse();
            }
        }
//        System.out.println();
    }

    /**
     * 2、中序查找： 左子树， 父节点， 右子树
     */
    public void inffixTraverse() {
        if (this != null) {
            if (this.left != null) {
                this.left.prefixTraverse();
            }
            System.out.print(this.data + "\t");
            if (this.right != null) {
                this.right.prefixTraverse();
            }
        }
//        System.out.println();
    }

    /**
     * 3、后序查找： 左子树， 右子树， 父节点
     */
    public void suffixTraverse() {
        if (this != null) {
            if (this.left != null) {
                this.left.prefixTraverse();
            }

            if (this.right != null) {
                this.right.prefixTraverse();
            }

            System.out.print(this.data + "\t");
        }
//        System.out.println();
    }

    // ============ 3、二叉树查找 ===========

    /**
     * 1、前序
     *
     * @return
     */
    public BinaryTree preffixSearch(int data) {
        System.out.println("前序查找...");
        // 1、父节点
        if (this.data == data) {
            return this;
        }

        BinaryTree node = null;

        // 2、左子树递归
        if (this.left != null) {
            node = this.left.preffixSearch(data);
        }
        if (node != null) {
            return node;
        }

        // 3、右子树递归
        if (this.right != null) {
            node = this.right.preffixSearch(data);
        }
        if (node != null) {
            return node;
        }
        return null;
    }

    /**
     * 2、中序
     *
     * @return
     */
    public BinaryTree inffixSearch(int data) {


        BinaryTree node = null;

        // 1、左子树递归
        if (this.left != null) {
            node = this.left.inffixSearch(data);
        }
        if (node != null) {
            return node;
        }

        System.out.println("中序查找...");
        // 2、父节点
        if (this.data == data) {
            return this;
        }

        // 3、右子树递归
        if (this.right != null) {
            node = this.right.inffixSearch(data);
        }
        if (node != null) {
            return node;
        }
        return null;
    }

    /**
     * 3、后序
     *
     * @return
     */
    public BinaryTree suffixSearch(int data) {


        BinaryTree node = null;

        // 1、左子树递归
        if (this.left != null) {
            node = this.left.suffixSearch(data);
        }
        if (node != null) {
            return node;
        }

        // 2、右子树递归
        if (this.right != null) {
            node = this.right.suffixSearch(data);
        }
        if (node != null) {
            return node;
        }

        System.out.println("后序查找...");
        // 3、父节点
        if (this.data == data) {
            return this;
        }


        return null;
    }


    // ============ 4、二叉树删除 ===========

    /**
     * !!! 规定：是子节点就直接删除； 如果不是子节点，那么删除左或右子树 !!!
     *
     * 步骤：
     * 1、root节点
     * // 否则：
     *
     * 2、当前节点的左节点是否相等
     * 3、当前节点的右节点是否相等
     * 4、递归左子树
     * 5、递归右子树
     *
     * @param root
     * @param searchData
     */
    public BinaryTree deleteTree(BinaryTree root, int searchData) {
        // 1、判断root节点
        if (root.data == searchData) {
            root = null;
            // 2、否则，是删除子节点
        } else {
            root.deleteNode(searchData);
        }

        return root;
    }

    /**
     * 删除节点
     * @param searchData
     */
    private void deleteNode(int searchData) {
        // 1、从左开始判断
        if(this.left != null) {
            if(this.left.data == searchData) {
                this.left = null;
                return;
                // 左子树递归
            } else {
                this.left.deleteNode(searchData);
            }

        }

        //2、从右开始判断
        if(this.right != null) {
            if(this.right.data == searchData) {
                this.right = null;
                return;
                // 右子树递归
            } else {
                this.right.deleteNode(searchData);
            }

        }
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
