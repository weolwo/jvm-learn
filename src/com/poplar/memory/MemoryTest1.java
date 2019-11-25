package com.poplar.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created BY poplar ON 2019/11/25
 * 关于Java对象创建的过程:
 * new关键字创建对象的3个步骤:
 * 1.在堆内存中创建出对象的实例。
 * 2.为对象的实例成员变量赋初值。
 * 3.将对象的引用返回
 * 指针碰撞(前提是堆中的空间通过一个指针进行分割，一侧是已经被占用的空间，另一侧是未被占用的空间)
 * 空闲列表(前提是堆内存空间中已被使用与未被使用的空间是交织在一起的，这时，虚拟机就需要通过一个列表来记录哪些空间是可以使用的，
 * 哪些空间是已被使用的，接下来找出可以容纳下新创建对象的且未被使用的空间，在此空间存放该对象，同时还要修改列表上的记录)
 * 对象在内存中的布局:
 * 1.对象头.
 * 2.实例数据(即我们在一个类中所声明的各项信息)
 * 3.对齐填充(可选) !
 * 引用访问对象的方式:
 * 1.使用句柄的方式。
 * 2.使用直接指针的方式。
 */
public class MemoryTest1 {
    public static void main(String[] args) {
        //-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError 设置jvm对空间最小和最大以及遇到错误时把堆存储文件打印出来
        //打开jvisualvm装在磁盘上的转存文件
        List<MemoryTest1> list = new ArrayList<>();
        while (true) {
            list.add(new MemoryTest1());
            System.gc();
        }
    }
}
