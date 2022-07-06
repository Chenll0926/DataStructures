package sparseArray;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) throws Exception{
        serializableSparseArr();
        int[][] sparseArr = deserializeSparseArr();

        //将稀疏数组恢复成原始数组
        /*
        1.读取稀疏数组第一行，根据第一行数据创建原始的二维数组
        2.读取后几行的数据，赋值给原始数组
         */
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的数组
        System.out.println();
        System.out.println("The recover array: ");
        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    //序列化
    private static void serializableSparseArr()throws IOException{
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        int sum = 0;
        for(int i = 0; i < chessArr.length; i++){
            for(int j = 0; j < chessArr.length; j++){
                if(chessArr[i][j] != 0){
                    sum++;
                }
            }
        }

        SparseAnArray sparseAnArray = new SparseAnArray();
//        sparseAnArray.makeSum(chessArr);
        sparseAnArray.setSum(sum);

        File file = new File("d:/map.data");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        for(int[] row : sparseAnArray.sparseArr){
//            for(int data : row){
//                oos.writeObject(data);
//                oos.writeObject(" ");
//            }
//            oos.writeObject("\r\n");
//        }
        oos.writeObject(sparseAnArray.makeSparseArr(chessArr));
        System.out.println("序列化完成");
        oos.close();
    }

    //反序列化
    private static int[][] deserializeSparseArr() throws Exception{
        File file = new File("d:/map.data");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
//        SparseAnArray sparseAnArray = (SparseAnArray) ois.readObject();
        int[][] sparseArr = (int[][]) ois.readObject();
        System.out.println("反序列化完成");
        return sparseArr;
    }
}
