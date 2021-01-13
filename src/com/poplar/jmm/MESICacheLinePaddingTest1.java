package com.poplar.jmm;

/**
 * Create BY poplar ON 2021/1/13
 * MESI相关概念参考：https://www.cnblogs.com/z00377750/p/9180644.html
 * inter一个缓存行为 64bit
 */
public class MESICacheLinePaddingTest1 {

    private static final int COUNT = 1000_0000;

    private static class T {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    //arr[0]和arr[1]在同一个缓存行中概率非常大
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        test();
    }

    //使用两个线程去分别修改arr[0]和arr[1]中的值
    private static void test() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);//inter i5-6200U 8G 时间： 292 279 249
    }
}
