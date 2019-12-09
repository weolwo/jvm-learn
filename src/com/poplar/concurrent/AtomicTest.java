package com.poplar.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created BY poplar ON 2019/12/7
 * Atomic变量自增测试
 */
public class AtomicTest {

    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
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
}
