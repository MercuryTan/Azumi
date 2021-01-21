package com.mercury.datastructure._04_stack;

/**
 * 后缀表达式 实现计算器
 */
public class SuffixCalculatorDemo {
    public static void main(String[] args) throws Exception {
        SuffixCalculator calculator = new SuffixCalculator();
        String infixStr = "1+((2+3)*4)-5";
//        calculator.infix2Suffix(infixStr);
        calculator.calculate(infixStr);
    }
}

class SuffixCalculator {

    StackS valueStack = new StackS(20);
    StackS operStack = new StackS(20);


    /**
     * 遍历valueStack得到最终值
     * 假设infixStr: 1+((2+3)×4)-5
     */
    public void calculate(String infixStr) throws Exception {
        // 1、将中缀表达式转换成后缀表达式 ==》 1 2 3 + 4 × + 5 -  ==》 1在栈底
        this.infix2Suffix(infixStr);


        // 2、遍历值栈得到字符串数据 ==> - 5 + * 4 + 3 2 1 ==》 反转后，-在栈底
        StackS reverseStack = this.reverseStack(valueStack);

        // 3、遍历反转的值栈。相当于从右往左读字
        StackP finalStack = new StackP(20);
        while(!reverseStack.isEmpty()) {
            int curr = reverseStack.pop();
            boolean isCharFlag = this.isOper((char) curr);
            //3.1 如果是符号，弹出两个数字，进行运算
            if (isCharFlag) {
                int v1 = finalStack.pop();
                int v2 = finalStack.pop();
                // 用v2 运算符 v1
                int result = this.calNum(v1, v2, curr);
                finalStack.push(result);
            } else {
                //判断是否为多个数字
                finalStack.push(curr);
            }
        }
        System.out.println("最后的值为：" + finalStack.pop());


    }


    private StackS reverseStack(StackS valueStack) throws Exception {
        StackS stackS = new StackS(20);
        while (!valueStack.isEmpty()) {
            stackS.push(valueStack.pop());
        }
        return stackS;
    }

    /**
     * 中缀表达式 转换成 后缀表达式
     */
    public void infix2Suffix(String infixStr) throws Exception {
        // 从前往后扫描
        char[] chars = infixStr.toCharArray();
        String numStr = "";
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            boolean isCharFlag = this.isOper(curr);
            // 1、如果是数字，加到valueStack中
            if(!isCharFlag) {
                numStr += curr;
                if ((i == chars.length-1) || this.isOper(chars[i + 1])) {
                    valueStack.push(Integer.parseInt(numStr));
                    numStr = "";
                }
            } else{
                // 2、如果是字符
                // 2.1 operStack为空，直接加入
                if(operStack.isEmpty()) {
                    operStack.push(curr);
                    continue;
                }
                // 2.2 operStack不为空
                // 2.2.1 括号
                // ( 括号，直接加入到operStack
                if(curr == '(') {
                    operStack.push(curr);
                    continue;
                }
                // ) 括号，将operStack弹出操作符，直到遇到( ==>将（）都去除了
                if(curr == ')') {
                    while(true) {
                        int top = operStack.pop();
                        if(top == '(') {
                            break;
                        }

                        valueStack.push(top);
                    }
                    continue;
                }

                // 2.2.2 运算符
                // 【优先级大于栈顶 或者 栈顶为 ( 】==》直接加入operStack
                boolean priority = operStack.priority(curr) > operStack.priority(operStack.peek());
                boolean isKh = operStack.peek() == '(';
                if(priority || isKh) {
                    operStack.push(curr);
                    continue;
                }
                // 【优先级小于等于栈顶】 ==》弹出operStack元素，加到valueStack中，直到优先级大于或栈为空
                while(true) {
                    if(operStack.isEmpty()) {
                        operStack.push(curr);
                        break;
                    }
                    int top = operStack.pop();
                    if(operStack.priority(curr) > operStack.priority(top)) {
                        break;
                    }
                    valueStack.push(top);
                }
            }
        }

        while (!operStack.isEmpty()) {
            valueStack.push(operStack.pop());
        }


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
class StackS {
    int size;
    int top;

    int[] arr;

    public StackS(int size) {
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