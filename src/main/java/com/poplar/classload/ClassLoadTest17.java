package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class ClassLoadTest17 {
    public static void main(String[] args) throws Exception {
        CustomClassLoader2 loader = new CustomClassLoader2("loader");
        Class<?> clazz = loader.loadClass("com.poplar.classload.Simple");
        System.out.println(clazz.hashCode());
        //如果注释掉该行，就并不会实例化MySample对象，不会加载MyCat（可能预先加载）
        Object instance = clazz.newInstance();//实列化Simple和MyCat
    }
}
