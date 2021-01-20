package com.mercury.datastructure._04_stack;

public class StackArrDemo {
    public static void main(String[] args) throws Exception {
        StackArray stack = new StackArray(2);
//        stack.pop();
        stack.push(1);
        stack.push(2);
//        stack.push(3);
        stack.print();

        stack.pop();
        stack.print();

    }

}

/**
 * 使用数组实现栈
 */
class StackArray {
    // 栈的长度
    int size;
    //指针，指向栈顶
    int top;
    int[] arr;

    public StackArray(int size) {
        this.size = size;

        arr = new int[size];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        //先移动top，再赋值
        arr[++top] = value;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("没有数据");
        }
        // 暂存top位置的值，再将top下移
        int tempVal = arr[top--];
        return tempVal;

    }

    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }
}
