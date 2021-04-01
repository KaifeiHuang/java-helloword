package com.kaifei.lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class SynchronizedDemo {

    static int m = 0;
    boolean sleepFlag = false;

    Object obj = new Object();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        countHash(synchronizedDemo.hashCode());
        String objectLayout = ClassLayout.parseInstance(synchronizedDemo).toPrintable();
        System.out.println(objectLayout);

    }

    static void countHash(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);
        long hasCode = 0;

        for (int index =7; index>0; index--) {
            hasCode |= (unsafe.getByte(obj, index) & 0xFF) << ((index - 1) * 8);
        }

        String code = Long.toHexString(hasCode);
        System.out.println("object hex code ------------- " + code);
    }

    public void addNoSync(){
//        SynchronizedDemo.sleep(1000);
        for (int i=0; i< 100; i++){
            m++;
        }
        System.out.println("m = " + m);

    }

    public void addWithSync(){

        synchronized (obj) {

            for (int i=0; i< 10000; i++){
                m++;

                if (m ==888){
                    if (sleepFlag ){
                        SynchronizedDemo.sleep(1000);
                    }
                }

            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("m = " + m);

        }
    }


    @Test
    public void testWithNoSync(){
        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                addNoSync();
            }).start();
        }

        System.out.println("m = " + m);
    }


    @Test
    public void testWithSync(){
        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                addWithSync();
            }).start();
        }
    }

    @Test
    public void testWithSyncButNewInstance(){
        for (int i = 0; i < 5; i++) {
            SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

            if (i == 3 || i==1) {
                sleepFlag = true;

                SynchronizedDemo.sleep(5000);
                synchronizedDemo.addWithSync();
            }
            else
            {

                synchronizedDemo.addWithSync();

            }

        }

    }


    public static void sleep(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
