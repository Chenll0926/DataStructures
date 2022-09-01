package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        quickSort(arr, 0 , arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[8000000];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = (int)(Math.random() * 8000000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);


        quickSort(arr1, 0 , arr1.length - 1); //交换法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left; //左下标
        int r = right; //右下标
        int pivot = arr[(left + right) / 2]; //中轴值
        int temp; //临时变量，交换时用
        //while循环的目的时让比pivot值小的放在左边，比pivot值大的放在右边
        while(l < r){
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while(arr[l] < pivot){
                l += 1;
            }

            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while(arr[r] > pivot){
                r -= 1;
            }

            //如果 l >= r 说明pivot的左右两边的值，已经按照左边全都是小于等于pivot值，右边全部都是大于等于pivot值的方式排列
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完之后，发现arr[l] == pivot，r--,后移
            if(arr[l] == pivot){
                r -= 1;
            }

            //如果交换完之后，发现arr[r] == pivot，l++,后移
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果 l == r，必须l++，r--，否则出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }
    }
}
