package com.poplar.memory;

/**
 * Created BY poplar ON 2019/12/1
 * 测试jvm是否是使用引用计数法判断对象是否存活
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 */
public class ReferenceCountGC {

    private Object instance = null;

    private static final int _1MB = 1024 * 1024;

    //只是为了占内存以便查看Gc日志
    private byte[] bytes = new byte[2 * _1MB];

    public static void main(String[] args) {

        test();//6096K->744K说明虚拟机并没有因为这两个对象互相引用就不回收他们
        // [GC (System.gc()) [PSYoungGen: 6096K->744K(9216K)] 6096K->752K(19456K), 0.0074672 secs]
        // [Full GC (System.gc()) [PSYoungGen: 744K->0K(9216K)] [ParOldGen: 8K->630K(10240K)] 752K->6

    }

    private static void test() {
        ReferenceCountGC referenceCountGC1 = new ReferenceCountGC();
        ReferenceCountGC referenceCountGC2 = new ReferenceCountGC();
        referenceCountGC1.instance = referenceCountGC2;
        referenceCountGC2.instance = referenceCountGC1;

        referenceCountGC1 = null;
        referenceCountGC2 = null;

        System.gc();
    }
}
