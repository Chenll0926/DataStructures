package sort;
;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 20, 10};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        System.out.println("排序后:");
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr1[i] = (int)(Math.random() * 80000); // 生成一个[0, 80000)的数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是：" + date1Str);

        bubbleSort(arr1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是:" + date2Str);
    }

    public static void bubbleSort(int[] arr){
        int temp; //临时变量
        boolean flag = false; //表示是否进行交换

        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){ // 排序中未发生交换
                break;
            }else{
                flag = false; //重置flag，进行下一次判断
            }
        }
    }
}
