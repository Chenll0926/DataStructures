package sparseArray;

import java.io.Serializable;

public class SparseAnArray implements Serializable {
    int sum;

    public int getSum() {
        return sum;
    }

//    public int[][] getSparseArr() {
//        return sparseArr;
//    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    //    public void makeSum(int array[][]){
//        for(int[] row : array){
//            for(int i = 0; i < array.length; i++){
//                if(row[i] != 0){
//                    sum++;
//                }
//            }
//        }
//    }

    public int[][] makeSparseArr(int array[][]){
        int sparseArr[][] = new int[sum + 1][3];

        sparseArr[0][0] = array.length;
        sparseArr[0][1] = array.length;
        sparseArr[0][2] = sum;

        int count = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                if(array[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        return sparseArr;
    }
}
