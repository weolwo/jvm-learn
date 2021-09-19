package com.v2.memory;

import com.v2.bean.Eliza;

/**
 * BY Alex CREATED 2021/9/3
 * 栈上分配内存测试<br/>
 *
 * <p>
 * 对象栈上分配
 * 我们通过JVM内存分配可以知道JAVA中的对象都是在堆上进行分配，当对象没有被引用的时候，需要依靠GC进行回收内存，如果对象数量较多的时候，
 * 会给GC带来较大压力，也间接影响了应用的性能。为了减少临时对象在堆内分配的数量，JVM通过逃逸分析确定该对象不会被外部访问。
 * 如果不会逃逸可以将该对象在栈上分配内存，这样该对象所占用的内存空间就可以随栈帧出栈而销毁，就减轻了垃圾回收的压力。
 * 对象逃逸分析：就是分析对象动态作用域，当一个对象在方法中被定义后，它可能被外部方法所引用，例如作为调用参数传递到其他地方中。
 * <br/>
 */


public class AllotOnStack {

    /**
     * 栈上分配，标量替换
     * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
     * <p>
     * 使用如下参数不会发生GC
     * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     * 使用如下参数都会发生大量GC
     * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //测试发现jdk1.8似乎是默认开启的
    }

    private static void alloc() {
        Eliza eliza = new Eliza();
        eliza.setId(1);
        eliza.setHobby(new String[]{});
        eliza.setName("el");
        eliza.setSex((byte) 1);
    }
}
