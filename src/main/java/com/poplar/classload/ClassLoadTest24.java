package com.poplar.classload;

/**
 * Created By poplar on 2019/11/9
 */
public class ClassLoadTest24 {

    /**
     * 当前类加载器(Current ClassLoader)
     * 每个类都会尝试使用自己的类加载器去加载依赖的类。
     * <p>
     * 线程上下文类加载器(Context ClassLoader)
     * 线程上下文加载器 @ jdk1.2
     * 线程类中的 getContextClassLoader() 与 setContextClassLoader(ClassLoader c)
     * 如果没有通过setContextClassLoader()方法设置，线程将继承父线程的上下文类加载器，
     * java应用运行时的初始线程的上下文类加载器是系统类加载器。该线程中运行的代码可以通过该类加载器加载类和资源。
     * <p>
     * 线程上下文类加载器的作用：
     * SPI：Service Provide Interface
     * 父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所制定的ClassLoader加载的类，
     * 这就改变了父加载器加载的类无法使用子加载器或是其他没有父子关系的ClassLoader加载的类的情况，即改变了双亲委托模型。
     * <p>
     * 在双亲委托模型下，类加载是由下至上的，即下层的类加载器会委托父加载器进行加载。但是对于SPI来说，
     * 有些接口是Java核心库所提供的的（如JDBC），Java核心库是由启动类记载器去加载的，而这些接口的实现却来自不同的jar包（厂商提供），
     * Java的启动类加载器是不会加载其他来源的jar包，这样传统的双亲委托模型就无法满足SPI的要求。通过给当前线程设置上下文类加载器，
     * 就可以由设置的上下文类加载器来实现对于接口实现类的加载。
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
        System.out.println(System.getProperty("java.system.class.loader"));
    }
}
