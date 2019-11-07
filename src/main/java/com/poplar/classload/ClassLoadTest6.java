package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 * 准备和初始化阶段静态变量赋值问题
 */
public class ClassLoadTest6 {

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.a);//1
        System.out.println(Singleton.b);//0
        System.out.println(ClassLoadTest6.class.getClassLoader());
    }
}

class Singleton {

    public static int a;

    private static Singleton instance = new Singleton();

    private Singleton() {
        a++;
        b++;
        System.out.println(a);//1
        System.out.println(b);//1
    }

    public static int b = 0;

    public static Singleton getInstance() {
        return instance;
    }
}