package com.poplar.classload;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created By poplar on 2019/11/9
 */
public class ClassLoadTest27 {
    //源码：
    /* private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader) {
            boolean result = false;
            if(driver != null) {
                Class<?> aClass = null;
                try {
                    //到这儿时其实已经加载过了，再次加载主要是名称空间的问题，确保是在同一名称空间下
                    aClass =  Class.forName(driver.getClass().getName(), true, classLoader);
                } catch (Exception ex) {
                    result = false;
                }
                result = ( aClass == driver.getClass() ) ? true : false;
            }

            return result;
        }*/
    public static void main(String[] args) throws Exception {
        //Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "123456");

       /* jar hell问题以及解决办法
        当一个类或者一个资源文件存在多个jar中，就会存在jar hell问题。
        可通过以下代码解决问题：*/
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resource = "java/lang/String.class";
        Enumeration<URL> urls = classLoader.getResources(resource);
        while (urls.hasMoreElements()) {
            URL element = urls.nextElement();
            System.out.println(element);
        }
    }
}
