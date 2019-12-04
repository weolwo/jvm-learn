package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class ClassLoadTest22 {

    static {
        System.out.println("ClassLoadTest22 invoked");
    }

    //扩展类加载器只加载jar包，需要把class文件打成jar
    //此列子中将扩展类加载的位置改成了当前的classes目录
    public static void main(String[] args) {
        System.out.println(ClassLoadTest22.class.getClassLoader());
        System.out.println(ClassLoadTest2.class.getClassLoader());
    }
}
