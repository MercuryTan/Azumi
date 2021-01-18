package com.mercury.datastructure._03_linked_list;

import java.util.Stack;

/**
 *
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        HeroNode n1 = new HeroNode(10, "Kobe");
        HeroNode n2 = new HeroNode(20, "James");
        HeroNode n3 = new HeroNode(30, "Curry");
        /* LinkedList list = new LinkedList();
         list.add(n1);
         list.add(n2);
         list.add(n3);
         list.print();*/


        LinkedList list2 = new LinkedList();
        list2.addByOrder(n1);
        list2.addByOrder(n3);
        list2.addByOrder(n2);
        list2.addByOrder(n2);
        list2.print();

        HeroNode n4 = new HeroNode(30, "CC");
        list2.update(n4);
        list2.print();

        HeroNode n5 = new HeroNode(20, "CC");
        list2.delete(n5);
        list2.print();

        testQuesTion();


    }

    /**
     * 测试题目
     */
    private static void testQuesTion() {
        HeroNode n1 = new HeroNode(10, "Kobe");
        HeroNode n2 = new HeroNode(20, "James");
        HeroNode n3 = new HeroNode(30, "Curry");
        LinkedList list = new LinkedList();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.print();
        //1、长度
        System.out.println("===========长度================");
        System.out.println(list.size());
        //2、倒数k个节点值
        System.out.println("===========倒数k值================");
        System.out.println(list.getNode(3));
        //3、反转
        System.out.println("===========反转================");
        list.reverse();
        list.print();
        //4、倒序打印
        System.out.println("===========倒序打印================");
        list.reversePrint();
        //5、合并两链表
        System.out.println("===========合并链表================");
        HeroNode n4 = new HeroNode(11, "Kobe1");
        HeroNode n5 = new HeroNode(21, "James1");
        HeroNode n6 = new HeroNode(31, "Curry1");
        LinkedList list2 = new LinkedList();
        list2.addByOrder(n4);
        list2.addByOrder(n5);
        list2.addByOrder(n6);

        HeroNode n7 = new HeroNode(12, "Kobe2");
        HeroNode n8 = new HeroNode(22, "James2");
        HeroNode n9 = new HeroNode(32, "Curry2");
        LinkedList list3 = new LinkedList();
        list3.addByOrder(n7);
        list3.addByOrder(n8);
        list3.addByOrder(n9);
        LinkedList linkedList = LinkedList.mergeList(list2, list3);
        linkedList.print();

    }
}

/**
 * 由于是单链表，所以当添加或删除节点时，都需要先找到前一节点node，在根据node.next判断节点值并进行相应操作
 */
class LinkedList {
    // 定义head节点，不存储具体数据
    private HeroNode head = new HeroNode(0, "");

    //=====题目开始=====

    /**
     * 1、求单链表中节点的个数
     */
    public int size() {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 2、查找单链表中倒数第k个节点
     *
     * @param k
     */
    public HeroNode getNode(int k) {
        int size = size();
        if (size == 0) {
            System.out.println("链表为空");
            return null;
        }
        int curIndex = size - k;
        if (curIndex < 0) {
            System.out.println("链表长度不够");
            return null;
        }
        int tmpLength = 0;
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (tmpLength == curIndex) {
                flag = true;
                break;
            }

            tmpLength++;
            temp = temp.next;
        }

        if (flag) {
            return temp;
        }
        return null;
    }

    /**
     * 3、单链表的反转
     * 方式：采用临时头节点保存
     */
    public void reverse() {
        // 用于遍历
        HeroNode temp = head.next;
        HeroNode tHead = new HeroNode(0, "");
        while (temp != null) {
            HeroNode currentNode = temp;
            //循环原链表
            temp = temp.next;

            currentNode.next = tHead.next;
            tHead.next = currentNode;


        }
        head.next = tHead.next;
    }


    /**
     * 4、从尾到头打印单链表
     * 【反转：破坏原结构】
     * 【栈】
     */
    public void reversePrint() {
        Stack<HeroNode> stacks = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            stacks.push(temp);
            temp = temp.next;
        }

        stacks.forEach(node -> {
            System.out.println(node);
        });
    }


    /**
     * 5、合并两个有序的单链表，合并后的链表依然有序
     *
     * @param listOne 单链表one
     * @param listTwo 单链表two
     */
    public static LinkedList mergeList(LinkedList listOne, LinkedList listTwo) {
        // 遍历listTwo，并顺序插入到listOne中
        HeroNode temp = listTwo.getHead().next;
        while (temp != null) {
            HeroNode currentNode = temp;
            temp = temp.next;
            listOne.addByOrder(currentNode);

        }
        return listOne;
    }

    //=====题目结束=====

    /**
     * 获取头结点
     *
     * @return
     */
    public HeroNode getHead() {
        return this.head;
    }

    public void delete(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {

            if (temp.next == null) {
                break;
            }

            // 如果当前遍历节点的下一节点和当前节点相同，说明要删除下一节点
            if (temp.next.age == node.age) {
                flag = true;
                break;
            }
            temp = temp.next;

        }

        if (flag) {
            temp.next = temp.next.next;
        }
    }

    /**
     * 根据age修改node节点
     *
     * @param node
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            //如果当前遍历节点的年龄和node的年龄相同，那么退出循环
            if (temp.age == node.age) {
                flag = true;
                break;
            }

            //如果没有下一节点，那么也退出循环
            if (temp.next == null) {
                break;
            }

            temp = temp.next;

        }
        if (flag) {
            temp.name = node.name;
            System.out.println("修改成功");
        } else {
            System.out.println("不存在node节点");
        }
    }


    /**
     * 插入到链表的最后
     *
     * @param node
     */
    public void add(HeroNode node) {
        // 1、找到最后一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 按年龄顺序插入，如果有重复那么提示错误
     * 要点：1、找到要插入的位置
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false; //标志node.age是否和temp.next.age相等
        while (true) {
            if (temp.next == null) {
                break;
            }
            // node和当前遍历节点.next比较
            if (temp.next.age > node.age) {
                break;
            } else if (temp.next.age == node.age) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            System.out.println("当前已存在数据");
            return;
        }

        node.next = temp.next;
        temp.next = node;

    }

    public void print() {
        if (head.next == null) {
            System.out.println("链表没有节点~");
            return;
        }
        HeroNode temp = head.next; //从链表第一个节点开始遍历
        while (true) {
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println("==========");
    }
}


class HeroNode {
    int age;
    String name;
    HeroNode next;

    public HeroNode(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}