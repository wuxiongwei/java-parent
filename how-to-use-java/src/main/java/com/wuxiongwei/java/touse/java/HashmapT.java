package com.wuxiongwei.java.touse.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
Hashmap
 */
@SuppressWarnings("all")
public class HashmapT {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("a","1");
        map.get("a");
        Object a = map.merge("a", "2",(old1, new1) -> old1 +""+ new1);
        System.out.println("merger result = "+a);
        Object b = map.merge("b", "2",(count, incr) -> count +""+ incr);
        System.out.println("merger result = "+b);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        entries.forEach(System.out::println);
        map.forEach((k,v)->{
            System.out.println("K:"+k);
            System.out.println("V:"+v);
        });
        map.keySet().forEach(System.out::println);

        Map<String,Integer> map2 = new HashMap<>();
        map2.put("a",1);
        map2.put("a",2);
        map2.merge("a",2,Integer::sum);
        map2.entrySet().forEach(System.out::println);

    }
}
