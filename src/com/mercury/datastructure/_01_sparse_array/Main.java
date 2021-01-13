package com.mercury.datastructure._01_sparse_array;

/**
 * 稀疏数组
 */
public class Main {
    public static void main(String[] args) {
        //定义二维数组
        int [][] arr = new int[10][8];
        arr[1][2] = 1;
        arr[2][1] = 2;
        arr[5][6] = 1;
        arr[8][3] = 2;

        // 将二维数组转换成稀疏数组
        int[][] sparseArr = transform2SparseArr(arr);

        // 将稀疏数组还原成二维数组
        revert2Arr(sparseArr);
    }

    /**
     * 稀疏数组 ==》 二维数组
     * @param sparseArr
     */
    private static void revert2Arr(int[][] sparseArr) {
        // 1、得到原数组行和列
        int rows = sparseArr[0][0];
        int cols = sparseArr[0][1];

        int [][] arr = new int[rows][cols];

        // 2、遍历稀疏数组，还原数据
        for (int i = 1; i < sparseArr.length; i++) {
            int r = sparseArr[i][0];
            int c = sparseArr[i][1];
            int val = sparseArr[i][2];

            arr[r][c] = val;
        }

        printArr(arr);
    }

    /**
     * 二维数组 ==》 稀疏数组
     * @param arr
     */
    private static int[][] transform2SparseArr(int[][] arr) {
        // 原数组的行数和列数
        int rows = arr.length;
        int columns = arr[0].length;

        //1、计算稀疏数组的行数【原数组有值的个数+1】和列数【3】 ==》 新建稀疏数组
        int size = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    size++;
                }
            }
        }
        int [][] sparseArr = new int[size][3];

        //2、遍历原二维数组，将值填充到稀疏数组中【第一行记录数组原来的行列和总元素个数】
        int index = 0;
        sparseArr[index][0] = rows;
        sparseArr[index][1] = columns;
        sparseArr[index][2] = size-1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = arr[i][j];
                }
            }
        }

        printArr(sparseArr);

        return sparseArr;
    }

    private static void printArr(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }


}
