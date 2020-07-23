package com.wuxiongwei.java.touse.java.basic.chapter22;

import com.wuxiongwei.java.touse.java.basic.chapter14.Circle;
import com.wuxiongwei.java.touse.java.basic.chapter14.GeometricObject;
import com.wuxiongwei.java.touse.java.basic.chapter14.Rectangle;

import java.util.Set;
import java.util.TreeSet;

public class TestTreeSetWithComparator {
  public static void main(String[] args) {
    // Create a tree set for geometric objects using a comparator
    Set<GeometricObject> set =
      new TreeSet<GeometricObject>(new GeometricObjectComparator());
    set.add(new Rectangle(4, 5));
    set.add(new Circle(40));
    set.add(new Circle(40));
    set.add(new Rectangle(4, 1));

    // Display geometric objects in the tree set
    System.out.println("A sorted set of geometric objects");
    for (GeometricObject element: set)
      System.out.println("area = " + element.getArea());
  }
}
