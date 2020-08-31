package com.ourlife.base.leetcode.数组;

import java.util.ArrayList;

/**
 * @author zhangchao
 * @createdOn 2020/8/25
 */
public class _792_匹配子序列的单词数 {

    public static void main(String[] args) {
        _792_匹配子序列的单词数 o = new _792_匹配子序列的单词数();
        int count = o.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});
        System.out.println(count);
    }

    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i) {
            heads[i] = new ArrayList<Node>();
        }
        for (String word : words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }
        for (char c : S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<>();

            for (Node node : old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

    class Node {
        String word;
        int index;
        public Node(String w, int i) {
            word = w;
            index = i;
        }
    }

}
