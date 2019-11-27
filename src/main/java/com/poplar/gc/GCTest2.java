package main.java.com.poplar.gc;

/**
 * Created BY poplar ON 2019/11/27
 * <p>
 * -verbose:gc 输出冗余的gc信息
 * -Xms20M 堆初始化大最小容量
 * -Xmx20M 堆初始化最大容量
 * -Xmn10M 新生代容量
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 配置新生代和survivor的大小比例为8：1：1
 * -XX:PretenureSizeThreshold=4194304 设置对象超过多大时直接分配到老年代
 * -XX:+UseSerialGC 表示指定垃圾收集器为SerialGC
 */
public class GCTest2 {

    public static void main(String[] args) {
        int size = 1024 * 1024;
        //GC发生在对象创建时，由于空间不足，JVM就会尝试执行垃圾回收，如果回收后空间还是不足，直接抛出异常OutOfMemoryError: Java heap space
        byte[] bytes1 = new byte[7 * size];
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world");
    }
}
