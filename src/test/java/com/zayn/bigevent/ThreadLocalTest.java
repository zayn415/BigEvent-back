package com.zayn.bigevent;

import org.junit.jupiter.api.Test;

/**
 * @author zayn
 * * @date 2024/3/28/13:15
 */

public class ThreadLocalTest {
    ThreadLocal tl = new ThreadLocal();
    @Test
    public void test() {
        new Thread(()-> {
            tl.set("aa");
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
        }, "t1").start();
        
        new Thread(()-> {
            tl.set("bb");
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
            System.out.println(Thread.currentThread().getName() + " " + tl.get());
        }, "t2").start();
    }
}
