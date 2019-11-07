package com.poplar.classload;

/**
 * Created By poplar on 2019/11/6
 * <p>常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 本质上，调用类并没有直接调用到定义常量的类，因此并不会触发定义常量的类的初始化
 * <b>注意:</b>这里指的是将常量存到ClassLoadTest2的常量池中，之后ClassLoadTest2和Student就没有任何关系了。
 * 甚至我们可以将MyParent2的class文件删除</p>
 * <h2>常见的注记符</h2>
 * <p>助记符 ldc：表示将int、float或者String类型的常量值从常量池中推送至栈顶</p>
 * <p>助记符 bipush：表示将单字节（-128-127）的常量值推送到栈顶</p>
 * <p>助记符 sipush：表示将一个短整型值（-32768-32369）推送至栈顶</p>
 * <p>助记符 iconst_1：表示将int型的1推送至栈顶（iconst_m1到iconst_5）</p>
 */
public class ClassLoadTest2 {
    public static void main(String[] args) {
        System.out.println(Student.m);
    }
}

class Student {
    static final String str = "hello world";
    static final short n = 127;
    static final int s = 1;
    static final int m = 6;

    static {
        System.out.println("Student static blocking");
    }
}