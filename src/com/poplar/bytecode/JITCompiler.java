package com.poplar.bytecode;

/**
 * Created BY poplar ON 2019/12/5
 * 测试即使编译
 * VM参数：-XX:+PrintCompilation  要求虚拟机在即时编译时将被编译成本地代码的 方法名称打印出来 其中带有“%”的输出说明是由回边计数器触发的 OSR编译
 */
public class JITCompiler {

    public static final int NUM = 15000;

    public static int doubleValue(int j) {
        for (int i = 0; i < 100000; i++) ;
        return j * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {

        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }

/*  260   31 %     3       com.poplar.bytecode.JITCompiler::doubleValue @ 2 (18 bytes)
            260   32       3       com.poplar.bytecode.JITCompiler::doubleValue (18 bytes)
    260   33 %     4       com.poplar.bytecode.JITCompiler::doubleValue @ 2 (18 bytes)
            261   31 %     3       com.poplar.bytecode.JITCompiler::doubleValue @ -2 (18 bytes)   made not entrant
    261   34       4       com.poplar.bytecode.JITCompiler::doubleValue (18 bytes)
    262   32       3       com.poplar.bytecode.JITCompiler::doubleValue (18 bytes)   made not entrant
    262   35       3       com.poplar.bytecode.JITCompiler::calcSum (26 bytes)
    263   36 %     4       com.poplar.bytecode.JITCompiler::calcSum @ 4 (26 bytes)
            265   37       4       com.poplar.bytecode.JITCompiler::calcSum (26 bytes)
    267   35       3       com.poplar.bytecode.JITCompiler::calcSum (26 bytes)   made not entrant*/

}
