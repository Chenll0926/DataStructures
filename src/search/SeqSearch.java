package search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        seqSearch(arr, -1);
    }

    public static void seqSearch(int[] arr, int value){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == value){
                System.out.println("找到数据，下标为" + i);
                return;
            }
        }
        System.out.println("没有找到数据");
    }
}
