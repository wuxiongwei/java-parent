package com.wuxiongwei.java.touse.java.datastructures;

/**
 大多数时候，当您使用数组时，它将其放在数组内，因此为什么不有
 超快速整数仅数组？此文件包含仅整数数组的实现。
 它可以比Java的ArrayList快10-15倍！
 */
public class IntArray implements Iterable<Integer> {

    private static final int DEFAULT_CAP = 1 << 3;

    public int[] arr;
    public int len = 0;
    private int capacity = 0;

    // 使用默认容量初始化阵列
    public IntArray() {
        this(DEFAULT_CAP);
    }

    // 初始化具有一定容量的阵列
    public IntArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    // 给定一个数组，使其成为动态数组！
    public IntArray(int[] array) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        arr = java.util.Arrays.copyOf(array, array.length);
        capacity = len = array.length;
    }

    // 返回数组的大小
    public int size() {
        return len;
    }

    // 关于数组是否为空，返回true / false
    public boolean isEmpty() {
        return len == 0;
    }

//    要获得/设置值而没有方法调用开销，可以改为使用array_obj.arr [index]，速度可以提高大约10倍！
    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int elem) {
        arr[index] = elem;
    }

    // 向此动态数组添加元素
    public void add(int elem) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size
            arr = java.util.Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
        }
        arr[len++] = elem;
    }

//    删除此列表中指定索引处的元素。 如果可能，请避免调用此方法，因为这会花费O（n）的时间删除元素（因为您必须重建数组！）
    public void removeAt(int rm_index) {
        System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index - 1);
        --len;
        --capacity;
    }

//    搜索并删除在数组中找到的元素
//     如果可能，请避免调用此方法，因为这会花费O（n）的时间
    public boolean remove(int elem) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // 反转此数组的内容
    public void reverse() {
        for (int i = 0; i < len / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
    }

//    在此数组上执行二进制搜索以找到O（log（n））时间的元素
//     确保此数组已排序！如果找不到项目，则返回值<0
    public int binarySearch(int key) {
        int index = java.util.Arrays.binarySearch(arr, 0, len, key);
        // if (index < 0) index = -index - 1; // 如果找不到，它将告诉您要插入的位置
        return index;
    }

    //对该数组排序
    public void sort() {
        java.util.Arrays.sort(arr, 0, len);
    }

    // 迭代器仍然很快，但不如循环迭代快
    @Override
    public java.util.Iterator<Integer> iterator() {
        return new java.util.Iterator<Integer>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public Integer next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    //例子
    public static void main(String[] args) {

        IntArray ar = new IntArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(6);
        ar.add(-2);

        ar.sort(); // [-2, 3, 6, 7]

        // Prints [-2, 3, 6, 7]
        for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

        // Prints [-2, 3, 6, 7]
        System.out.println(ar);

    }
}
