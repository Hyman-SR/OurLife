package com.ourlife.base.leetcode;

import javafx.util.Pair;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author zhangchao
 * @createdOn 2020/8/24
 */
public class _121_买卖股票的最佳时机 {

    public static void main(String[] args) {
        _121_买卖股票的最佳时机 o = new _121_买卖股票的最佳时机();
        System.out.println(o.maxProfit(new int[]{2, 4, 1}));
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            int dif = prices[i] - minPrice;
            if (dif > maxProfit) {
                maxProfit = dif;
            }
        }
        return maxProfit;
    }
}
