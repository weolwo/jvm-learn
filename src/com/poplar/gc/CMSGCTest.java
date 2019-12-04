package com.poplar.gc;

/**
 * Created BY poplar ON 2019/11/28
 */
public class CMSGCTest {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes1 = new byte[4 * size];
        System.out.println("111111");

        byte[] bytes2 = new byte[4 * size];
        System.out.println("2222222");

        byte[] bytes3 = new byte[4 * size];
        System.out.println("33333333");

        byte[] bytes4 = new byte[4 * size];
        System.out.println("4444444");
    }
}
