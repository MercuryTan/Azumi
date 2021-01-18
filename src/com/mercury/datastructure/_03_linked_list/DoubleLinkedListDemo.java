package com.mercury.datastructure._03_linked_list;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        StudentNode n1 = new StudentNode(1, "tom");
        StudentNode n2 = new StudentNode(2, "jerry");
        StudentNode n4 = new StudentNode(3, "jerry");
        StudentNode n5 = new StudentNode(4, "jerry");
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(n1);
        list.add(n2);
        list.add(n4);
        list.add(n5);

        list.print();
        System.out.println(list.get(1));
        System.out.println("============================");
        StudentNode n3 = new StudentNode(2, "jerry222");
        list.update(n3);
        list.print();

        list.delete(2);

        list.print();


        StudentNode nn1 = new StudentNode(1, "tom");
        StudentNode nn5 = new StudentNode(4, "jerry1");
        StudentNode nn2 = new StudentNode(2, "jerry2");
        StudentNode nn4 = new StudentNode(3, "jerry3");
        DoubleLinkedList list2 = new DoubleLinkedList();
        list2.addByOrder(nn1);
        list2.addByOrder(nn5);
        list2.addByOrder(nn2);
        list2.addByOrder(nn4);
        list2.print();

    }

}

class DoubleLinkedList {

    private StudentNode head = new StudentNode(0, "");

    //=========基础操作==========

    /**
     * 1、查询
     */
    public StudentNode get(int no) {
        StudentNode curr = head.next;
        boolean flag = false;
        while (curr != null) {
            if (curr.no == no) {
                flag = true;
                break;
            }
            curr = curr.next;
        }
        if (flag) {
            return curr;
        }
        return null;
    }


    /**
     * 2、新增
     * 【加到链表的最后】
     */
    public void add(StudentNode node) {
        // 找到最后一个节点[通过next寻找]
        StudentNode curr = head;
        while (true) {
            if (curr.next == null) {
                break;
            }
            curr = curr.next;
        }

        curr.next = node;
        node.prev = curr;
    }

    /**
     * 顺序存储
     *
     * @param node
     */
    public void addByOrder(StudentNode node) {
        StudentNode curr = head;
        boolean flag = false;
        while (true) {

            if (curr.next == null) {
                break;
            }

            if (curr.next.no > node.no) {
                break;
            } else if (curr.next.no == node.no) {
                flag = true;
                break;
            }
            curr = curr.next;
        }
        if(flag) {
            System.out.println("已存在no数据");
            return;
        }

        // 如果是链尾，那么只用关联尾节点的前置
        if(curr.next == null) {
            curr.next = node;
            node.prev = curr;
            return;
        }

        // 和curr原后置节点关联
        curr.next.prev = node;
        node.next = curr.next;

        // 和curr节点关联
        curr.next = node;
        node.prev = curr;

    }

    /**
     * 3、修改
     */
    public void update(StudentNode node) {
        StudentNode curr = head.next;
        boolean flag = false;
        while (curr != null) {
            if (curr.no == node.no) {
                flag = true;
                break;
            }
            curr = curr.next;
        }
        if (flag) {
            curr.name = node.name;
        }
    }

    /**
     * 4、删除
     * 【遍历当前节点就可进行删除】（单向链表需要找到删除节点的前一节点）
     */
    public void delete(int no) {
        StudentNode curr = head.next;
        boolean flag = false;
        while (curr != null) {
            if (curr.no == no) {
                flag = true;
                break;
            }
            curr = curr.next;
        }

        if (flag) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
    }

    public void print() {
        StudentNode curr = head.next;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
        System.out.println("============================");
    }
}

class StudentNode {
    int no;
    String name;

    StudentNode prev;
    StudentNode next;

    public StudentNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
