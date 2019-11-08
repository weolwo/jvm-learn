package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 */
public class ClassLoadTest10 {

    static {
        System.out.println("ClassLoadTest10");
    }

    public static void main(String[] args) {
        Parent2 parent2;
        parent2 = new Parent2();
        System.out.println(Parent2.a);
        System.out.println(Child2.b);
        /*执行结果：由于父类已经初始化过了所以Parent2只输出一次
         * ClassLoadTest10
         * Parent2
         * 2
         * Child2
         * 3
         */
    }
}

class Parent2 {
    static int a = 2;

    static {
        System.out.println("Parent2");
    }
}

class Child2 extends Parent2 {
    static int b = 3;

    static {
        System.out.println("Child2");
    }
}