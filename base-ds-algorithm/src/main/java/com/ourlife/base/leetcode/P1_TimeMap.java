package com.ourlife.base.leetcode;

import javafx.util.Pair;

import java.util.*;

/**
 * 基于时间的键值存储  https://leetcode-cn.com/problems/time-based-key-value-store/
 *
 * @author zhangchao
 * @createdOn 2020/6/16
 */
public class P1_TimeMap {
}

/**
 * HashMao + 二分查找
 */
class TimeMap_1 {
    Map<String, List<Pair<Integer, String>>> M;

    public TimeMap_1() {
        M = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key)) {
            M.put(key, new ArrayList<Pair<Integer, String>>());
        }
        M.get(key).add(new Pair<>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) {
            return "";
        }
        List<Pair<Integer, String>> A = M.get(key);
        int i = Collections.binarySearch(A, new Pair<Integer, String>(timestamp, "{"), (a, b) -> Integer.compare(a.getKey(), b.getKey()));

        if (i >= 0) {
            return A.get(i).getValue();
        } else if (i == -1) {
            return "";
        } else {
            return A.get(-i - 2).getValue();
        }
    }
}

/**
 * TreeMap
 */
class TimeMap_2 {
    Map<String, TreeMap<Integer, String>> M;

    public TimeMap_2() {
        M = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key)) {
            M.put(key, new TreeMap<>());
        }
        M.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tree = M.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }
}
