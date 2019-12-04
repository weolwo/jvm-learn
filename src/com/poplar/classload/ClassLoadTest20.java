package com.poplar.classload;

import java.lang.reflect.Method;

/**
 * Created By poplar on 2019/11/8
 */
public class ClassLoadTest20 {
    public static void main(String[] args) throws Exception {
        CustomClassLoader2 loader1 = new CustomClassLoader2("load1");
        CustomClassLoader2 loader2 = new CustomClassLoader2("load2");
        Class<?> clazz1 = loader1.loadClass("com.poplar.classload.Person");
        Class<?> clazz2 = loader2.loadClass("com.poplar.classload.Person");

        //clazz1和clazz均由应用类加载器加载的，第二次不会重新加载，结果为true
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setPerson", Object.class);
        method.invoke(object1, object2);
    }
}

class Person {

    private Person person;

    public void setPerson(Object object) {
        this.person = (Person) object;
    }
}