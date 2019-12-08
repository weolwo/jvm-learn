package com.poplar.bytecode;

/**
 * Created BY poplar ON 2019/12/4
 * 自动装箱与拆箱
 */
public class AutoBoxing {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 1L;
        //包装类的“==”运算再不遇到算数运算的晴空下不会自动拆箱。以及他们的equals方法不处理数据类型转型的关系
        //Integer值判断是否相等问题 要用equals判断不要用“==”判断
        System.out.println(c == d); //true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//false
        System.out.println(c.equals((a + b)));//true
        System.out.println(g == (a + b));//false
        System.out.println(g.equals((a + b)));//false
    }
}
