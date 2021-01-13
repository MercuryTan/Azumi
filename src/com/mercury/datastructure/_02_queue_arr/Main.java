package com.mercury.datastructure._02_queue_arr;

/**
 * 数组实现队列
 */
public class Main {
    public static void main(String[] args) {
        ArrQueue queue = new ArrQueue(5);
        // queue.get();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.print();

        System.out.println(queue.get());
        System.out.println(queue.get());
        queue.print();

        queue.add(6);
    }
}

class ArrQueue {
    private int maxSize;
    private int front; //指向第一个元素的前一个位置==>取元素
    private int rear; // 指向最后一个元素 ==>加元素

    int[] arr;

    public ArrQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public void add(int val) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }

        arr[++rear] = val;
    }

    public int get(){
        if(isEmpty()) {
            throw  new RuntimeException("空了");
        }
        front++;
        int tmpVal = arr[front];
        //清空之前的值
        arr[front] = 0;
        return tmpVal;

    }

    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }


    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

}
