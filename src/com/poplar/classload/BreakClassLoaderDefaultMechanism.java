package com.poplar.classload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Create BY poplar ON 2021/1/13
 * 主要为了测试打破默认的双亲委派机制，有些时候这是很重要的
 * 可参考：https://www.cnblogs.com/june0816/p/10090428.html
 */
public class BreakClassLoaderDefaultMechanism extends ClassLoader {

    //由于双亲委派机制是在ClassLoader的这个方法中使用模板方法模式写死的，所以如果要打破默认的类加载机制，就必须重写这个方法
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        File file = new File("E:\\logs\\", name.replace(".", File.separator).concat(".class"));

        if (!file.exists()) {
            return super.loadClass(name);
        }

        try {

            InputStream is = new FileInputStream(file);

            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.loadClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //我加载E:\logs\目录下的同一个文件
        BreakClassLoaderDefaultMechanism loader = new BreakClassLoaderDefaultMechanism();
        Class<?> clazz = loader.loadClass("com.poplar.bean.User");

        loader = new BreakClassLoaderDefaultMechanism();
        Class<?> clazzNew = loader.loadClass("com.poplar.bean.User");

        System.out.println(clazz == clazzNew);//false
        System.out.println(clazz.getClassLoader());
        System.out.println(clazzNew.getClassLoader());
    }

}
