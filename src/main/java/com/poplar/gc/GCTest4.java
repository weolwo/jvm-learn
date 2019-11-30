package main.java.com.poplar.gc;

/**
 * Created BY poplar ON 2019/11/28
 * -verbose:gc
 * -Xmx200M
 * -Xmn50M
 * -XX:TargetSurvivorRatio=60 表明所有age的survivor space对象的大小如果超过Desired survivor size，则重新计算threshold
 * -XX:+PrintTenuringDistribution 打印对象年龄
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps 打印收集时间
 * -XX:+UseConcMarkSweepGC 老年代使用cms收集器
 * -XX:+UseParNewGC 新生代使用ParNew收集器
 * -XX:MaxTenuringThreshold=3 设置晋升到老年代的阈值
 */
public class GCTest4 {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytes1 = new byte[1024 * 1024];
        byte[] bytes2 = new byte[1024 * 1024];

        method();
        Thread.sleep(1000);
        System.out.println("11111111");

        method();
        Thread.sleep(1000);
        System.out.println("222222222");

        method();
        Thread.sleep(1000);
        System.out.println("3333333333");

        method();
        Thread.sleep(1000);
        System.out.println("4444444444");

        byte[] bytes3 = new byte[1024 * 1024];
        byte[] bytes4 = new byte[1024 * 1024];
        byte[] bytes5 = new byte[1024 * 1024];

        method();
        Thread.sleep(1000);
        System.out.println("5555555");

        method();
        Thread.sleep(1000);
        System.out.println("666666");
    }

    public static void method() {
        for (int i = 0; i < 40; i++) {
            byte[] bytes = new byte[1024 * 1024];
        }
    }
}
