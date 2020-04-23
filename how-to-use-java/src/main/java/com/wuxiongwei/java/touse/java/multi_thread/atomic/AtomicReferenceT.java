package com.wuxiongwei.java.touse.java.multi_thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 引用类型原子类介绍

 基本类型原子类只能更新一个变量，如果需要原子更新多个变量，需要使用 引用类型原子类。

 - AtomicReference：引用类型原子类
 - AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
 - AtomicMarkableReference ：原子更新带有标记的引用类型。该类将 boolean 标记与引用关联起来，~~也可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。~~

 上面三个类提供的方法几乎相同，所以我们这里以 AtomicReference 为例子来介绍。

 */
public class AtomicReferenceT {
    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("SnailClimb", 22);
        ar.set(person);
        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(person, updatePerson);

        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }
}


class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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
