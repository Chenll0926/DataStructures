package Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("数组实现环形队列测试");
        //创建队列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4); //有效数据个数实际为 4 - 1 = 3个
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch(key){
                case 's' :
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是: %d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = circleArrayQueue.headQueue();
                        System.out.printf("队列头的数据是： %d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/*
    1.front指向队列的第一个元素
    2.rear指向队列的最后一个元素的后一个
    3.一个大小为maxSize的数组最多可以存放maxSize-1个元素
    4.front == rear时，队列为空
    5.(rear + 1) % maxSize == front时，队列为满
    6.环形队列中的数据个数为(rear + maxSize - front) % maxSize个
 */
class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int maxSize){
        front = 0;
        rear = 0;
        arr = new int[maxSize];
        this.maxSize = maxSize;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    //进队
    public void addQueue(int data){
        if(isFull()){
            System.out.println("The queue is full");
            return;
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    //出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("The queue is empty");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("The queue is empty");
            return;
        }
            //遍历
            for(int i = front; i < front + size(); i++){
                System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
            }

    }

    //计算队列数据个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("The queue is empty");
        }

        return arr[front];
    }
}
