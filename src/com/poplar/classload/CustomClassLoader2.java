package com.poplar.classload;

import java.io.*;

/**
 * Created By poplar on 2019/11/7
 * 自定义类加载器
 */
public class CustomClassLoader2 extends ClassLoader {

    private static final String filePost = ".class";


    public static void main(String[] args) throws Exception {
        //类的卸载
        CustomClassLoader2 Loader2 = new CustomClassLoader2();
        test1(Loader2);
        System.gc();
        //Thread.sleep(10000); //jvisualvm 查看当前java进程 -XX:+TraceClassUnloading这个用于追踪类卸载的信息
        CustomClassLoader2 Loader3 = new CustomClassLoader2();
        test1(Loader3);
        /*
        执行结果：
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        com.poplar.classload.CustomClassLoader2@15db9742
        2018699554
        -------------------------------------
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        com.poplar.classload.CustomClassLoader2@4e25154f
        1550089733*/
    }

    private static void test1(CustomClassLoader2 loader2) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = loader2.loadClass("com.poplar.bean.User");
        Object instance = clazz.newInstance();
        System.out.println(instance.getClass().getClassLoader());
        System.out.println(instance.hashCode());
        System.out.println("-------------------------------------");
        //运行结果：（此处测试建议把源码文件先删掉，不然idea会重新生成classes,还是会导致系统类加载器加载）
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass,输出这句话说明我们自己的类加载器加载了指定的类");
        byte[] b = loadClassData(name);
        if (b == null) {
            //直接抛 ClassNotFoundException
            return super.findClass(name);
        }
        return defineClass(name, b, 0, b.length);
    }

    /**
     * 获取到指定路径为类文件的二进制字节数组
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {
            //File.separator根据操作系统而变化
            File file = new File("E:\\logs\\", name.replace(".", File.separator).concat(filePost));
            is = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            int len = 0;
            while (-1 != (len = is.read())) {
                baos.write(len);
            }
            return baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
