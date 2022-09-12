package hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //菜单
        String key;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");


            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入需要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少跳链表

    public HashTab(int size) {
        this.size = size;

        //初始化empLinkedListArray
        empLinkedListArray= new EmpLinkedList[size];
        for(int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应该添加到哪条链表
        int empLinkedListNO = hasFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表，遍历HashTab
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        //使用散列函数确定应该到哪一条链表去找
        int empLinkedListNo = hasFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpByID(id);
        if(emp != null){
            System.out.printf("在第%d链表中找到雇员 id = %d\n", (empLinkedListNo + 1), id);
        }else{
            System.out.println("没有找到该雇员");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hasFun(int id){
        return id % size;
    }
}

//表示一个雇员（节点）
class Emp{
    public int id;
    public String name;
    public Emp next; //表示下一个雇员，默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，执行第一个emp，因此我们这个链表的head是直接指向第一个emp
    private Emp head;

    /*
        添加雇员到链表：
          1.假定当添加雇员时，id是自增长，即id的分配总是由小到大，因此我们将该雇员直接加入到本链表的最后即可
     */
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }

        //如果不是第一个雇员，需要一个辅助指针帮助定位到最后
        Emp temp = head;
        while(true){
            if(temp.next == null){ //说明到链表最后
                break;
            }
            temp = temp.next; //后移
        }
        temp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }

        System.out.printf("第" + (no + 1) + "链表的信息为：");
        Emp temp = head; //辅助指针
        while(true){
            System.out.printf("=> id = %d name = %s\t", temp.id, temp.name);
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //根据id查找雇员，如果找到返回emp，未找到返回null
    public Emp findEmpByID(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        Emp temp = head;
        while(true){
            if(temp.id == id){ //找到
                break;
            }
            if(temp.next == null){ //遍历链表未找到
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}
