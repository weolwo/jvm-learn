package com.v2.classload;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * BY Alex CREATED 2021/9/1
 * <p>
 * 打破双亲委派机制
 * <br/>
 * 自定义类加载器只需要继承 java.lang.ClassLoader 类，该类有两个核心方法，一个是
 * loadClass(String, boolean)，实现了双亲委派机制，还有一个方法是findClass，默认实现是空
 * 方法，所以我们自定义类加载器主要是重写findClass方法。而要打破双亲模型则需要重写 loadClass
 * <p>
 * <p>
 * 为什么要设计双亲委派机制？
 * <br/>
 * 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心
 * API库被随意篡改，例如：即使把 Object类拷贝出来，用自己写的类加载器加载，也是无法加载到
 * 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一
 * 次，保证被加载类的唯一性
 * <p>
 * 注意：同一个JVM内，两个相同包名和类名的类对象可以共存，因为他们的类加载器可以不一
 * 样，所以看两个类对象是否是同一个，除了看类的包名和类名是否都相同之外，还需要他们的类
 * 加载器也是同一个才能认为他们是同一个。
 */


public class CustomizeClassLoader extends ClassLoader {

    private String classPath;

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(getClassPath() + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    public static void main(String[] args) throws Exception {
        CustomizeClassLoader classLoader1 = new CustomizeClassLoader();
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载
        //器设置为应用程序类加载器AppClassLoader
        classLoader1.setClassPath("E:/test");

        Class clazz = classLoader1.loadClass("com.v2.bean.Amos");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("out", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass());

        //模拟tomcat 打破双亲模型和解决不同项目间存在的版本问题，对于每一个 项目都会生成一个对于的类加载器
        CustomizeClassLoader classLoader2 = new CustomizeClassLoader();
        classLoader2.setClassPath("E:/test2");
        clazz = classLoader2.loadClass("com.v2.bean.Amos");
        obj = clazz.newInstance();
        method = clazz.getDeclaredMethod("out", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass());

        /*String v1
        class com.v2.classload.CustomizeClassLoader
        String v2
        class com.v2.classload.CustomizeClassLoader*/
    }

    //重写 loadClass
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                //非自定义的类，还是走以前的双亲模型
                if (!name.contains("com.v2")) {
                    c = this.getParent().loadClass(name);
                } else {
                    c = findClass(name);
                }
                long t1 = System.nanoTime();

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
