package LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero2);
        //显示
        singleLinkedList.list();
    }
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    /*
    思路，当不考虑编号顺序时：
    1.找到当前链表的最后节点
    2.将最后这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助节点temp遍历
        HeroNode temp = head;

        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当推出while循环时，temp就指向链表最后
        temp.next = heroNode;
    }

    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("The list is empty");
            return;
        }

        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }
    }
}

//先定义一个HeroNode, 每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int hNo, String hName, String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
