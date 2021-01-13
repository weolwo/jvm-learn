package com.poplar.jmm;

/**
 * Create BY poplar ON 2021/1/13
 * MESI相关概念参考：https://www.cnblogs.com/z00377750/p/9180644.html
 * inter一个缓存行为 64bit
 */
public class MESICacheLinePaddingTest2 {

    private static final int COUNT = 1000_0000;

    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    //保证arr[0]和arr[1]在不同的缓存行中
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);//inter i5-6200U 8G 时间： 156 117 139
    }
}
