package com.wuxiongwei.java.touse.java.algorithms.search;

/**
 Knuth-Morris-Pratt KMP
 */
public class KMP {

//    给定一个模式和一个文本 kmp 查找在文本中找到该模式的所有位置（甚至重叠的图案匹配）*
    public static java.util.List<Integer> kmp(String txt, String pat) {
        java.util.List<Integer> matches = new java.util.ArrayList<>();
        if (txt == null || pat == null) return matches;

        int m = pat.length(), n = txt.length(), i = 0, j = 0;
        if (m > n) return matches;

        int[] arr = kmpHelper(pat, m);

        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                matches.add(i - j);
                j = arr[j - 1];
            } else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) j = arr[j - 1];
                else i = i + 1;
            }
        }

        return matches;
    }

//    对于每个索引，我计算从 0 开始的正前缀和从 i 开头的正确后缀之间的最长匹配。
    private static int[] kmpHelper(String pat, int m) {
        int[] arr = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (pat.charAt(i) == pat.charAt(len)) {
                arr[i++] = ++len;
            } else {
                if (len > 0) len = arr[len - 1];
                else i++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        java.util.List<Integer> matches = kmp("abababa", "aba");
        System.out.println(matches); // [0, 2, 4]

        matches = kmp("abc", "abcdef");
        System.out.println(matches); // []

        matches = kmp("P@TTerNabcdefP@TTerNP@TTerNabcdefabcdefabcdefabcdefP@TTerN", "P@TTerN");
        System.out.println(matches); // [0, 13, 20, 51]
    }
}
