package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class ClassLoadTest17_2 {
    public static void main(String[] args) throws Exception {
        CustomClassLoader2 loader = new CustomClassLoader2("loader");
        loader.setPath("C:\\Users\\poplar\\Desktop\\");
        Class<?> clazz = loader.loadClass("com.poplar.classload.Simple2");
        System.out.println(clazz.hashCode());
        //如果注释掉该行，就并不会实例化MySample对象，不会加载MyCat（可能预先加载）
        Object instance = clazz.newInstance();//实列化Simple和MyCat
        //修改MyCat2后，仍然删除classpath下的Simple2，留下MyCat2，程序报错
        //因为命名空间，父加载器找不到子加载器所加载的类，因此MyCat2找不到
    }
}

class MyCat2 {
    public MyCat2() {
        System.out.println("MyCat by load " + MyCat.class.getClassLoader());
        System.out.println(Simple.class);
    }
}

class Simple2 {
    public Simple2() {
        System.out.println("Simple by Load " + Simple.class.getClassLoader());
        new MyCat();
        System.out.println(MyCat.class);
    }
}