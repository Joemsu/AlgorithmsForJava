package com.github.joemsu.algorithms.sort;

/**
 * @author joemsu 2017-06-23 上午11:15
 *         最小优先队列
 */
public class MinPriorityQueues<T extends Comparable<T>> {

    private T[] pq;

    private int N = 0;

    private static final int MAX_CONTAINER = 10;

    public MinPriorityQueues() {
        pq = (T[]) new Comparable[MAX_CONTAINER + 1];
    }

    public MinPriorityQueues(int max) {
        pq = (T[]) new Comparable[max + 1];
    }

    /**
     * 交换两个元素的位置
     *
     * @param i i
     * @param j j
     */
    public void swap(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * @param i i
     * @param j j
     * @return 判断pq[i] < pq[j]
     */
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * @param i 由下至上的堆有序化（上浮）
     */
    public void swim(int i) {
        while (i > 1 && !less(i / 2, i)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * @param i 由上至下的堆有序化（下沉）
     */
    public void sink(int i) {
        while (2 * i <= N) {
            int k = 2 * i;
            if (k < N && !less(k, k + 1))
                k++;
            if (!less(k, i))
                break;
            swap(i, k);
            i = k;
        }
    }

    /**
     * @return 返回最小的元素
     */
    public T min() {
        return pq[1];
    }

    /**
     * @param t 插入的元素
     *          将插入的元素放到最底下，然后上浮
     */
    public void insert(T t) {
        pq[++N] = t;
        swim(N);
    }

    /**
     * @return 删除并返回最小的元素
     * 将最底部的元素元素与顶部的元素交换，然后下沉
     */
    public T delMin() {
        //从根结点获得最大元素
        T min = pq[1];
        swap(1, N--);
        //防止对象游离
        pq[N + 1] = null;
        //恢复有序性
        sink(1);
        return min;
    }

    /**
     * @return 返回队列是否为空
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return 返回优先队列中的元素个数
     */
    public int size() {
        return N;
    }

    public static void main(String[] args) {
        MinPriorityQueues<String> minPriorityQueues = new MinPriorityQueues<>(11);
        String[] s = "P E H O A T R G N I S".split(" ");
        for (String insert : s) {
            minPriorityQueues.insert(insert);
        }
        while (!minPriorityQueues.isEmpty()) {
            System.out.print(minPriorityQueues.delMin() +  " ");
        }
    }
}
