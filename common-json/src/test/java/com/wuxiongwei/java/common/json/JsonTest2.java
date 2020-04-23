/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.wuxiongwei.java.common.json;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class JsonTest2 {
  public static class Foo {
    private String fooBar;
    private List<Cat> cats;

    public List<Cat> getCats() {
      return cats;
    }

    public void setCats(List<Cat> cats) {
      this.cats = cats;
    }

    public String getFooBar() {
      return fooBar;
    }

    public void setFooBar(String fooBar) {
      this.fooBar = fooBar;
    }
  }
  public static class Cat {
      private String name;
      private Integer age;

    public Cat(String name, Integer age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }
  }

  @Test
  public void testSerializeDefault() throws Exception {
    String expectedJson = "{\"cats\":[{\"name\":\"c1\",\"age\":10},{\"name\":\"c2\",\"age\":20}],\"fooBar\":\"test\"}";
    Foo foo = new Foo();
    foo.setFooBar("test");
    Cat c1 = new Cat("c1",10);
    Cat c2 = new Cat("c2",20);
    List<Cat> list = new ArrayList<>();
    list.add(c1);
    list.add(c2);

    foo.setCats(list);
//    System.out.println(JsonHelper.toJson(foo));
//    {"cats":[{"name":"c1","age":10},{"name":"c2","age":20}],"fooBar":"test"}
    assertEquals(expectedJson, JsonHelper.toJson(foo));
  }

}
