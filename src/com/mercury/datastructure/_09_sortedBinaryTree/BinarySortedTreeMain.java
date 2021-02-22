package com.mercury.datastructure._09_sortedBinaryTree;

/**
 * 二叉排序树 测试类
 */
public class BinarySortedTreeMain {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 1, 5, 9, 12, 2, 11, 13};
        Node root = new Node(arr[0]);
        BinarySortedTree tree = new BinarySortedTree(root);
        testInsert(tree, arr);
        System.out.println();

        tree.inOrderTraverse(); // 测试中序遍历： 1	2	3	5	7	9	10	11	12	13
        // 测试删除叶子节点
       /* tree.deleteNode(2);
        System.out.println();*/
        // 测试删除一个子树的节点
       /* tree.deleteNode(1);
        System.out.println();*/

        tree.deleteNode(10);
        System.out.println();
        tree.inOrderTraverse(); // 中序遍历：1  2	3	5	7	9	11	12	13
    }

    /**
     * 测试插入节点
     *
     * @param tree
     * @param arr
     */
    private static void testInsert(BinarySortedTree tree, int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            tree.insertNode(arr[i]);
        }
    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 二叉排序树
 */
class BinarySortedTree {

    Node root;

    public BinarySortedTree(Node root) {
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

    /**
     * 删除节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }
        root.deleteNode(value);
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

    // 删除节点相关方法

    /**
     * 根据value删除节点值
     *
     * @param value
     */
    public void deleteNode(int value) {
        Node targetNode = searchTargetNode(value);
        Node parentNode = searchParentNode(value);

        boolean inLeft = parentNode.left != null && parentNode.left.data == value;
        boolean inRight = parentNode.right != null && parentNode.right.data == value;

        // 判断删除节点子树情况
        // 1、删除节点为叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 要删除节点在父节点的左边
            if (inLeft) {
                parentNode.left = null;
            } else if (inRight) {
                parentNode.right = null;
            }
            // 2、删除节点存在两个子树
        } else if (targetNode.left != null && targetNode.right != null) {
            // 从当前节点开始找，找到右子树中最小的值
            Node minNode = targetNode.right.searchMinNode();
            // 将最小节点删除
            deleteNode(minNode.data);
            // 将原删除节点的值置位最小节点的值
            targetNode.data = minNode.data;

            //3、删除节点存在一个子树
        } else {
            // 删除节点在父节点的左边
            if (inLeft) {
                // 子树在删除节点的左边
                if (targetNode.left != null) {
                    parentNode.left = targetNode.left;
                    // 子树在删除节点的右边
                } else {
                    parentNode.left = targetNode.right;
                }
             // 删除节点在父节点的右边
            } else if (inRight) {
                // 子树在删除节点的左边
                if (targetNode.left != null) {
                    parentNode.right = targetNode.left;
                    // 子树在删除节点的右边
                } else {
                    parentNode.right = targetNode.right;
                }
            }
        }
    }

    /**
     * 找到右子树中最小的值  ==> 是最左的树
     * @return
     */
    private Node searchMinNode() {
        Node node = this;
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 查询要删除的目标节点
     *
     * @param value
     */
    public Node searchTargetNode(int value) {
        if (this.data == value) {
            return this;
        }
        // 向左递归
        if (this.data > value) {
            if (this.left != null) {
                return this.left.searchTargetNode(value);
            }
            // 向左递归查找
        } else {
            if (this.right != null) {
                return this.right.searchTargetNode(value);
            }
        }
        return null;
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要删除节点的值
     * @return null:没有找到
     */
    public Node searchParentNode(int value) {
        // 当前节点的左子节点或者右子节点的值要查的值相同，说明当前节点就是要删除节点的父节点
        boolean leftFlag = this.left != null && this.left.data == value;
        boolean rightFlag = this.right != null && this.right.data == value;
        if (leftFlag || rightFlag) {
            return this;
        }
        // 向左递归
        if (this.data > value) {
            if (this.left != null) {
                return this.left.searchParentNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.searchParentNode(value);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
