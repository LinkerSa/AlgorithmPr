package com.cl.nv1.queue;

import java.util.Scanner;

public class QueueCircle {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列");
        CircleArr circleArr = new CircleArr(4);
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
                    circleArr.showQ();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArr.addQ(value);
                    break;
                case 'g':
                    try {
                        int data = circleArr.getQ();
                        System.out.println("取出的数据是:"+data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = circleArr.showHead();
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
class CircleArr{
    private int maxSize;//表示数组最大容量
    private int front;//front变量的含义做出调整:front指向队列的第一个元素 也就是说arr[front]就是队列第一个元素 初始值为0
    private int rear;//rear变量含义做出调整:rear指向最后一个元素的位置，因为希望空出一个空间做约定，rear初始值为0
    private int[] arr;//该数组存数据

    public CircleArr(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = data;
        //将rear进行取模
        rear = (rear+1) % maxSize;
    }
    //获取队列数据 出队
    public int getQ(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //先把front对应的值保存到临时对象
        int value = arr[front];
        //然后将front后移
        front = (front+1) % maxSize;
        //将临时保存的变量返回
        return value;
    }
    //显示当前队列数据
    public void showQ(){
        if (isEmpty()){
            System.out.println("没有数据~~~");
            return;
        }
        //从front开始遍历 遍历多少个元素就可以
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效数据范围
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头数据（不是取数据）
    public int showHead (){
        if (isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front];
    }
}