package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 */
public class ClassLoadTest7 {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());//null 由于String是由根加载器加载，在rt.jar包下
        System.out.println(C.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@73d16e93
    }
}

class C {

}
