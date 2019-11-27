package main.java.com.poplar.memory;

/**
 * Created BY poplar ON 2019/11/26
 * jmam命令的使用 -clstats<pid>进程id  to print class loader statistics
 * jmap -clstats 3740
 * <p>
 * jstat -gc 3740
 * S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 * 512.0  512.0   0.0    0.0   24064.0   9626.0   86016.0     1004.1   4864.0 3758.2 512.0  409.1     144    0.064   0      0.000    0.064
 * MC元空间总大小，MU元空间已使用的大小
 */
public class MemoryTest4 {
    public static void main(String[] args) {
        while (true)
            System.out.println("hello world");
    }
    //查看java进程id jps -l
    // 使用jcmd查看当前进程的可用参数：jcmd 10368 help
    //查看jvm的启动参数 jcmd 10368 VM.flags
    // 10368:-XX:CICompilerCount=3 -XX:InitialHeapSize=132120576 -XX:MaxHeapSize=2111832064 -XX:MaxNewSize=703594496
    // -XX:MinHeapDeltaBytes=524288 -XX:NewSize=44040192 -XX:OldSize=88080384 -XX:+UseCompressedClassPointers
    // -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC

    public void method() {
        Object object = new Object();

        /*生成了2部分的内存区域，1)object这个引用变量，因为
        是方法内的变量，放到JVM Stack里面,2)真正Object
        class的实例对象，放到Heap里面
        上述 的new语句一共消耗12个bytes, JVM规定引用占4
        个bytes (在JVM Stack)， 而空对象是8个bytes(在Heap)
        方法结束后，对应Stack中的变量马上回收，但是Heap
        中的对象要等到GC来回收、*/
    }
}
