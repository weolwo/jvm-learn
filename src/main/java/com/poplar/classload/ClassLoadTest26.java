package com.poplar.classload;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created By poplar on 2019/11/9
 * <p>
 * 线程上下文类加载器的一般使用模式：（获取-使用-还原）
 * 伪代码：
 * ClassLoader classLoader=Thread.currentThread().getContextLoader();
 * try{
 * Thread.currentThread().setContextLoader(targetTccl);
 * myMethod();
 * }finally{
 * Thread.currentThread().setContextLoader(classLoader);
 * }
 * 在myMethod中调用Thread.currentThread().getContextLoader()做某些事情
 * ContextClassLoader的目的就是为了破坏类加载委托机制
 * <p>
 * 在SPI接口的代码中，使用线程上下文类加载器就可以成功的加载到SPI的实现类。
 * <p>
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（或实例化）底层的类时，
 * 就必须通过上下文类加载器来帮助高层的ClassLoader找到并加载该类。
 */
public class ClassLoadTest26 {
    public static void main(String[] args) {

        //一旦加入下面此行，将使用ExtClassLoader去加载Driver.class， ExtClassLoader不会去加载classpath，因此无法找到MySql的相关驱动。
        //Thread.getCurrentThread().setContextClassLoader(MyTest26.class.getClassLoader().parent());

        //ServiceLoader服务提供者，加载实现的服务
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader" + driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文加载器" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器" + ServiceLoader.class.getClassLoader());
    }
}

