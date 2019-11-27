package main.java.com.poplar.gc;

/**
 * Created BY poplar ON 2019/11/27
 * 垃圾回收测试
 */
public class GCTest1 {

    /*
    -verbose:gc 输出冗余的gc信息
    -Xms20M 堆初始化大最小容量
    -Xmx20M 堆初始化最大容量
    -Xmn10M 新生代容量
    -XX:+PrintGCDetails
    -XX:SurvivorRatio=8 配置新生代和survivor的大小比例为8：1：1
    */

    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes1 = new byte[2 * size];
        byte[] bytes2 = new byte[2 * size];
        byte[] bytes3 = new byte[3 * size];
        byte[] bytes4 = new byte[3 * size];
        //当需要分配内存的对象的大小超出了新生代的容量时，对象会被直接分配到老年代
        System.out.println("hello world");

        /*
         [GC (Allocation Failure)（表示发生GC的原因） [PSYoungGen（PS表示收集器类型）: 8144K（收集前）->728K（收集后）(9216K)
         （新生代总的容量）] 8144K（推收集前）->6872K（堆收集后）(19456K)（堆总的容量）, 0.0087417 secs（所用时间）] [Times: user（用户态收集所用时间）=0.02 sys=0.02系统态收集所用时间）, real=0.01 secs]
         [Full GC (Ergonomics) [PSYoungGen: 728K->0K(9216K)] [ParOldGen: 6144K->6774K(10240K)] 6872K->6774K(19456K), [Metaspace: 3217K->3217K(1056768K)], 0.0070323 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         hello world
         Heap
         PSYoungGen      total 9216K, used 2287K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         eden space 8192K, 27% used [0x00000000ff600000,0x00000000ff83be00,0x00000000ffe00000)
         from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
         to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
         ParOldGen       total 10240K, used 6774K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         object space 10240K, 66% used [0x00000000fec00000,0x00000000ff29daa8,0x00000000ff600000)
         Metaspace       used 3225K, capacity 4496K, committed 4864K, reserved 1056768K
         class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
         */
    }
}
