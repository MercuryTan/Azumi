package com.mercury.datastructure._02_queue_arr;

/**
 * 数组方式实现环形队列
 */
public class CircularQueueMain {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
//        queue.get();
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
        queue.add(7);
        queue.print();
    }
}

/**
 * 这种方式会有一个空间是预留值的
 */
class CircularQueue {
    private int maxSize;
    private int front; //指向第一个元素
    private int rear; // 指向最后一个空元素

    int[] arr;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public void add(int val) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        arr[rear] = val;
        rear = (rear + 1) % maxSize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        int tmpVal = arr[front];
        //清空之前的值
        arr[front] = 0;

        front = (front + 1) % maxSize;
        return tmpVal;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

}
