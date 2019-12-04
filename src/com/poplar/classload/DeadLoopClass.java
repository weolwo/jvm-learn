package com.poplar.classload;

/**
 * Created BY poplar ON 2019/12/3
 * JVM会保证一个类的<init>方法在多线程的情况下被正确的枷锁，同步
 */
public class DeadLoopClass {
    static {
        if (true) {//此处如果不加if编译器会提示 initializer dose not complete normallyr
            System.out.println(Thread.currentThread() + " init DeadLoopClass");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        String string= new String();
        new Thread(DeadLoopClass::test).start();

        new Thread(DeadLoopClass::test).start();
    }

    private static void test() {
        System.out.println(Thread.currentThread() + "start");
        DeadLoopClass deadLoopClass = new DeadLoopClass();
        System.out.println(Thread.currentThread() + "run over");
    }
}
