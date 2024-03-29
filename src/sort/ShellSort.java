package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        //shellSort(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[80000];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = (int)(Math.random() * 80000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);
//        System.out.println("排序前:");
//        System.out.println(Arrays.toString(arr1));

        shellSort(arr1); //交换法
        //shellSort2(arr1); //移位法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
//        System.out.println("排序后:");
//        System.out.println(Arrays.toString(arr1));
    }

    //希尔排序时，对有序序列在插入时采用交换法
    public static void shellSort(int[] arr){
        int temp;

        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                //遍历各组中所欲的元素（共有gap组），步长为gap
                for(int j = i -gap; j >= 0; j -= gap){
                    //如果当前元素大于加上步长后的那个元素，需要交换
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对交换式的希尔排序进行优化->位移法
    public static void shellSort2(int[] arr){

        //增量gap，并逐步缩小的增量
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            //从第gap个元素，逐个对其所在的的组进行直接插入排序
            for(int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];

                if(arr[j] < arr[j - gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
