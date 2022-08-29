package sort;

import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {
        //排序的数组
        int a[]={100, 93, 97, 92, 96, 99, 92, 89, 93, 97, 90, 94, 92, 95};
        int b[]=countSort(a);
        System.out.println("排序前：" + Arrays.toString(a));
        System.out.println("排序后：" + Arrays.toString(b));
    }

    public static int[] countSort(int[] arr){
        int[] b = new int[arr.length]; //存放排序输出

        int max = arr[0]; //排序数组中最大的元素
        for(int i : arr){
            if(i > max){
                max= i;
            }
        }

        int[] c = new int[max + 1]; //临时存储空间，用于计数要排序数组arr中的每个元素有多少个，c的下标i对应arr中的元素i，c[i]表示元素i在arr中出现过的次数
        for(int value: arr){
            c[value]++; //c[value]表示arr中value元素的个数
        }

        for(int i = 1; i < c.length; i++){
            c[i] = c[i] + c[i - 1]; //现在c[i]包含的是arr中小于或等于i的元素个数
        }

        for(int i = arr.length - 1; i >= 0; --i){
            c[arr[i]] = c[arr[i]] - 1;
            b[c[arr[i]]] = arr[i];
        }

        return b;
    }
}
