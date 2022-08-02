package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        /*
            完成一个中缀表达式转成后缀表达式的过程
            说明：
                1.1+((2+3)*4)-5 -> 1 2 3 + 4 * + 5 -
                2.因为直接对str进行操作，不方便，因此先将中缀表达式 1+((2+3)*4)-5 转成对应的list:
                  即1+((2+3)*4)-5 -> ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
                3.将得到的中缀表达式的list转化为对应的后缀表达式的list
                  即ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] -> ArrayList[1, 2, 3, +, 4, *, +, 5, -]
         */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list：" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list：" + suffixExpressionList);

        System.out.printf("expression = %d\n", calculate(suffixExpressionList));

        /*
            先定义逆波兰表达式
            (30+4)×5-6 -> 30 4 + 5 × 6 - ->164
            4×5-8+60+8/2 -> 4 5 × 8 - 60 + 8 2 / +
         */

        //测试
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; //76

        /*
            1.先将表达式放入ArrayList中
            2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */

        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList = " + list);
        int res = calculate(list);
        System.out.println("result = " + res);

    }

    //将中缀表达式转换为对应的list
    // s="1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int i = 0; //这是一个指针，用于遍历 中缀表达式 字符串
        String str; //对多位数的拼接
        char c; //每遍历一个字符，就放入到c
        do{
            //如果c是一个非数字，需要加入到list
            if((c = s.charAt(i)) < '0'|| (c = s.charAt(i)) > '9'){
                list.add(String.valueOf(c));
                i++;
            }else{ //如果是一个数
                str = ""; //先将str重置成""   '0'[48] -> '9'[57]
                while(i < s.length() && (c = s.charAt(i)) >= '0' && (c = s.charAt(i)) <= '9'){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < s.length());
        return list;
    }

    //将得到的中缀表达式对应的list -> 后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> list){
        Stack<String> s1 = new Stack<>(); //运算符栈 s1
        /*
            说明：因为 s2 这个栈，在整个转换过程中没有pop操作，而且后面我们还需要逆序输出，比较麻烦
                 因此我们在这里不使用 Stack<String> s2 而是直接使用 List<String> s2
         */
//        Stack<String> s2 = new Stack<>(); //储存中间结果的栈 s2
        List<String> s2 = new ArrayList<>(); //存储中间结果的 s2

        //遍历list
        for(String item: list){
            //如果是一个数，加入s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号")"，则依次弹出栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //将左括号"("弹出,丢弃这一对括号
            }else{
                //当item的优先级小于等于 s1 栈顶运算符，将 s1 栈顶的运算符弹出并加入到 s2 中，再次将item与 s1 中的新栈顶进行比较
                //利用Operation类来比较优先级的高低
                while(s1.size() != 0 && Operation.getVal(s1.peek()) >= Operation.getVal(item)){
                    s2.add(s1.pop());
                }
                //还要将item压入s1
                s1.push(item);
            }
        }

        //将 s1 剩余的运算符依次弹出并加入 s2
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        for(String ele: split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();

        //遍历ls
        for(String item: ls){
            //用正则表达式取出数，\d表示匹配一个数字字符，+表示匹配前面的子表达式一次或多次，即\d+匹配的是多位数
            if(item.matches("\\d+")){
                stack.push(item); //入栈
            }else{
                //pop出两个数，并运算，再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res;

                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num2 - num1;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num2 / num1;
                }else{
                    throw new RuntimeException("运算符错误");
                }

                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getVal(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                //System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}