package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex);

        List<Integer> resIndexList = binarySearch2(arr, 0 ,arr.length - 1, 1000);
        System.out.println("resIndexList = " + resIndexList);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 需要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findValue){
        if(left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if(findValue < midValue){
            return binarySearch(arr, left, mid - 1, findValue);
        }else if(findValue > midValue){
            return binarySearch(arr, mid + 1, right, findValue);
        }else{
            return mid;
        }
    }

    //当有序数组中有多个相同数值时，如何将所有的数值都查找到
    /*
        1.在mid找到索引值，不要立刻返回
        2.向mid左边扫描，将所有符合findValue的元素的下标，加入到集合ArrayList
        3.向mid右边扫描，将所有符合findValue的元素的下标，加入到集合ArrayList
        4.返回ArrayList
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue){
        if(left > right){
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if(findValue < midValue){
            return binarySearch2(arr, left, mid - 1, findValue);
        }else if(findValue > midValue){
            return binarySearch2(arr, mid + 1, right, findValue);
        }else{
            List<Integer> resIndexList = new ArrayList<>();

            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp] != findValue){
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }

            resIndexList.add(mid);

            while(true){
                if(temp > arr.length - 1 || arr[temp] != findValue){
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
