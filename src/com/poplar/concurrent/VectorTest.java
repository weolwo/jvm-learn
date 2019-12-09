package com.poplar.concurrent;

import java.util.Vector;

/**
 * Created BY poplar ON 2019/12/8
 * 对vector线程安全的测试,通过对源码debug测试发现会出现 ArrayIndexOutOfBoundsException
 * 尽管这里使用到的Vector的get（）、remove（）和size（）方法都是同步的， 但是在多线程的环境中，
 * 如果不在方法调用端做额外的同步措施的话，使用这段代码仍然是 不安全的，因为如果另一个线程恰好在错误的时间里删除了一个元素，
 * 导致序号i已经不再 可用的话，再用i访问数组就会抛出一个ArrayIndexOutOfBoundsException
 */
public class VectorTest {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            }).start();

            new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println(vector.get(i));
                }
            }).start();

            while (Thread.activeCount() > 90) ;
        }

    }
}
