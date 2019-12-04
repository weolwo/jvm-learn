package com.poplar.bytecode;

/**
 * Created BY poplar ON 2019/12/4
 * 基于栈的解释器的执行过程概念模型
 */
public class BasicStackExecutionProcess {

    public int calc() {
        int a = 100;
        int b = 200;
        int c = 300;
        return (a + b) * c;

    /*
   public int calc();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=1
         0: bipush        100  执行地址偏移量为0 将100推送至栈顶
         2: istore_1          执行地址偏移量为2  将栈顶的100出栈并存放到第一个局部变量Slot中
         3: sipush        200
         6: istore_2
         7: sipush        300
        10: istore_3
        11: iload_1          执行地址偏移量为11 将局部变量中第一个Slot中的整型值复制到栈顶
        12: iload_2
        13: iadd            将栈顶的两个元素出栈并作整形加法，然后把结果重新入栈
        14: iload_3
        15: imul            将栈顶的两个元素出栈并作整形乘法，然后把结果重新入栈
        16: ireturn         结束方法并将栈顶的值返回给方法调用者
      LineNumberTable:
        line 10: 0
        line 11: 3
        line 12: 7
        line 13: 11
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      17     0  this   Lcom/poplar/bytecode/BasicStackExecutionProcess;
            3      14     1     a   I
            7      10     2     b   I
           11       6     3     c   I
     */
    }

    public static void main(String[] args) {
        BasicStackExecutionProcess process = new BasicStackExecutionProcess();
        int res = process.calc();
        System.out.println(res);
    }
}
