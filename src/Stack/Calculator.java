package Stack;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        String expression;
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入需要计算的表达式：");
        expression = scanner.next();

        ArrayStack1 numStack = new ArrayStack1(50);
        ArrayStack1 operStack = new ArrayStack1(50);

        int index = 0; //用于遍历表达式
        int num1;
        int num2;
        int oper;
        int res;
        char ch; //将每次遍历得到char保存在ch中
        String keepNum = ""; //用于拼接多位数

        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，做相应的处理
            if(operStack.isOper(ch)){ //如果为运算符

                if(!operStack.isEmpty()){ //如果符号栈为空

                    /*
                        如果符号栈有操作符，就进行比较，如果当前操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，
                        再从符号栈中pop出一个符号，进行运算。得出的结果入数栈，然后当前操作符入符号栈。
                     */
                    if(operStack.priority(ch) <= operStack.priority(operStack.peak()) && operStack.peak() != '('){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);

                        //运算的结果入数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);

                        /*
                          进行右括号的判断，匹配左括号
                          当发现进入的是右括号时，就优先进行括号内的运算
                         */
                    }else if(ch == ')') {
                        //先让右括号进栈
                        operStack.push(ch);
                        if(ch == ')'){
                            //再把右括号弹出
                            int oper1 = operStack.pop();
                            //弹出右括号后开始进行括号内运算
                            while(true){
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                oper = operStack.pop();
                                res = numStack.cal(num1, num2, oper);

                                //把运算结果加入数栈
                                numStack.push(res);
                                //当运算到栈顶为左括号的时候，就弹出栈顶元素左括号，结束循环
                                if(operStack.peak() == '('){
                                    int oper2 = operStack.pop();
                                    break;
                                }
                            }
                        }
                    }else{
                        //如果当前操作符优先级大于栈中操作符，直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果符号栈为空，直接入栈
                    operStack.push(ch);
                }
            }else{ //如果是数字，直接入数栈
                /*
                    1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                    2.在处理数，需要向expression表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                    3.因此我们需要定义一个变量 字符串，用于拼接
                 */

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是数字就继续扫描，如果是运算符则入栈
                    //注意是看后一位，不是index++

                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))){
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空！！！！
                        keepNum = "";
                    }
                }
            }
            //让index + 1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序地从 数栈 和 符号栈 中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //打印结果
        int result = numStack.pop();
        System.out.printf("%s = %d", expression, result);
    }
}

class ArrayStack1{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack1(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public int peak(){
        return stack[top];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }

        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }

        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d", i, stack[i]);
        }
    }

    //判断符号优先级,优先级由程序员界定，用数字表示，数字越大优先级越高
    public int priority(int oper){
        if(oper == '(' || oper == ')'){
            return 2;
        }else if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
    }

    public int cal(int num1, int num2, int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}