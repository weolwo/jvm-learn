package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class ClassLoadTest17_1 {
    public static void main(String[] args) throws Exception {
        CustomClassLoader2 loader = new CustomClassLoader2();

        Class<?> clazz = loader.loadClass("com.poplar.classload.Simple");
        System.out.println(clazz.hashCode());
        //如果注释掉该行，就并不会实例化MySample对象，不会加载MyCat（可能预先加载）
        Object instance = clazz.newInstance();//实列化Simple和MyCat
        //MyCat是由加载MySample的加载器去加载的：
        //如果只删除classpath下的MyCat，则会报错，NoClassDefFoundError；
        //如果只删除classpath下的MySample，则由自定义加载器加载桌面上的MySample，由系统应用加载器加载MyCat。
    }
}
