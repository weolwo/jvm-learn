package com.poplar.concurrent;

/**
 * Created BY poplar ON 2019/12/7
 * volatile在并发环境下并非原子操作
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    public static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            }).start();
        }

        //等待所有累加线程都结束,如果还有线程在运行，主线程就让出cpu资源
        while (Thread.activeCount() > 2) {//由于idea原因此处不能为一
            Thread.yield();
        }
        System.out.println(race);
    }

    /*
    public static void increase();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=0, args_size=0
         0: getstatic     #2                  // Field race:I
         3: iconst_1
         4: iadd
         5: putstatic     #2                  // Field race:I
         8: return
         当getstatic指令把race的值取到操作栈顶时，volatile关键字保证了race的值在此 时是正确的，但是在执行iconst_1、iadd这些指令的时候，
         其他线程可能已经把race的值加大 了，而在操作栈顶的值就变成了过期的数据，所以putstatic指令执行后就可能把较小的race值 同步回主内存之中
    */
}
