package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        mergeSort(arr, 0 , arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[8000000];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = (int)(Math.random() * 8000000); // 生成一个[0, 80000)的数
        }
        int[] temp1 = new int[arr1.length];

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);


        mergeSort(arr1, 0, arr1.length - 1, temp1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (right + left) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并方法

    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left; //初始化i，左边有序序列的初始索引
        int j = mid + 1; //初始化j，右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i <= mid && j <= right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素填充到temp中，然后t++,i++
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
            }else{ //反之，将右边有序序列的当前元素填充到temp中
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //把有剩余数据的一边的数据依次全部填充到temp中
        while(i <= mid){ //左边有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }

        while(j <= right){ //右边有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将temp的元素拷贝到arr，注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;

        //第一次合并tempLeft = 0，right = 1 // tempLeft = 1, right = 2 // tempLeft = 0, right = 3
        //最后一次tempLeft = 0, right = 7
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
