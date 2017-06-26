package com.github.joemsu.algorithms.searching;

/**
 * @author joemsu 2017-06-26 下午4:05
 *         基于拉链的散列表(与java里的map类似，不过在jdk1.8里 map使用了数组加链表加红黑树)
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：<lgN， 插入: <lgN
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：N/(2M) 插入：N/M (M为容量)
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {

    /**
     * 存放链表对象的数组
     */
    private SequentialSearchST<Key, Value>[] st;

    /**
     * 散列表的大小
     */
    private int m;

    /**
     * 键值对的总数
     */
    private int n;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    private int hash(Key key) {
        //将32位的整数变成31位的非负整数，然后散列到0 -> m-1里
        return (key.hashCode() & 0xfffffff) % m;
    }

    public Value get(Key key) {
        int index = hash(key);
        return st[index].get(key);
        //return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        int index = hash(key);
        st[index].put(key, value);
        n++;
        //return st[hash(key)].put(key, value);
    }
}
