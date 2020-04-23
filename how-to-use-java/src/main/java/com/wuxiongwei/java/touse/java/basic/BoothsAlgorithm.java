package com.wuxiongwei.java.touse.java.basic;

/**
 该文件包含Booths算法的实现，该算法查找按字典顺序最小的字符串旋转。
 */
public class BoothsAlgorithm {

//    执行Booths算法，返回最早的索引
//     字典上最小的字符串旋转。注意比较
//     是使用ASCII值完成的，因此混合使用小写和大写
//     字母可能会给您带来意想不到的结果，O（n）
    public static int leastCyclicRotation(String s) {
        s += s;
        int[] f = new int[s.length()];
        java.util.Arrays.fill(f, -1);
        int k = 0;
        for (int j = 1; j < s.length(); j++) {
            char sj = s.charAt(j);
            int i = f[j - k - 1];
            while (i != -1 && sj != s.charAt(k + i + 1)) {
                if (sj < s.charAt(k + i + 1)) k = j - i - 1;
                i = f[i];
            }
            if (sj != s.charAt(k + i + 1)) {
                if (sj < s.charAt(k)) k = j;
                f[j - k] = -1;
            } else f[j - k] = i + 1;
        }
        return k;
    }

    public static void main(String[] args) {

        String s = "abcde";
        int index = leastCyclicRotation(s);

        // Outputs 0 since the string is already in its least rotation
        System.out.println(index);

        s = "cdeab";
        index = leastCyclicRotation(s);

        // Outputs 3 since rotating the string 3 times to the left makes
        // the smallest rotation: "cdeab" -> "deabc" -> "eabcd" -> "abcde"
        System.out.println(index);
    }
}
