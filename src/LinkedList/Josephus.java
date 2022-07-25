package LinkedList;

/*
    约瑟夫环问题：设编号为1，2，... ，n的n个人围坐一圈，
    约定编号为k (1 <= k <= n) 的人从1开始报数，数到m的那个人出列，
    它的下一位又从1开始报数，数到m的那个人又出列，直到所有人出列为止，由此产生一个出队编号的序列
 */

public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoys(5);
        circleSingleLinkedList.showBoys();
    }
}

class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加节点，组成环形链表
    public void addBoys(int nums){
        if(nums < 1){
            System.out.println("nums 的值不正确");
        }

        //辅助指针
        Boy curBoy = null;

        //构建环形链表
        for(int i = 1; i <= nums; i++){
            Boy boy = new Boy(i);

            if(i == 1){
                first = boy;
                first.setNext(first); //构成环
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoys(){
        if(first == null){
            System.out.println("没有节点");
            return;
        }

        Boy curBoy = first;
        while(true){
            System.out.printf("节点编号%d\n", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出圈人的顺序

    /**
     *
     * @param startNo
     *                  表示从第几个小孩开始数
     * @param countNum
     *                  表示数几下
     * @param nums
     *                  表示最初有几个小孩在圈里
     */
    public void countBoys(int startNo, int countNum, int nums){

    }
}

//表示节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}