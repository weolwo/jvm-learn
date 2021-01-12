package com.poplar.compiler;

/**
 * Create BY poplar ON 2021/1/12
 * JVM的执行模式默认为混合模式
 *<blockquote><pre>
 * C:\Users\poplar>java -version
 * java version "1.8.0_201"
 * Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)
 * </pre></blockquote>
 * -Xint 禁止编译执行
 * -Xcomp 强制虚拟机运行于“编译模式”
 */
public class JItAndInterpreterTest {

    //inter i5-6200U,内存：8G
    //混合用时：4125 4309 4778
    //解释执行：过了几分钟都没完成
    //编译执行：4163 4030 3969

    public static void main(String[] args) {
        for (int i = 0; i < 10_0000; i++) {
            func();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_0000; i++) {
            func();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    public static void func() {
        for (long i = 0; i < 10_0000L; i++) {
            long j = i % 3;
        }
    }
}
