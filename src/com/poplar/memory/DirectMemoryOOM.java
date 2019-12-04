package com.poplar.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created BY poplar ON 2019/12/1
 * 本机直接内存溢出测试
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        System.out.println(field);
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        //并没有真正调用操作系统申请分配内存，只是通过计算得知内存无法分配，于是手动抛出异常
        while (true) {
            unsafe.allocateMemory(_1MB);//Exception in thread "main" java.lang.OutOfMemoryError
        }
    }
}
