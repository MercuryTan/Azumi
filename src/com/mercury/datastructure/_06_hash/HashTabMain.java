package com.mercury.datastructure._06_hash;

public class HashTabMain {
    public static void main(String[] args) {
        Emp e1 = new Emp(1,"mercury");
        Emp e3 = new Emp(8,"mercury22");
        Emp e2 = new Emp(2,"tan");

        HashTab hashTab = new HashTab(7);
        hashTab.add(e1);
        hashTab.add(e2);
        hashTab.list();
        hashTab.add(e3);

        hashTab.list();
    }

}

class HashTab {
    EmpLinkedList[] empLinkedLists;
    int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[this.size];
        // 注意！！ 主要初始化
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int bucketNo = hash(emp.getId());
        empLinkedLists[bucketNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list();
        }
        System.out.println();
    }


    /**
     * 散列函数：求出应该放在数组的哪个链表下
     *
     * @return
     */
    public int hash(int id) {
        return id % size;
    }
}


/**
 * Emp链表
 */
class EmpLinkedList {

    Emp head = null;

    /**
     * 将当前Emp加到链表最后
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list() {
        if(head == null) {
            System.out.println("当前链表没有数据");
            return;
        }
        Emp tmp = head;
        while (true) {
            System.out.println(tmp);
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        System.out.println(" ============= ");
    }
}


/**
 * 链表节点
 */
class Emp {
    int id;
    String name;

    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}