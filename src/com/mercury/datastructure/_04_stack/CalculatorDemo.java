package com.mercury.datastructure._04_stack;


/**
 * 栈实现计算器
 */
public class CalculatorDemo {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        String value = "5*10-3+4*5";
        calculator.calculate(value);
    }
}

class Calculator {

    /**
     * 假设value为： 5*1-3+4*5
     *
     * @param value
     */
    public void calculate(String value) throws Exception {
        Stack valueStack = new Stack(10);
        Stack operStack = new Stack(10);
        char[] chars = value.toCharArray();
        String numStr = "";
        // 一、先将表达式通过优先级进行运算 ==> 剩余的都是同一优先级的
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            boolean isCharFlag = this.isOper(curr);
            //1、如果是字符
            if (isCharFlag) {
                // 1.1 字符栈为空，直接加入
                if (operStack.isEmpty() ) {
                    operStack.push(curr);
                    continue;
                }
                // 1.2 优先级小于等于，从值栈中pop出两个数据,字符栈中pop出一个 ==》 再将得出的值放到值栈中，当前符号放到符号栈中
                boolean isLessThan = operStack.priority(curr) <= operStack.priority(operStack.peek());
                if(isLessThan) {
                    int v1 = valueStack.pop();
                    int v2 = valueStack.pop();
                    int oper = operStack.pop();
                    int result = this.calNum(v1, v2, oper);
                    valueStack.push(result);
                    operStack.push(curr);
                    continue;
                }
                // 1.3 优先级大于，直接加入
                operStack.push(curr);
                continue;

                //2、如果是数字
            } else {
                //判断是否为多个数字
                numStr += curr;
                // 如果下一个是字符或者已经到字符串最后一位了，那么这次就把值加到栈中, 否则只拼接数字字符
                if((i+1 == chars.length) || this.isOper(chars[i+1]) ) {
                    valueStack.push(Integer.parseInt(numStr));
                    numStr = "";
                }

            }
        }

        // 二、遍历剩余的值，并进行运算
        while(true) {
            if(operStack.isEmpty()) {
                break;
            }
            int v1 = valueStack.pop();
            int v2 = valueStack.pop();
            int oper = operStack.pop();
            int result = this.calNum(v1, v2, oper);
            valueStack.push(result);

        }


        System.out.println("最终的值为：" + valueStack.pop());
    }

    /**
     * 计算两数的值
     */
    public int calNum(int num1,int num2,int oper){
        int result = 0;
        switch(oper){
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }

        return result;
    }

    public boolean isOper(char val){
        return val == '+' ||val == '-' ||val == '*' ||val == '/';
    }


}


class Stack {
    int size;
    int top;

    int[] arr;

    public Stack(int size) {
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
        int tempVal = arr[top];
        arr[top] = 0;
        top--;
        return tempVal;

    }

    public int peek(){
        return arr[top];
    }


    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

    }

    public int priority(int curr) {
        if (curr == '*' || curr == '/') {
            return 1;
        } else if (curr == '+' || curr == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }
}
