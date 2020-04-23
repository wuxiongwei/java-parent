package com.wuxiongwei.java.touse.java.multi_thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 如果需要原子更新某个类里的某个字段时，需要用到对象的属性修改类型原子类。

 - AtomicIntegerFieldUpdater:原子更新整形字段的更新器
 - AtomicLongFieldUpdater：原子更新长整形字段的更新器
 - AtomicReferenceFieldUpdater ：原子更新引用类型里的字段的更新器

 要想原子地更新对象的属性需要两步。第一步，因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。第二步，更新的对象属性必须使用 public volatile 修饰符。

 */
public class AtomicIntegerFieldUpdaterT {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User("Java", 22);
        System.out.println(a.getAndIncrement(user));// 22
        System.out.println(a.get(user));// 23
    }
}

class User {
    private String name;
    public volatile int age;

    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
