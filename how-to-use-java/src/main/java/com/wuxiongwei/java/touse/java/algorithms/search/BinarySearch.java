package com.wuxiongwei.java.touse.java.algorithms.search;

import java.util.function.DoubleFunction;

/**
    二分
 O（log（ n ））
    如果需要对离散值进行二进制搜索，则应使用Java的二进制搜索：
    java.util.Arrays.binarySearch（int [] ar，int key）但是，如果您需要
    对实数进行二进制搜索，您可以使用此实现。
 */
public class BinarySearch {
    // 直接比较双精度值是不好的做法。
    // 使用较小的epsilon值是首选方法
    private static final double EPS = 0.00000001;

    public static double binarySearch(
            double lo, double hi, double target, DoubleFunction<Double> function) {

        if (hi <= lo) throw new IllegalArgumentException("hi should be greater than lo");

        double mid;
        do {

            // 找到中间点
            mid = (hi + lo) / 2.0;

//            计算中间点的函数值
//             请注意，f可以是任何函数，而不仅仅是平方根函数
            double value = function.apply(mid);

            if (value > target) {
                hi = mid;
            } else {
                lo = mid;
            }

        } while ((hi - lo) > EPS);

        return mid;
    }

    public static void main(String[] args) {

//        示例＃1
//         假设我们想知道875的平方根是什么，
//         我们不知道美妙的Math.sqrt（）函数。
//         一种方法是使用二进制搜索，因为我们知道
//         875的平方根在以下区域范围内：[0，875]。
//
//         我们可以定义我们的函数为f（x）= x * x和我们的目标
//         值是875。当我们对f（x）进行二分搜索时，
//         越来越接近875的值，我们变得越来越好
//         x的值（875的平方根）

        double lo = 0.0;
        double hi = 875.0;
        double target = 875.0;

        DoubleFunction<Double> function = (x) -> (x * x);

        double sqrtVal = binarySearch(lo, hi, target, function);
        System.out.printf("sqrt(%.2f) = %.5f, x^2 = %.5f\n", target, sqrtVal, (sqrtVal * sqrtVal));

//        例子＃2
//         假设我们要使用以下公式找到体积为100m ^ 3的球体的半径
//         二进制搜索。我们知道，对于一个球体，其体积为
//         V =（4/3）* pi * r ^ 3，因此我们要做的就是在半径上进行二进制搜索。
//
//         注意：这是一个愚蠢的示例，因为您可以求解r，但是
//         说明了二进制搜索如何成为一种强大的技术。

        double radiusLowerBound = 0;
        double radiusUpperBound = 1000;
        double volume = 100.0;
        DoubleFunction<Double> sphereVolumeFunction = (r) -> ((4.0 / 3.0) * Math.PI * r * r * r);

        double sphereRadius =
                binarySearch(radiusLowerBound, radiusUpperBound, volume, sphereVolumeFunction);

        System.out.printf("Sphere radius = %.5fm\n", sphereRadius);
    }
}
