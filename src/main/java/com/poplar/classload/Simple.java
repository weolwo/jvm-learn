package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class Simple {
    public Simple() {
        System.out.println("Simple by Load " + Simple.class.getClassLoader());
        new MyCat();
    }
}
