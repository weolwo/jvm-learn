package com.poplar.bytecode;

/**
 * Created By poplar on 2019/11/10
 * 方法的动态分派
 * 方法的动态分派涉及到一个重要概念:方法接收者。
 * invokevirtua1字节码指令的多态查找流程
 * 比较方法重载(overload)与方法重写(overwrite) ,我们可以得到这样一个结论:
 * 方法重载是静态的,是编译期行为;
 * 方法重写是动态的,是运行期行为。
 */
public class ByteCodeTest5 {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        apple.test();//<com/poplar/bytecode/Fruit.test>将符号引用转换为直接引用

        Fruit orange = new Orange();
        orange.test();
    }
}

class Fruit {

    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {

    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Orange");
    }
}