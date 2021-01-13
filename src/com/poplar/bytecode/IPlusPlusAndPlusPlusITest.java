package com.poplar.bytecode;

/**
 * Create BY poplar ON 2021/1/13
 * 通过字节码理解 i++ 和 ++i 此处使用的是 jclasslib bytecode view插件
 */
public class IPlusPlusAndPlusPlusITest {

    public static void main(String[] args) {
        int i = 1;
        //i = i++;
        i = ++i;
        System.out.println(i);
    }
   // i++字节码：
/*   0 iconst_1         把1压栈
     1 istore_1         把1从栈中弹出来放到局部变量表索引为1的变量上
     2 iload_1          把局部变量表索引为1的值压入栈中
     3 iinc 1 by 1      把局部变量表索引为1的值+1
     6 istore_1
     7 getstatic #2 <java/lang/System.out>
    10 iload_1
    11 invokevirtual #3 <java/io/PrintStream.println>
    14 return*/

    // ++i的字节码
/*
     0 iconst_1
     1 istore_1
     2 iinc 1 by 1
     5 iload_1
     6 istore_1
     7 getstatic #2 <java/lang/System.out>
    10 iload_1
    11 invokevirtual #3 <java/io/PrintStream.println>
    14 return*/
}
