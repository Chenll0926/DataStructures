package Stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            key = scanner.next();
            switch (key){
                case "show" :
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    }catch (Exception e){
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class LinkedListStack{
    private Node top = new Node(-1);

    public boolean isEmpty(){
        return top.getNext() == null;
    }

    public void push(int value){
        Node node = new Node(value);
        if(top.getNext() == null){
            top.setNext(node);
            return;
        }

        //头插法
        Node temp = top.getNext();
        top.setNext(node);
        node.setNext(temp);
    }

    public int pop(){
        if(top.getNext() == null) {
            throw new RuntimeException("栈为空");
        }

        Node temp = top.getNext();
        int res = temp.getValue();
        top.setNext(temp.getNext());
        return res;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }

        Node temp = top;
        while(temp.next != null){
            System.out.println("Node value: " + temp.getNext().getValue());
            temp = temp.getNext();
        }
    }
}

class Node{
    private int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
