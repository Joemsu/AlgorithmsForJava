package com.github.joemsu.algorithms.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-07-02 上午11:40
 *         索引最小优先队列
 */
public class IndexMinPriorityQueues<K extends Comparable<K>> implements Iterable<Integer> {

    private K[] keys;

    /**
     * pq[n] = i
     */
    private int[] pq;

    /**
     * qp[i] = n,用于删除等操作的时候 根据索引来找到对应的n
     */
    private int[] qp;

    private int n;

    private final int maxN;

    public IndexMinPriorityQueues(int max) {
        n = 0;
        this.maxN = max;
        keys = (K[]) new Comparable[max + 1];
        pq = new int[max + 1];
        qp = new int[max + 1];
        for (int i = 0; i < max + 1; i++) {
            //用来判断是否包含某个键
            qp[i] = -1;
        }
    }

    /**
     * @param k k
     */
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }


    private void sink(int k) {
        while (2 * k <= this.n) {
            int j = 2 * k;
            //找出子堆中较小的一个
            if (j < n && greater(j, j + 1)) j++;
            //如果k比j小的话 那就退出循环，否则交换
            if (!greater(k, j)) break;
            exch(j, k);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * @param i    索引i
     * @param item 要插入的值
     *             插入一个元素，将他和索引k相关联
     */
    public void insert(int i, K item) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        keys[i] = item;
        pq[++n] = i;
        qp[i] = n;
        swim(n);
    }

    /**
     * @param i   索引i
     * @param key 更换的元素key
     *            将索引为k的元素设置为item
     */
    public void change(int i, K key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * @param i 索引i
     * @return 是否存在索引为k的元素
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    /**
     * @param i 索引i
     *          删去索引为i及其相关联的元素
     */
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        //swim这一步应该不需要，因为n这个是最大的，不可能再向上交换了
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /**
     * @return 返回最小的元素
     */
    public K min() {
        return keys[pq[1]];
    }

    /**
     * @return 返回最小元素的索引
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * @return 删除最小元素并且返回他的索引
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n + 1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        return min;
    }

    /**
     * @return 优先队列是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return 优先队列中的元素的数量
     */
    public int size() {
        return n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPriorityQueues<K> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPriorityQueues<K>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
