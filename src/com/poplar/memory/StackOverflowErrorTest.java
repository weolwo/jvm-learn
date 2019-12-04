package com.poplar.memory;

/**
 * Created BY poplar ON 2019/11/25
 * 虚拟机栈溢出测试
 * -Xss100k设置栈大小
 */
public class StackOverflowErrorTest {

    private int length;

    public int getLength() {
        return length;
    }

    public void test() throws InterruptedException {
        length++;
        Thread.sleep(1);
        test();
    }

    public static void main(String[] args) {
        //测试调整虚拟机栈内存大小为：  -Xss160k，此处除了可以使用JVisuale监控程序运行状况外还可以使用jconsole
        StackOverflowErrorTest stackOverflowErrorTest = new StackOverflowErrorTest();
        try {
            stackOverflowErrorTest.test();
        } catch (Throwable e) {
            System.out.println(stackOverflowErrorTest.getLength());//打印最终的最大栈深度为：2587
            e.printStackTrace();
        }
    }
}
