package com.poplar.classload;

import java.lang.reflect.Method;

/**
 * Created By poplar on 2019/11/8
 * 1.每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类构成；
 * 2.在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类；
 * 3.在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类；
 * 4.同一命名空间内的类是互相可见的，非同一命名空间内的类是不可见的；
 * 5.子加载器可以见到父加载器加载的类，父加载器也不能见到子加载器加载的类。
 */
public class ClassLoadTest21 {

    public static void main(String[] args) throws Exception {
        CustomClassLoader2 loader1 = new CustomClassLoader2();
        CustomClassLoader2 loader2 = new CustomClassLoader2();
        Class<?> clazz1 = loader1.loadClass("com.poplar.bean.User");
        Class<?> clazz2 = loader2.loadClass("com.poplar.bean.ClassLoaderTest");
        //由于clazz1和clazz2分别有不同的类加载器所加载，所以他们处于不同的名称空间里
        System.out.println(clazz1 == clazz2);//false

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();
        System.out.println(object1.getClass().getClassLoader());
        System.out.println(object2.getClass().getClassLoader());
        //Method method = clazz1.getMethod("setMyPerson", Object.class);
        //此处报错，loader1和loader2所处不用的命名空间
        //method.invoke(object1, object2);

       /*
       输出结果：
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        false
        Exception in thread "main" java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.poplar.classload.ClassLoadTest21.main(ClassLoadTest21.java:25)
        Caused by: java.lang.ClassCastException: com.poplar.classload.MyPerson cannot be cast to com.poplar.classload.MyPerson*/
    }
}
