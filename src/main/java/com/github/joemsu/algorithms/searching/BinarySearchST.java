package com.github.joemsu.algorithms.searching;

/**
 * @author joemsu 2017-06-24 上午11:54
 *         基于有序数组的二分查找
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：lgN， 插入:N
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：lgN 插入：N/2
 */
public class BinarySearchST<K extends Comparable<K>, V> {

    private K[] keys;

    private V[] values;

    private int n;

    public BinarySearchST(int n) {
        keys = (K[]) new Comparable[n];
        values = (V[]) new Object[n];
    }

    /**
     * 将键值对存入表中（若为空，则将key从表中删除）
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        //如果key已经存在，则找到索引并且替换掉原来的value
        int j = rank(key);
        if (j < n && key.compareTo(keys[j]) == 0) {
            //直接通过比较keys[i]与key是否相等就可以知道是否包含key，如果用contains会用二分查找再搜索，耗费性能
//        if (contains(key)) {
            values[j] = value;
            return;
        }
        // 扩展大小为原来的2倍
        if (n == keys.length) resize(2 * keys.length);
        //如果不存在key
        for (int i = n; i > j; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[j] = key;
        values[j] = value;
        n++;
    }

    /**
     * @param key 键
     * @return 根据key获得value
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("first argument to get() is null");
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }

    /**
     * @param key 键
     * @return 小于key的键的数量
     */
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (keys[mid].compareTo(key) > 0) hi = mid - 1;
            else if (keys[mid].compareTo(key) < 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * @return 表中的键值对数量
     */
    public int size() {
        return n;
    }

    /**
     * @param key 根据键删除key以及其对应的值
     */
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to delete() is null");
        }
        int j = rank(key);
        if (j == n || keys[j].compareTo(key) != 0) {
            //直接通过比较keys[i]与key是否相等就可以知道是否包含key，如果用contains会用二分查找再搜索，耗费性能
//        if (!contains(key)) {
            return;
        }
        for (int i = j; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        n--;
        //避免对象游离
        keys[n] = null;
        values[n] = null;

        // 如果到了1/4就可以重新变化数组容量
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
    }

    /**
     * @param capacity 重新变化大小的容量
     */
    private void resize(int capacity) {
        assert capacity >= n;
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    /**
     * @param key 键
     * @return 是否包含某个key
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * @return 表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return 最小的键
     */
    public K min() {
        return keys[0];
    }

    /**
     * @return 最大的键
     */
    public K max() {
        return keys[n - 1];
    }

    /**
     * @param key key
     * @return 小于等于key的最大键
     */
    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException();
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) != 0) {
            i--;
        }
        if (i == 0) return null;
        return keys[i];
    }

    /**
     * @param key key
     * @return 大于等于key的最小键
     */
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException();
        int i = rank(key);
        if (i == n) return null;
        else return keys[i];
    }


    /**
     * @param k 排名为k 排名从0开始。。
     * @return 排名为k的键
     */
    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    /**
     * 删除最小的键
     */
    public void delteMin() {
        delete(min());
    }

    /**
     * 删除最大的键
     */
    public void deleteMax() {
        delete(max());
    }

    /**
     * @param lo lo
     * @param hi hi
     * @return [lo...hi]之间键的数量
     */
    public int size(K lo, K hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else
            return rank(hi) - rank(lo);
    }

    /**
     * @return 表中所有的键的集合，已排序
     */
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    /**
     * @param lo 低位
     * @param hi 高位
     * @return [lo...hi]之间的键， 已排序
     * 与SequentialSearchST相同
     */
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }


}
