package com.mercury.datastructure._03_linked_list;

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

    }
}

/**
 * 由于是单链表，所以当添加或删除节点时，都需要先找到前一节点node，在根据node.next判断节点值并进行相应操作
 */
class LinkedList {
    // 定义head节点，不存储具体数据
    HeroNode head = new HeroNode(0, "");

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