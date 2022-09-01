package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[8000000];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = (int)(Math.random() * 8000000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);


        radixSort(arr1); //交换法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
    }

    public static void radixSort(int[] arr){
        //得到数组中最大数的位数
        int max = arr[0];
        for(int i : arr){
            if(i > max){
                max = i;
            }
        }

        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        /*
            说明：
            1.二维数组包含10个一维数组
            2.为了防止在放入数的时候数据溢出，每个一维数组（桶）大小定为arr.length
            3.基数排序是用空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        //为了可以记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        //例如：bucketElementsCount[0]记录的就是bucket[0]桶中数据的个数
        int[] bucketElementsCount = new int[10];

        for(int i = 0, n = 1; i < maxLength; i++, n *= 10){
            //针对每个元素的对应位进行排序处理，第一次是个位，第二次是十位……
            for(int j = 0; j < arr.length; j++){
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放到对应的桶中
                bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[j];
                bucketElementsCount[digitOfElement]++;
            }
            //按照这个桶的顺序，一维数组的下标依次取出数据，放入原来数组
            int index = 0;
            //遍历每一个桶，并将桶中数据放入到原数组
            for(int k = 0; k < bucketElementsCount.length; k++){
                //如果桶中有数据，我们采访到原数组中
                if(bucketElementsCount[k] != 0){
                    //循环该桶即第k个桶（即第k个一维数组），放入
                    for(int l = 0; l < bucketElementsCount[k]; l++){
                        //取出元素放到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementsCount[k] = 0
                bucketElementsCount[k] = 0;
            }
        }
    }
}
