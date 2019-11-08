package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 */
public class ClassLoadTest9 {

    static {
        System.out.println("ClassLoadTest9");
    }

    public static void main(String[] args) {
        System.out.println(Child1.a);
    }
}

class Parent1 {
    static int a = 9;

    static {
        System.out.println("Parent1");
    }
}

class Child1 extends Parent1 {
    static int b = 0;

    static {
        System.out.println("Child1");
    }
}

//最后输出顺序
//ClassLoadTest9
// Parent1
//9