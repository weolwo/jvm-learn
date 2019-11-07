package com.poplar.classload;

import java.time.LocalDateTime;

/**
 * Created By poplar on 2019/11/7
 * <p>
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化
 * 只有在真正使用到父接口的时候（如引用接口中定义的常量），才会初始化
 * </p>
 */
public class ClassLoadTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild.b);
    }

}

interface Student5 {

    int a = 9; //前面省了public static final

    Thread thread = new Thread() {
        {
            System.out.println("thread 初始化了");//如果父接口初始化了这句应该输出
        }
    };
}

interface MyChild extends Student5 {     //接口属性默认是 public static final
    String b = LocalDateTime.now().toString();
}
