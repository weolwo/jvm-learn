package com.poplar.classload;

/**
 * Created By poplar on 2019/11/9
 */
public class ClassLoadTest25 implements Runnable {

    private Thread thread;

    public ClassLoadTest25() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = thread.getContextClassLoader();
        thread.setContextClassLoader(classLoader);
        System.out.println("Class: " + classLoader.getClass()); //Class: class sun.misc.Launcher$AppClassLoader
        System.out.println("Parent " + classLoader.getParent()); // Parent sun.misc.Launcher$ExtClassLoader@5b74b597
    }

    public static void main(String[] args) {
        new ClassLoadTest25();
    }
}
//源码：
   /* public Launcher() {
        Launcher.ExtClassLoader var1;
        try {
            var1 = Launcher.ExtClassLoader.getExtClassLoader();
        } catch (IOException var10) {
            throw new InternalError("Could not create extension class loader", var10);
        }

        try {
            //获取到系统类加载器
            this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
        } catch (IOException var9) {
            throw new InternalError("Could not create application class loader", var9);
        }
        //把系统类加载器设置到当前线程的上下文类加载器中
        Thread.currentThread().setContextClassLoader(this.loader);
    }*/