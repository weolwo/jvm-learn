package com.poplar.bytecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created BY poplar ON 2019/12/4
 * java伪泛型存在的问题
 * 下啊面的代码不能编译
 */
public class GenericType {

    //由于编译器类型擦除导致方法参数签名相同成为方法重载失败的部分原因
  /*  public static void method(List<String> list) {
        System.out.println("list1");
    }*/

    public static void method(List<Integer> list) {
        System.out.println("list2");
    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        System.out.println(map.get("hello"));
        //擦除法仅仅堆方法code属性中的字节码进行擦除，元数据还保留了原信息，这也是我们能够通过反射取得参数化类型的依据
      /*  LocalVariableTypeTable:
        Start  Length  Slot  Name   Signature
        8      29     1   map   Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;*/
    }

}
