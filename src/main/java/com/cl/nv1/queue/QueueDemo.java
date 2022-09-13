package com.cl.nv1.queue;

import java.util.Scanner;

public class QueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key ;//用户输入
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(showHead):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQ();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQ(value);
                    break;
                case 'g':
                    try {
                       int data = arrayQueue.getQ();
                        System.out.println("取出的数据是:"+data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.showHead();
                        System.out.println("队列头数据是:"+head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    System.out.println("程序退出~~~");
                    break;
                default:
                    break;
            }
        }
    }
}
//使用数组模拟一个队列
class ArrayQueue{
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组存数据

    //队列构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头的前一个位置
        rear = -1;//指向队列尾的具体的位置
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列 进队
    public void addQ(int data){
      //判断队列是否满
      if (isFull()){
          System.out.println("队列满，不可加入数据");
          return;
      }
      rear++;//让rear后移
        arr[rear] = data;
    }
    //获取队列数据 出队
    public int getQ(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;//front后移
        return arr[front];
    }
    //显示当前队列数据
    public void showQ(){
        if (isEmpty()){
            System.out.println("没有数据~~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列头数据（不是取数据）
    public int showHead (){
        if (isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front+1];
    }
}