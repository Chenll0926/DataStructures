package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 109, 1, -1, 90, 123};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr1[i] = (int)(Math.random() * 80000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);

        selectSort(arr1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
    }

    public static void selectSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < min){ //说明设定的最小值并不是最小
                    minIndex = j;
                    min = arr[j];
                }
            }
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
