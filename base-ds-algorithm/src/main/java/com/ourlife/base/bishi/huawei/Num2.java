package com.ourlife.base.bishi.huawei;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangchao
 * @createdOn 2020/8/21
 */
public class Num2 {

    public static void main(String[] args) {

    }

    public static void printValue(String str) {
        Map<Character, Integer> map = new HashMap<>(str.length());
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                int count = map.get(c) + 1;
                map.put(c, count);
                break;
            }
            map.put(c, 1);
        }
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
        Map<Integer, TreeSet<Character>> map1= new HashMap<>(map.size());
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            if (!map1.containsKey(next.getKey())) {
                TreeSet<Character> characters = new TreeSet<>(new Comparator<Character>() {
                    @Override
                    public int compare(Character o1, Character o2) {
                        return o2.charValue() - o1.charValue();
                    }
                });
                map1.put(next.getValue(), characters);
            }
            map1.get(next.getValue()).add(next.getKey());
            map1.put(next.getValue(), map1.get(next.getValue()));
        }
        Iterator<Map.Entry<Integer, TreeSet<Character>>> ite = map1.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Integer, TreeSet<Character>> next = ite.next();
            TreeSet<Character> set = next.getValue();
            for (Character c : set) {
                System.out.print(c + ":" + next.getKey() + ";");
            }
        }
    }
}
