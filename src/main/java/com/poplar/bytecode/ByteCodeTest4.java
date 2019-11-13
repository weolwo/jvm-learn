package com.poplar.bytecode;

/**
 * Created By poplar on 2019/11/10
 * 静态解析的四种场：静态方法、父类方法、构造方法、私有方法。
 * 以上四种方法称为非虚方法，在类加载阶段将符号引用转换为直接引用。
 */

/**
 * 方法的静态分派。
 * Grandpa g1 = new Father();
 * 以上代码, g1的静态类型是Grandpa,而g1的实际类型(真正指向的类型)是Father.
 * 我们可以得出这样一个结论:变量的静态类型是不会发生变化的,而变量的实际类型则是可以发生变化的(多态的一种体现)
 * 实际变量是在运行期方可确定
 */
public class ByteCodeTest4 {

    public void test(Grandpa grandpa) {
        System.out.println("Grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("Son");
    }

    public static void main(String[] args) {
        ByteCodeTest4 byteCodeTest4 = new ByteCodeTest4();
        //方法重载,是一种静态的行为,编译期就可以完全确定
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();
        byteCodeTest4.test(g1);//Grandpa
        byteCodeTest4.test(g2);//Grandpa
    }
}

class Grandpa {

}

class Father extends Grandpa {

}

class Son extends Father {

}