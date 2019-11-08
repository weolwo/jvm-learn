package com.poplar.classload;

        import sun.misc.Launcher;

/**
 * Created By poplar on 2019/11/8
 * 在运行期，一个Java类是由该类的完全限定名（binary name）和用于加载该类的定义类加载器所共同决定的。
 * 如果同样名字（完全相同限定名）是由两个不同的加载器所加载，那么这些类就是不同的，即便.class文件字节码相同，并且从相同的位置加载亦如此。
 * 在oracle的hotspot，系统属性sun.boot.class.path如果修改错了，则运行会出错：
 * Error occurred during initialization of VM
 * java/lang/NoClassDeFoundError: java/lang/Object
 */
public class ClassLoadTest23 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));//根加载器路径
        System.out.println(System.getProperty("java.ext.dirs"));//扩展类加载器路径
        System.out.println(System.getProperty("java.class.path"));//应用类加载器路径

        System.out.println(ClassLoader.class.getClassLoader());
        //此处由于系统和扩展类加载器都是Launcher其内部静态类，但又都是非public的，
        // 所以不能直接获取他们的类加载器，方法就是通过获取他们的外部类加载器是谁？从而确当他们的类加载器。
        System.out.println(Launcher.class.getClassLoader());

        //下面的系统属性指定系统类加载器，默认是AppClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));
    }
}
