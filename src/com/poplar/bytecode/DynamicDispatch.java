package com.poplar.bytecode;

/**
 * Created BY poplar ON 2019/12/4
 * 动态分派的演示与证明：
 * 在动态分派中虚拟机是如何知道要调用那个方法的？
 */
public class DynamicDispatch {

    static abstract class Human {
        public abstract void hello();
    }

    static class Man extends Human {
        @Override
        public void hello() {
            System.out.println("Hello Man");
        }
    }

    static class Woman extends Human {
        @Override
        public void hello() {
            System.out.println("Hello Woman");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woMan = new Woman();
        man.hello();
        woMan.hello();

        man = new Woman();
        man.hello();

    /*public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: new           #2                  // class main/java/com/poplar/bytecode/DynamicDispatch$Man
         3: dup
         4: invokespecial #3                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Man."<init>":()V
         7: astore_1
         8: new           #4                  // class main/java/com/poplar/bytecode/DynamicDispatch$Woman
        11: dup
        12: invokespecial #5                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Woman."<init>":()V
        15: astore_2
        16: aload_1 从局部变量加载一个引用 aload1是加载索引为1的引用（man），局部变量有三个（0：args; 1 :man ; 2 :woMan）
        17: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        20: aload_2 加载引用woMan
        21: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        24: new           #4                  // class main/java/com/poplar/bytecode/DynamicDispatch$Woman
        27: dup
        28: invokespecial #5                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Woman."<init>":()V
        31: astore_1
        32: aload_1
        33: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        36: return
      LineNumberTable:
        line 28: 0
        line 29: 8
        line 30: 16
        line 31: 20
        line 33: 24
        line 34: 32
        line 36: 36
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      37     0  args   [Ljava/lang/String;
            8      29     1   man   Lmain/java/com/poplar/bytecode/DynamicDispatch$Human;
           16      21     2 woMan   Lmain/java/com/poplar/bytecode/DynamicDispatch$Human;
    }
    invokevirtual 运行期执行的时候首先：
    找到操作数栈顶的第一个元素它所指向对象的实际类型，在这个类型里边，然后查找和常量里边Human的方法描述符和方法名称都一致的
    方法，如果在这个类型下，常量池里边找到了就会返回实际对象方法的直接引用。

    如果找不到，就会按照继承体系由下往上(Man–>Human–>Object)查找，查找匹配的方式就是
    上面描述的方式，一直找到位为止。如果一直找不到就会抛出异常。

    比较方法重载（overload）和方法重写（overwrite），我们可以得出这样的结论：
    方法重载是静态的，是编译器行为；方法重写是动态的，是运行期行为。
    ————————————————
    版权声明：本文为CSDN博主「魔鬼_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/wzq6578702/article/details/82712042
       */
    }
}
