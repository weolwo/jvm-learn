package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class MyCat {
    public MyCat() {
        System.out.println("MyCat by load " + MyCat.class.getClassLoader());
    }
}
