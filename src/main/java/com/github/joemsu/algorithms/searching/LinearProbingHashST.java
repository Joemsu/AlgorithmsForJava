package com.github.joemsu.algorithms.searching;

/**
 * @author joemsu 2017-06-26 下午4:17
 *         基于线性探测的符号表
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：clgN， 插入: clgN
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：<1.5 插入：<2.5
 */
public class LinearProbingHashST<Key, Value> {

    private Key[] keys;

    private Value[] values;

    /**
     * 总的键值对数
     */
    private int n;

    /**
     * 线性探测表的大小
     */
    private int m = 16;


    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    public LinearProbingHashST(int size) {
        keys = (Key[]) new Object[size];
        values = (Value[]) new Object[size];
        this.m = size;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0xffffff) % m;
    }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(size);
        for (int i = 0; i < this.m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        this.values = t.values;
        this.keys = t.keys;
        this.m = t.m;
    }

    public void put(Key key, Value value) {
        // 如果键值对大于等于容量的一半 则扩容
        if (n >= m / 2) resize(2 * m);
        int i;
        for (i = hash(key); keys[i] != null; i = (1 + i) % m) {
            //如果找到，则更新
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (get(key) == null) return;
        int hash = hash(key);
        //先找到要删除的key的索引
        while (!key.equals(keys[hash])) {
            hash = (hash + 1) % m;
        }
        keys[hash] = null;
        values[hash] = null;
        hash = (hash + 1) % m;
        while (keys[hash] != null) {
            Key tempKey = keys[hash];
            Value tempValue = values[hash];
            keys[hash] = null;
            values[hash] = null;
            put(tempKey, tempValue);
            hash = (hash + 1) % m;
        }
        n--;
        if (n > 0 && n == m / 8) resize(m / 2);
    }
}

