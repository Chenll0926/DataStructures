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
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //按照编号的顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList.list();

        //测试修改节点
        HeroNode newHeroNode = new HeroNode(2,"小卢", "玉麒麟11");
        singleLinkedList.update(newHeroNode);

        System.out.println();
        System.out.println("After update");

        //显示
        singleLinkedList.list();
        System.out.println();
        System.out.println("After delete");

        singleLinkedList.delete(1);
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

    //按顺序插入到指定位置，如果已经有这个排名顺序，则添加失败并给出提示
    public void addByOrder(HeroNode heroNode){
        //头节点不能动，仍然通过一个辅助指针来帮助找到添加的位置
        //因为是单链表，所以temp是加入位置的前一个节点，否则无法插入
        HeroNode temp = head;
        boolean flag = false; // 标识添加的编号是否存在，默认为false
        while(true){
            if(temp.next == null){ //说明temp在链表最后
                break;
            }
            if(temp.next.no > heroNode.no){ //位置找到了，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){ //希望添加的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag
        if (flag){
            System.out.printf("待插入编号 %d 存在,不能加入\n", heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据编号no来修改，即no不能修改
    /*
        1.根据newHeroNode的no来修改即可
     */
    public void update(HeroNode heroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("The list is empty");
            return;
        }

        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点

        while(true){
            if(temp == null){
                break; //到了链表的结尾，表示链表遍历结束
            }
            if(temp.no == heroNode.no){
                //找到要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.name = heroNode.name;;
            temp.nickName = heroNode.nickName;
        }else{
            //没有找到节点
            System.out.printf("The node with no %d is not found\n", heroNode.no);
        }
    }

    //删除节点
    /*
        1.head不能动，因此需要一个temp辅助节点找到但删除节点的前一个节点
        2.说明我们在比较是，是temp.next.no 和 需要删除的节点no比较
     */
    public void delete(int no){
        if(head.next == null){
            System.out.println("List is empty");
            return;
        }

        HeroNode temp = head;
        boolean flag = false; //判断是否找到待删除节点

        while(true){
            if(temp.next == null){
                break; //已经在链表最后
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            //删除节点
            temp.next = temp.next.next;
        }else{
            System.out.println("The node which want to be delete is not found");
        }
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
