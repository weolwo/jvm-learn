package com.poplar.classload;

/**
 * Created By poplar on 2019/11/6
 * <p>对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为 [L com.hisense.classloader.Student3 这种形式。
 * 对于数组来说，JavaDoc经构成数据的元素成为Component，实际上是将数组降低一个维度后的类型。
 * </p>
 * <p>助记符：anewarray：表示创建一个引用类型（如类、接口）的数组，并将其引用值压入栈顶</p>
 * <p>助记符：newarray：表示创建一个指定原始类型（int boolean float double）的数组，并将其引用值压入栈顶</p>
 */
public class ClassLoadTest4 {
    public static void main(String[] args) {
        Student3 student3 = new Student3();                //创建类的实例，属于主动使用，会导致类的初始化
        Student3[] studentArr = new Student3[1];          //不是主动使用
        System.out.println(student3.getClass());          //输出 [com.poplar.classload.Student3
        System.out.println(studentArr.getClass().getSuperclass());    //输出Object
        System.out.println("----------------------------");
        int[] i = new int[1];
        System.out.println(i.getClass());                    //输出 [ I
        System.out.println(i.getClass().getSuperclass());    //输出Object
    }
}

class Student3 {
    static {
        System.out.println("Student3 static block");
    }
}
