package com.v2.bytecode;

import com.v2.bean.Eliza;
import org.openjdk.jol.info.ClassLayout;

/**
 * BY Alex CREATED 2021/9/3
 * 类和类对象，对象相关
 *
 * <p>
 * 在HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：对象头（Header）、 实例数据（Instance Data）
 * 和对齐填充（Padding）。 HotSpot虚拟机的对象头包括两部分信息，第一部分用于存储对象自身的运行时数据， 如哈
 * 希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时 间戳等。对象头的另外一部分
 * 是类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。
 * </p>
 * <p>
 * 对象头在hotspot的C++源码markOop.hpp文件里的注释如下：
 * <br/>
 * 32 bits:
 * ‐‐‐‐‐‐‐‐
 * hash:25 ‐‐‐‐‐‐‐‐‐‐‐‐>| age:4 biased_lock:1 lock:2 (normal object)
 * JavaThread*:23 epoch:2 age:4 biased_lock:1 lock:2 (biased object)
 * size:32 ‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐>| (CMS free block)
 * PromotedObject*:29 ‐‐‐‐‐‐‐‐‐‐>| promo_bits:3 ‐‐‐‐‐>| (CMS promoted object)
 * <p>
 * 64 bits:
 * ‐‐‐‐‐‐‐‐
 * unused:25 hash:31 ‐‐>| unused:1 age:4 biased_lock:1 lock:2 (normal object)
 * JavaThread*:54 epoch:2 unused:1 age:4 biased_lock:1 lock:2 (biased object)
 * PromotedObject*:61 ‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐>| promo_bits:3 ‐‐‐‐‐>| (CMS promoted object)
 * size:64 ‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐>| (CMS free block)
 * <p>
 * unused:25 hash:31 ‐‐>| cms_free:1 age:4 biased_lock:1 lock:2 (COOPs && normal object)
 * JavaThread*:54 epoch:2 cms_free:1 age:4 biased_lock:1 lock:2 (COOPs && biased object)
 * narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ‐‐‐‐‐>| (COOPs && CMS promoted object)
 * unused:21 size:35 ‐‐>| cms_free:1 unused:7 ‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐‐>| (COOPs && CMS free block)
 */


public class ObjectMeta {

    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new Eliza());
        System.out.println(layout2.toPrintable());
    }
}
