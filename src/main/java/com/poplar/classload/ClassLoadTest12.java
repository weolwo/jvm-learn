package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 * 调用类的loadClass并不是主使实用类，不会导致类的初始化
 */
public class ClassLoadTest12 {
    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = classLoader.loadClass("com.poplar.classload.G");
        System.out.println("-------------------------------");
        Class<?> clazz = Class.forName("com.poplar.classload.G");//反射会导致一个类的初始化
        System.out.println(clazz);
        //输出结果：
        //G
        //class com.poplar.classload.G
    }
}

class G {
    static {
        System.out.println("G");
    }
}