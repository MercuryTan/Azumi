package com.mercury.datastructure._04_stack;

/**
 * 通过前缀表达式 进行计算
 */
public class PrefixCalculatorDemo {
    public static void main(String[] args) throws Exception {
        PrefixCalculator calculator = new PrefixCalculator();
        String infixStr = "1+((2+3)*4)-5";
//        calculator.infix2Prefix(infixStr);

        calculator.calculate(infixStr);
    }
}

class PrefixCalculator {

    StackP valueStack = new StackP(20);
    StackP operStack = new StackP(20);

    /**
     * 遍历valueStack得到最终值
     * 假设infixStr: 1+((2+3)×4)-5
     */
    public void calculate(String infixStr) throws Exception {
        // 1、将中缀表达式转换成前缀表达式 ==》 5 4 3 2 + × 1 + -  ==》 5在栈底
        this.infix2Prefix(infixStr);


        // 2、遍历值栈得到字符串数据 ==> “- + 1 × + 2 3 4 5 ==》 反转后，-在栈底
        StackP reverseStack = this.reverseStack(valueStack);

        // 3、遍历反转的值栈。相当于从右往左读字
        StackP finalStack = new StackP(20);
        while(!reverseStack.isEmpty()) {
            int curr = reverseStack.pop();
            boolean isCharFlag = this.isOper((char) curr);
            //3.1 如果是符号，弹出两个数字，进行运算
            if (isCharFlag) {
                int v1 = finalStack.pop();
                int v2 = finalStack.pop();
                // 用v1 运算符 v2
                int result = this.calNum(v2, v1, curr);
                finalStack.push(result);
            } else {
                //判断是否为多个数字
              finalStack.push(curr);
            }
        }
        System.out.println("最后的值为：" + finalStack.pop());


    }

    private StackP reverseStack(StackP valueStack) throws Exception {
        StackP stackP = new StackP(20);
        while (!valueStack.isEmpty()) {
            stackP.push(valueStack.pop());
        }
        return stackP;
    }


    /**
     * 中缀转换为前缀表达式
     */
    public void infix2Prefix(String infixStr) throws Exception {
        //从右往左开始扫描
        String numStr = "";
        char[] chars = infixStr.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char curr = chars[i];
            boolean isCharFlag = this.isOper(curr);
            //1、如果是数字，加入valueStack 中
            if (!isCharFlag) {
                //判断是否为多个数字
                numStr += curr;
                // 如果下一个是字符或者已经到字符串最后一位了，那么这次就把值加到栈中, 否则只拼接数字字符
                if ((i == 0) || this.isOper(chars[i - 1])) {
                    valueStack.push(Integer.parseInt(numStr));
                    numStr = "";
                }

            } else {
                //2、如果是操作符
                //2.1 operStack为空，直接加入
                if (operStack.isEmpty()) {
                    operStack.push(curr);
                    continue;
                }
                //2.2 operStack不为空
                //2.2.1 如果是括号
                // )括号，直接加入
                if (curr == ')') {
                    operStack.push(curr);
                    continue;
                }
                if (curr == '(') {
                    // (括号，弹出运算符到valueStack中，直到遇到）括号

                    while (true) {
                        int top = operStack.pop();
                        if (top == ')') {
                            break;
                        }
                        valueStack.push(top);

                    }
                    continue;
                }

                //2.2.2 如果不是括号
                //优先级大于等于栈顶元素，直接加入

                boolean flag = operStack.priority(curr) >= operStack.priority(operStack.peek());
                if (flag) {
                    operStack.push(curr);
                    continue;
                }

                //优先级小于栈顶元素，弹出运算符到valueStack中，直到优先级大于等于
                while (true) {
                    int top = operStack.pop();
                    if (operStack.priority(curr) >= operStack.priority(top)) {
                        break;
                    }
                    valueStack.push(top);
                }
            }
        }

        while (!operStack.isEmpty()) {
            valueStack.push(operStack.pop());
        }
        System.out.println(valueStack);
        System.out.println(operStack);


    }

    /**
     * 是否为字符
     *
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/' || val == ')' || val == '(';
    }

    /**
     * 计算两数的值
     */
    public int calNum(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
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
}


/**
 * 栈
 */
class StackP {
    int size;
    int top;

    int[] arr;

    public StackP(int size) {
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

    public int peek() {
        return arr[top];
    }


    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

    }

    /**
     * ()优先级和+-一致
     *
     * @param curr
     * @return
     */
    public int priority(int curr) {
        if (curr == '*' || curr == '/') {
            return 1;
        } else if (curr == '+' || curr == '-' || curr == '(' || curr == ')') {
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