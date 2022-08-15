package recursion;

public class RecursionTest {
    public static void main(String[] args) {

        //打印问题回顾递归调用机制
        /*
            没有else语句打印 n = 2 n = 3 n = 4
            有else语句打印 n = 2
         */
        test(4);
        System.out.println();

        //阶乘问题
        int res = factorial(4);
        System.out.println("res = " + res);

    }

    //打印问题
    public static void test(int n){
        if(n > 2){
            test(n - 1);
        }//else{
            System.out.println("n = " + n);
        //}
    }

    //阶乘问题
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }else{
            return factorial(n - 1) * n;
        }
    }
}
