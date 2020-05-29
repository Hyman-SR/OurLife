package com.ourlife.base.jdk.gcdemo;

/**
 * 演示目的：一个对象的finalize方法只会被系统自动调用一次，当再次回首时，则不会再出发finalize方法
 *
 * @author zhangchao
 * @createdOn 2020/5/29
 */
public class FinalizeExcapeGC {

    public static FinalizeExcapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeExcapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeExcapeGC();

        //对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        //finalize方法优先级较低，暂停一会
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead");
        }

        //再次尝试拯救自己
        SAVE_HOOK = null;
        System.gc();
        //finalize方法优先级较低，暂停一会
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead");
        }
    }
}
