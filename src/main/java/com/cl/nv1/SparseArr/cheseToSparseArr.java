package com.cl.nv1.SparseArr;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 陈龙
 * @description 实现压缩数组 使用稀疏数组 来对二维数组进行压缩然后进行持久化存储 以围棋游戏封盘 重开盘为例
 */
public class cheseToSparseArr {
    public static final String FILE_PATH = "D:\\test\\test.txt";
    @Test
    public void cheseToSpa(){
        //展示 将棋盘转换成的二维数组
        int[][] chese = new int[11][11];
        chese[1][2] = 1;
        chese[2][3] = 2;
        chese[3][5] = 1;
        //sum记录一共有几个有效数据
        int sum = 0;
        for (int[] ints : chese) {
            for (int data : ints) {
                if (data != 0){
                    sum++;
                }
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将该二维数组转化为 稀疏数组
        int[][] sparse = new int[sum+1][3];
        sparse[0][0] = 11;
        sparse[0][1] = 11;
        //用于记录第几个有效值
        int count = 0;
        //遍历二维数组为稀疏数组赋值
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chese[i][j] != 0){
                    count++;
                    sparse[count][0] = i;
                    sparse[count][1] = j;
                    sparse[count][2] = chese[i][j];
                }
            }
        }
        sparse[0][2] = count;
        System.out.println();
        //输出该稀疏数组
        for (int i = 0; i < sparse.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparse[i][0],sparse[i][1],sparse[i][2]);
        }
        //输出的稀疏数组正确 做持久化存储
        System.out.println("开始写入文件......");
        File file = new File(FILE_PATH);
        try {
        if (file.exists()){
            file.delete();
            file.createNewFile();
        }
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter os = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter bw = new BufferedWriter(os);
            for (int[] ints : sparse) {
                for (int da : ints) {
                    bw.write(String.valueOf(da)+"\t");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件写入完成......");
    }
}
