package com.poplar.bytecode;

/**
 * Created By poplar on 2019/11/9
 * 从字节码分析得出的结论：
 * 成员变量的初始化是在构造方法中完成的，有多少个构造方法，初始化指令就会调用几次
 * 静态成员变量同样是在clinit方法完成的，不管有多少个静态变量都是在该方法完成初始化
 */
public class ByteCodeTest2 {

    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;

    public ByteCodeTest2(String str) {
        this.str = str;
    }

    public ByteCodeTest2(String str, int x) {
        this.str = str;
        this.x = x;
    }

    public ByteCodeTest2() {

    }

    public static void main(String[] args) {
        ByteCodeTest2 byteCodeTest2 = new ByteCodeTest2();
        byteCodeTest2.setX(8);
        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    public void test(String str) {
        synchronized (this) {//给当前对象上锁
            System.out.println("Hello World");
        }
    }

    //给类字节码码上锁
    public static synchronized void test() {
    }

    static {
        System.out.println();
    }
}
