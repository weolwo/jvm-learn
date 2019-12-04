package com.poplar.gc;

/**
 * Created BY poplar ON 2019/11/30
 * G1日志分析
 * 虚拟机相关参数：
 * -verbose:gc
 * -Xms10m
 * -Xmx10m
 * -XX:+UseG1GC 表示垃圾收集器使用G1
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:MaxGCPauseMillis=200m 设置垃圾收集最大停顿时间
 */
public class G1LogAnalysis {

    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes1 = new byte[size];
        byte[] bytes2 = new byte[size];
        byte[] bytes3 = new byte[size];
        byte[] bytes4 = new byte[size];
        System.out.println("hello world");
    }
}
/**
 * GC日志：
 * 2019-11-30T16:13:41.663+0800: [GC pause (G1 Humongous Allocation【说明分配的对象超过了region大小的50%】) (young) (initial-mark), 0.0014516 secs]
 * [Parallel Time: 1.1 ms, GC Workers: 4【GC工作线程数】]
 * [GC Worker Start (ms): Min: 167.0, Avg: 167.1, Max: 167.1, Diff: 0.1]【几个垃圾收集工作的相关信息统计】
 * [Ext Root Scanning (ms): Min: 0.4, Avg: 0.4, Max: 0.4, Diff: 0.1, Sum: 1.6]
 * [Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 * [Processed Buffers: Min: 0, Avg: 0.0, Max: 0, Diff: 0, Sum: 0]
 * [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 * [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 * [Object Copy (ms): Min: 0.6, Avg: 0.6, Max: 0.6, Diff: 0.0, Sum: 2.4]
 * [Termination (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 * [Termination Attempts: Min: 1, Avg: 1.3, Max: 2, Diff: 1, Sum: 5]
 * 【上面的几个步骤为YOUNG GC的固定执行步骤】
 * 阶段1:根扫描
 * 静态和本地对象被描
 * 阶段2:更新RS
 * 处理dirty card队列更新RS
 * 阶段3:处理RS
 * 检测从年轻代指向老年代的对象
 * 阶段4:对象拷贝
 * 拷贝存活的对象到survivor/old区域
 * 阶段5:处理引用队列
 * 软引用，弱引用，虚引用处理
 * [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.2]
 * [GC Worker Total (ms): Min: 1.0, Avg: 1.1, Max: 1.1, Diff: 0.1, Sum: 4.2]
 * [GC Worker End (ms): Min: 168.1, Avg: 168.1, Max: 168.1, Diff: 0.0]
 * [Code Root Fixup: 0.0 ms]
 * [Code Root Purge: 0.0 ms]
 * [Clear CT: 0.1 ms]【清楚cardTable所花费时间】
 * [Other: 0.3 ms]
 * [Choose CSet: 0.0 ms]
 * [Ref Proc: 0.1 ms]
 * [Ref Enq: 0.0 ms]
 * [Redirty Cards: 0.1 ms]
 * [Humongous Register: 0.0 ms]
 * [Humongous Reclaim: 0.0 ms]
 * [Free CSet: 0.0 ms]
 * [Eden: 2048.0K(4096.0K)->0.0B【新生代清理后】(2048.0K) Survivors: 0.0B->1024.0K Heap: 3800.2K(10.0M)->2752.1K(10.0M)]
 * [Times: user=0.00 sys=0.00, real=0.01 secs]
 * 2019-11-30T16:13:41.671+0800: [GC concurrent-root-region-scan-start]
 * 2019-11-30T16:13:41.671+0800: [GC concurrent-root-region-scan-end, 0.0008592 secs]
 * 2019-11-30T16:13:41.671+0800: [GC concurrent-mark-start]
 * 2019-11-30T16:13:41.672+0800: [GC concurrent-mark-end, 0.0000795 secs]
 * 2019-11-30T16:13:41.672+0800: [GC remark 2019-11-30T16:13:41.672+0800: [Finalize Marking, 0.0001170 secs] 2019-11-30T16:13:41.672+0800: [GC ref-proc, 0.0002159 secs] 2019-11-30T16:13:41.672+0800: [Unloading, 0.0005800 secs], 0.0011024 secs]
 * [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 2019-11-30T16:13:41.673+0800: [GC cleanup 4800K->4800K(10M), 0.0003239 secs]
 * [Times: user=0.00 sys=0.00, real=0.00 secs]
 * hello world
 * Heap
 * garbage-first heap   total 10240K, used 4800K [0x00000000ff600000, 0x00000000ff700050, 0x0000000100000000)
 * region size 1024K【说明region默认大小】, 2 young (2048K), 1 survivors (1024K)
 * Metaspace       used 3224K, capacity 4496K, committed 4864K, reserved 1056768K
 * class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
 */