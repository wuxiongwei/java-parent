package com.wuxiongwei.java.touse.java.multi_thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 **AtomicInteger 类常用方法**

 ```java
 public final int get() //获取当前的值
 public final int getAndSet(int newValue)//获取当前的值，并设置新的值
 public final int getAndIncrement()//获取当前的值，并自增
 public final int getAndDecrement() //获取当前的值，并自减
 public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
 boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
 public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
 ```
 */
@SuppressWarnings("all")
public class AtomicIntegerT {
    public static void main(String[] args) {
        knowAtomicInteger();
        NoUseAtomic noUseAtomic = new NoUseAtomic();
        noUseAtomic.increment();
        System.out.println(noUseAtomic.getCount());
        UseAtomic useAtomic = new UseAtomic();
        useAtomic.increment();
        System.out.println(useAtomic.getCount());
    }

    private static void knowAtomicInteger() {
        int temvalue = 0;
        AtomicInteger i = new AtomicInteger(0);
        temvalue = i.getAndSet(3);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
        temvalue = i.getAndIncrement();
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
        temvalue = i.getAndAdd(5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:4;  i:9
    }

    public static class NoUseAtomic{
        private volatile int count = 0;
        //若要线程安全执行执行count++，需要加锁
        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static class UseAtomic {
        private AtomicInteger count = new AtomicInteger();

        public void increment() {
            count.incrementAndGet();
        }
        //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
        public int getCount() {
            return count.get();
        }
    }

}
