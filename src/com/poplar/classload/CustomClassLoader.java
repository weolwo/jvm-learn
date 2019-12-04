package com.poplar.classload;

import java.io.*;

/**
 * Created By poplar on 2019/11/7
 * 自定义类加载器
 */
public class CustomClassLoader extends ClassLoader {

    private String classLoaderName;

    private static final String filePost = ".class";

    public CustomClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader(String classLoaderName) {
        super();//将系统类加载器当作该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class findClass(String name) {
        System.out.println("findClass,输出这句话说明我们自己的类加载器加载了指定的类");
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            name = name.replace(".", File.separator);//File.separator根据操作系统而变化
            is = new FileInputStream(new File(name + filePost));
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            while (-1 != (len = is.read())) {
                byteArrayOutputStream.write(len);
            }
            data = byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.poplar.classload.ClassLoadTest");
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }

    public static void main(String[] args) throws Exception {
        CustomClassLoader classLoader = new CustomClassLoader("load1");
        test(classLoader);
    }
}
