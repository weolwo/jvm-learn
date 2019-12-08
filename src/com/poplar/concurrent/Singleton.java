package com.poplar.concurrent;

/**
 * Created BY poplar ON 2019/12/8
 * volatile静止指令重排序演示代码
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
/*
instance = new Singleton();
这段代码可以分为如下的三个步骤：
memory = allocate();  // 1：分配对象的内存空间
ctorInstance(memory); // 2：初始化对象
instance = memory;    // 3：设置instance指向刚分配的内存地址
我们知道，编辑器和处理器会进行代码优化，而其中重要的一点是会将指令进行重排序。
上边的代码经过重排序后可能会变为：
memory = allocate();  // 1：分配对象的内存空间
instance = memory;    // 3：设置instance指向刚分配的内存地址
					  // 注意：此时对象尚未初始化
ctorInstance(memory); // 2：初始化对象

代码对应的汇编的执行过程
* 0x01a3de0f：mov		$0x3375cdb0，%esi		;……beb0cd75 33
											;{oop（'Singleton'）}
0x01a3de14：mov		%eax，0x150（%esi）		;……89865001 0000
0x01a3de1a：shr		$0x9，%esi				;……c1ee09
0x01a3de1d：movb	$0x0，0x1104800（%esi）	;……c6860048 100100
0x01a3de24：lock	addl$0x0，（%esp）		;……f0830424 00
											;*put static instance
											;-
Singleton：getInstance@24

生成汇编码是lock addl $0x0, (%rsp), 在写操作（put static instance）之前使用了lock前缀，锁住了总线和对应的地址，这样其他的CPU写和读都要等待锁的释放。
当写完成后，释放锁，把缓存刷新到主内存。
加了 volatile之后，volatile在最后加了lock前缀，把前面的步骤锁住了，这样如果你前面的步骤没做完是无法执行最后一步刷新到内存的，
换句话说只要执行到最后一步lock，必定前面的操作都完成了。那么即使我们完成前面两步或者三步了，还没执行最后一步lock，或者前面一步执行了就切换线程2了，
线程B在判断的时候也会判断实例为空，进而继续进来由线程B完成后面的所有操作。当写完成后，释放锁，把缓存刷新到主内存。
————————————————
版权声明：本文为CSDN博主「夏洛克卷」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zx48822821/article/details/86589753

* */