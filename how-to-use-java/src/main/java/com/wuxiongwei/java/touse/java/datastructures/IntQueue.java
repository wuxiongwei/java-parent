package com.wuxiongwei.java.touse.java.datastructures;

/**
 此文件包含仅整数队列的实现，该实现非常快速且
  轻巧。就性能而言，它的性能可能优于java.util.ArrayDeque（Java最快的队列
  实施）的40倍！请参见下面的基准测试以获取证明。但是，不利的一面
  您是否需要知道任何队列中将在队列内的元素数量的上限
  给这个队列工作的时间。
 */
public class IntQueue {

    private int[] ar;
    private int front, end, sz;

    // maxSize is the maximum number of items
    // that can be in the queue at any given time
    public IntQueue(int maxSize) {
        front = end = 0;
        sz = maxSize + 1;
        ar = new int[sz];
    }

    // Return true/false on whether the queue is empty
    public boolean isEmpty() {
        return front == end;
    }

    // Return the number of elements inside the queue
    public int size() {
        if (front > end) return (end + sz - front);
        return end - front;
    }

    public int peek() {
        return ar[front];
    }

    // Add an element to the queue
    public void enqueue(int value) {
        ar[end] = value;
        if (++end == sz) end = 0;
        if (end == front) throw new RuntimeException("Queue too small!");
    }

    // Make sure you check is the queue is not empty before calling dequeue!
    public int dequeue() {
        int ret_val = ar[front];
        if (++front == sz) front = 0;
        return ret_val;
    }

    // Example usage
    public static void main(String[] args) {

        IntQueue q = new IntQueue(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println(q.dequeue()); // 1
        System.out.println(q.dequeue()); // 2
        System.out.println(q.dequeue()); // 3
        System.out.println(q.dequeue()); // 4

        System.out.println(q.isEmpty()); // false

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.dequeue()); // 5
        System.out.println(q.dequeue()); // 1
        System.out.println(q.dequeue()); // 2
        System.out.println(q.dequeue()); // 3

        System.out.println(q.isEmpty()); // true

        benchMarkTest();
    }

    // BenchMark IntQueue vs ArrayDeque.
    private static void benchMarkTest() {

        int n = 10000000;
        IntQueue intQ = new IntQueue(n);

        // IntQueue times at around 0.0324 seconds
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) intQ.enqueue(i);
        for (int i = 0; i < n; i++) intQ.dequeue();
        long end = System.nanoTime();
        System.out.println("IntQueue Time: " + (end - start) / 1e9);

        // ArrayDeque times at around 1.438 seconds
        java.util.ArrayDeque<Integer> arrayDeque = new java.util.ArrayDeque<>();
        // java.util.ArrayDeque <Integer> arrayDeque = new java.util.ArrayDeque<>(n); // strangely the
        // ArrayQueue is slower when you give it an initial capacity.
        start = System.nanoTime();
        for (int i = 0; i < n; i++) arrayDeque.offer(i);
        for (int i = 0; i < n; i++) arrayDeque.poll();
        end = System.nanoTime();
        System.out.println("ArrayDeque Time: " + (end - start) / 1e9);
    }
}
