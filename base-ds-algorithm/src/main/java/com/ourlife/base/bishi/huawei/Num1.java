package com.ourlife.base.bishi.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangchao
 * @createdOn 2020/8/21
 */
public class Num1 {

    public static void main(String[] args) {
        new HashMap<>(0);
        Scanner input=new Scanner(System.in);
        getAllValue(input.nextInt());
    }

    private static void getAllValue(int num) {
        List<Integer> values = new ArrayList<>();
        for (int i = 2; i < num; i++) {
            if (isHuiWen(i + "") && isSuShu(i)) {
                values.add(i);
            }
        }
        System.out.print(values.size());
        for (Integer value : values) {
            System.out.print("," + value);
        }
    }

    private static boolean isHuiWen(String str) {
        if (str.length() <= 1) {
            return true;
        }
        int left, right;
        // 12321  123321
        if (str.length() == 2) {
            left = 0;
            right = 1;
        } else if (str.length() % 2 == 0) {
            left = str.length() / 2 - 1;
            right = str.length();
        } else {
            left = str.length() / 2 - 1;
            right = str.length() / 2 + 1;
        }
        while (left >= 0 && right < str.length()) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    private static boolean isSuShu(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
