package LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();

        System.out.println();

        //修改
        System.out.println("修改节点测试：");
        HeroNode2 hero5 = new HeroNode2(4, "公孙胜" , "入云龙");
        doubleLinkedList.update(hero5);
        doubleLinkedList.list();
        System.out.println();

        //删除
        System.out.println("删除节点测试:");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {
    //头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历（显示链表）
    public void list() {
        if (head.next == null) {
            System.out.println("Linked list is empty");
            return;
        }

        HeroNode2 temp = head.next;

        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加节点到链表末尾
    public void add(HeroNode2 heroNode){
        //辅助节点
        HeroNode2 temp = head;

        //while循环结束，代表temp已经指到链表的最后一个节点
        while(true){
            if(temp.next == null){
                break;
            }

            temp = temp.next;
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点内容
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("Linked list is empty");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false; //表示是否找到指定节点

        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true; //找到节点
                break;
            }

            temp = temp.next;
        }

        if(flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            System.out.printf("Do not found the node %d\n", heroNode.no);
        }

    }

    //删除节点
    public void delete(int no){
        if(head.next == null){
            System.out.println("The linked list is empty, could not be deleted");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false; //是否找到待删除节点

        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if(flag){
            temp.pre.next = temp.next;
            //避免删除最后一个节点出现空指针问题
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("Can not find the node %d\n", no);
        }
    }

    //按顺序添加
    public void addByOrder(HeroNode2 heroNode){
        //辅助节点
        HeroNode2 temp = head;
        boolean flag1 = false; //是否存在相同编号的节点
        boolean flag2 = false; //链表是否为空

        while(true){
            if(temp.no > heroNode.no){
                break; //找到位置
            }
            if(temp.next == null){
                flag2 = true;
                break;
            }
            if(temp.no == heroNode.no){
                flag1 = true;
                break;
            }
            temp = temp.next;
        }

        if(flag1){
            System.out.printf("The node with NO.%d is already exist, failed to add\n", heroNode.no);
        }else if(flag2){
            temp.next = heroNode;
            heroNode.pre = temp;
        }else{
            temp.pre.next = heroNode;
            heroNode.pre = temp.pre;
            heroNode.next = temp;
            temp.pre = heroNode;
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 pre;
    public HeroNode2 next;

    //构造器

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，重新toString
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}