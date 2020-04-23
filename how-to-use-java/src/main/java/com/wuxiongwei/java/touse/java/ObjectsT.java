package com.wuxiongwei.java.touse.java;

import java.util.Objects;

/**
 Objects
 */
@SuppressWarnings("all")
public class ObjectsT {
    public static void main(String[] args) {
        Objects.requireNonNull("123");
        Objects.requireNonNull("abc");
        Objects.requireNonNull(123);

        try {
            Objects.requireNonNull(null,"you are null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(1.0);
        System.out.println(Objects.nonNull(null));
        System.out.println(Objects.isNull(null));
    }
}
