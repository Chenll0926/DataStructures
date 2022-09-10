package search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findValue 查找值
     * @return 如果找到，返回对应的下标，没找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue){
        //findValue < arr[0]和findValue > arr[arr.length - 1]必须需要，否则mid可能越界
        if(left > right || findValue < arr[0] || findValue > arr[arr.length - 1]){
            return -1;
        }

        //求自适应mid
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if(findValue > midValue){ //向右递归
            return insertValueSearch(arr, mid + 1, arr.length - 1, findValue);
        }else if(findValue < midValue){ //向左递归
            return insertValueSearch(arr, left, mid - 1, findValue);
        }else{
            return mid;
        }
    }
}
