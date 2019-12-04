package com.poplar.memory;

import java.util.Map;

/**
 * Created BY poplar ON 2019/12/2
 * 下面的代码相当于jstack命令的大部分功能
 * jstack用于生成虚拟机当前时刻的快照
 */
public class StackInfoTest {
    public static void main(String[] args) {
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = stackTrace.getKey();
            StackTraceElement[] traceElements = stackTrace.getValue();
            if (Thread.currentThread().equals(thread)) {
                continue;
            }
            System.out.println("线程名： " + thread.getName());
            for (StackTraceElement element : traceElements) {
                //Each element represents a single stack frame
                System.out.println("\t" + element + "\n");
            }
        }
    }
}
