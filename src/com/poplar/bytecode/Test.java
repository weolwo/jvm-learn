package com.poplar.bytecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created BY poplar ON 2019/12/3
 * 由于编译器收集的顺序是由语句的源文件中出现的顺序决定的
 */
public class Test {
    static {
        i = 0;
        // System.out.println(i);//illegal forward reference
    }

    static int i = 1;

    public static void main(String[] args) {
        // int[][][] arr = new int[0][1][-1];//NegativeArraySizeException
        // System.out.println(arr);
    }

}
