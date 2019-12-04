package com.poplar.memory;

/**
 * Created BY poplar ON 2019/12/1
 * 此代码演示两点：
 * 1，对象可以在GC时自我拯救
 * 2，这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统调用一次
 */
public class FinalizeEscapeGC {

    private static FinalizeEscapeGC SAVE_HOOK = null;

    public void isLive() {
        System.out.println("yes,i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        //因为finalize()方法优先级很低，所以暂停0.5m
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isLive();
        } else {
            System.out.println("no, i am dead :(");
        }

        //和上面的代码一样，但是这次自我拯救失败了
        SAVE_HOOK = null;
        System.gc();

        //因为finalize()方法优先级很低，所以暂停0.5m
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isLive();
        } else {
            System.out.println("no, i am dead :(");
        }

        //最后输出结果：
        //finalize method executed 该方法只执行了一次
        //yes,i am still alive :)
        //no, i am dead :(
    }
}
