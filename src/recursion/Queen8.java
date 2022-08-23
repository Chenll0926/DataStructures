package recursion;

/*
    八皇后问题：在 8×8 的国际象棋盘摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法

    思路：
     1.第一个皇后先放第一行第一列
     2.第二个皇后放第二行第一列，然后判断是否可行，若不可行，继续放在第二列、第三列… 依次放完所有列，找到合适的列
     3.继续第三个皇后，还是第一列、第二列……直到第八个皇后也能放在一个不冲突的位置，即为找到一个正确解
     4.当得到一个正确解时，在栈回到上一个栈时，就会开始进行回溯（递归调用机制），即将第一个皇后，放到第一列的所有正确解，全部得到
     5.然后回头将第一个皇后放到第二列，后面循环执行1、2、3、4的步骤

     说明：理论上应该创建一个二维数组来表示棋盘，但实际上可以通过算法，用一个一维数组即可解决问题。
          如： arr = {0, 4, 7, 5, 2, 6, 1, 3} 对应arr下标，可以表示第几行，即第几个皇后，
              arr[i] = val -> 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列
 */

public class Queen8 {
    //定义一个max表示一共有几个皇后
    int max = 8;

    //定义一个array，保存皇后存放位置的结果 e.g arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n", count);
        System.out.printf("判断冲突的次数%d次\n", judgeCount);
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有 for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n){
        if(n == max){ //八个皇后都已经放好
            print();
            return;
        }

        //依次放入皇后，检查是否冲突
        for(int i = 0; i < max; i++){
            //先把第n个皇后，放到第n行的第1列
            array[n] = i;
            //判断放到第n行第i列中是否冲突
            if(judge(n)){ //不冲突
                //换下一个皇后，开始递归
                check(n + 1);
            }
            //如果冲突就i+1,即把第n个皇后放到第n行的后一列
        }
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已摆放的皇后冲突

    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        /*
            说明：
                1.array[i] == array[n] 表示判断第 n 个皇后是否和前面的 n-1 个皇后在同一列
                2.Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线上（等腰直角三角形）
         */
        for(int i = 0; i < n; i++){
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                judgeCount++;
                return false;
            }
        }
        return true;
    }

    //编写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for(int i = 0; i < max; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
