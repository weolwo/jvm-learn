package com.poplar.classload;

/**
 * <p>对于静态字段来说，只有直接定义了该字段的类才会被初始化
 * 当一个类在初始化时，要求父类全部都已经初始化完毕</p>
 * <P>-XX:+TraceClassLoading，用于追踪类的加载信息并打印出来</p>
 * <h2>jvm参数设置</h2>
 * <P> -XX:+option，表示开启option选项</p>
 * <P>-XX:-option，表示关闭option选项</p>
 * <P>-XX:option=value，表示将option的值设置为value</p>
 */
public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Parent.str1);
        ClassLoadTest.class.getClassLoader().loadClass("");
    }
}

class Parent {
    static String str1 = "welcome Parent";

    static {
        System.out.println("from Parent class");
    }
}

class Child extends Parent {
    static String str2 = "welcome Child";

    static {
        System.out.println("from Child class");
    }
}
