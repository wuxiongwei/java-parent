package com.wuxiongwei.java.touse.java.algorithms.search;

/**
 O（log（log（n（n））））
 插值搜索
 只能用于有序的数组
 */
public class InterpolationSearch {
    /**
     当元素均匀分布时，快速替代二进制搜索。这个
          算法以〜O（log（log（n（n））））的时间复杂度运行。
     *
     * @param nums - 包含均匀分布值的有序列表。
     * @param val -  我们要以“ nums”为单位的值
     */
    public static int interpolationSearch(int[] nums, int val) {
        int lo = 0, mid = 0, hi = nums.length - 1;
        while (nums[lo] <= val && nums[hi] >= val) {
            mid = lo + ((val - nums[lo]) * (hi - lo)) / (nums[hi] - nums[lo]);
            if (nums[mid] < val) {
                lo = mid + 1;
            } else if (nums[mid] > val) {
                hi = mid - 1;
            } else return mid;
        }
        if (nums[lo] == val) return lo;
        return -1;
    }

    public static void main(String[] args) {

        int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};

//        由于值数组中存在25个，因此插值搜索
//         返回它在索引2处找到25
        System.out.println(interpolationSearch(values, 25));

        // 111不存在，所以我们得到-1作为索引值
        System.out.println(interpolationSearch(values, 111));
    }
}
