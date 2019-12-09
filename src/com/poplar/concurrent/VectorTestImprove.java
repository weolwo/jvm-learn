package com.poplar.concurrent;

import java.util.Vector;

/**
 * Created BY poplar ON 2019/12/8
 * 改进后debug源码未发现异常情况
 */
public class VectorTestImprove {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            }).start();

            new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            }).start();

            while (Thread.activeCount() > 90) ;
        }

    }
}
