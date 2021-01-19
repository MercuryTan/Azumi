package com.mercury.datastructure._03_linked_list;

/**
 * 【单向环形链表】实现约瑟夫问题
 */
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(5);
        // 测试插入单个节点
        /*PersonNode n1 = new PersonNode(1);
        PersonNode n2 = new PersonNode(2);
        PersonNode n3 = new PersonNode(3);
        list.addNode(n1);
        list.addNode(n2);
        list.addNode(n3);*/
        list.print();

        list.countPerson(1,2,5);
    }
}

class CircleSingleLinkedList {
    // 指明第一个节点的位置
    PersonNode first;

    /**
     * 批量插入节点
     *
     * @param nums
     */
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("长度不能小于1");
            return;
        }

        // 由于first不能动,curr是当前遍历的节点
        PersonNode curr = new PersonNode(0);
        for (int i = 1; i <= nums; i++) {
            PersonNode node = new PersonNode(i);
            // first和curr都指向第一个节点,且自己指向自己
            if (i == 1) {
                first = node;
                curr = node;
                curr.next = first;
                // 假设现在插入的是第二个节点
                //那么： 第一个节点的下一个指向第二个节点，第二个节点的下一个指向first节点
                // 同时移动curr到第二个节点
            } else {
                curr.next = node;
                node.next = first;
                curr = node;
            }
        }
    }

    /**
     * 插入单个节点
     *
     * @param node
     */
    public void addNode(PersonNode node) {
        // 如果加入的是第一个节点,那么让自己指向自己形成环状
        if (first == null) {
            first = node;
            node.next = first;
            return;
        }

        PersonNode curr = first;
        while (true) {
            // 找到链表的最后一个节点
            if (curr.next == first) {
                break;
            }

            curr = curr.next;
        }

        curr.next = node;
        node.next = first;
    }


    /**
     * 约瑟夫问题: 从环中的k个人开始报数，数到m的人出去
     * 例如： nums：5 k：1 m：2 ==》 一共5个人，从第1个人开始报数，每数到2，那个人就出去(紧接着又从下一个人开始报数)==》顺序就是：2，4，1,5,3
     *
     * @param k 从第k个人开始报数
     * @param m 数到m的人出圈
     * @param nums 圈中一共多少人
     * @return
     */
    public void countPerson(int k, int m, int nums) {
        //0、校验
        if(first == null || k > nums) {
            System.out.println("链表为空或者数据不对");
            return;
        }

        //1、找到helper的位置 【helper指向first的前一个位置】
        PersonNode helper = first;
        while(true) {
            // 找到最后一个节点
            if(helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        //2、将helper，first移到正确的起始位置。即k的位置==》移动k-1次
        for (int i = 0; i < k - 1; i++) {
            helper = helper.next;
            first = first.next;
        }

        //3、将helper，first移到报数的位置(报m次数) ==》移动m-1次
        while(true) {
            // 只剩一个节点
            if(helper == first) {
                break;
            }

            for (int i = 0; i < m - 1; i++) {
                helper = helper.next;
                first = first.next;
            }
            System.out.println("出圈：" + first);

            //4、出圈
            first = first.next;
            helper.next = first;
        }

        System.out.println("剩下的人：" + helper);

    }

    public void print() {
        PersonNode temp = first;
        while (true) {
            System.out.println(temp);
            if (temp.next == first) {
                break;
            }


            temp = temp.next;
        }
    }
}


class PersonNode {
    int no;

    PersonNode next;

    public PersonNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                '}';
    }
}