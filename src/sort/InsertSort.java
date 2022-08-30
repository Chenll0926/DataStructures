package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[25];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = (int)(Math.random() * 80000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr1));

        insertSort(arr1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
        System.out.println("排序后:");
        System.out.println(Arrays.toString(arr1));
    }

    public static void insertSort(int[] arr){
        int insertValue;
        int insertIndex;

        for(int i = 1; i < arr.length; i++){
            insertValue = arr[i];
            insertIndex = i - 1;

            /*  给insertValue找插入位置
                说明：
                1.insertIndex >= 0 保证在给insertValue找插入位置，不越界
                2.insertValue < arr[insertIndex] 待插入的数的位置需要满足的条件，若没有找到，将arr[insertIndex]后移
             */

            while(insertIndex >=  0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //退出while循环时，说明插入的位置insertIndex + 1已找到
            //判断是否赋值
            if(insertIndex != i){
                arr[insertIndex + 1] = insertValue;
            }
        }
    }
}
