package com.wuxiongwei.java.touse.java.algorithms.search;

import java.util.function.DoubleFunction;

/**
 三分查找

 三元搜索与二元搜索相似，不同之处在于三元搜索的作用是
  然后增加。三元搜索的此实现返回与
  您正在搜索的函数的最小输出值。
  
   时间复杂度：O（log（高-低））。
  
   注意：您还可以使用先增大后减小的函数，只需对函数取反即可
 */
public class TernarySearch {
    // 定义一个非常小的epsilon值以比较双精度值
    private static final double EPS = 0.000000001;

//    从低到高的间隔执行三元搜索。
//                 请记住，您的函数必须是连续的单峰
//     函数，这意味着函数先减小后增大（U形）
    public static double ternarySearch(double low, double high, DoubleFunction<Double> function) {
        Double best = null;
        while (true) {
            double mid1 = (2 * low + high) / 3, mid2 = (low + 2 * high) / 3;
            double res1 = function.apply(mid1), res2 = function.apply(mid2);
            if (res1 > res2) low = mid1;
            else high = mid2;
            if (best != null && Math.abs(best - mid1) < EPS) break;
            best = mid1;
        }
        return best;
    }

    public static void main(String[] args) {

        // Search for the lowest point on the function x^2 + 3x + 5
        // using a ternary search on the interval [-100, +100]
        DoubleFunction<Double> function = (x) -> (x * x + 3 * x + 5);
        double root = ternarySearch(-100.0, +100.0, function);
        System.out.printf("%.4f\n", root);
    }
}
