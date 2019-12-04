package com.poplar.classload;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * Created By poplar on 2019/11/8
 * 各加载器的路径是可以修改的，修改后会导致运行失败，ClassNotFoundExeception
 */
public class ClassLoadTest19 {
    public static void main(String[] args) {
        //该类默认有扩展类加载器加载的,但是如果我们把该类默认的加载路劲修改后，就会报错
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader()); //ExtClassLoader@232204a1
    }
}
