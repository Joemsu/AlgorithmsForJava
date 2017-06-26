package com.github.joemsu.algorithms.searching;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-06-24 上午10:51
 *         顺序查找（基于无序链表）
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：N， 插入:N
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：N/2 插入：N
 *
 */
public class SequentialSearchST<K extends Comparable<K>, V> {

    private Node first;

    private int n;

    private class Node {
        private K key;

        private V value;

        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     *
     * @param key 要插入的键
     * @param value 要插入的值
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        for (Node temp = first; temp != null; temp = temp.next) {
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }
        }
        n++;
        //如果没有return 则说明没有找到，那么新建一个结点放在头部
        first = new Node(key, value, first);
    }

    /**
     *
     * @return 是否存在K某个Key
     */
    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     *
     * @param key 要查找的键
     * @return 返回对应的值
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (Node temp = first; temp != null; temp = temp.next) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
        }
        return null;
    }

    /**
     *
     * @return 是否是空的
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     *
     * @return 表中的键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     *
     * @param key 要删除的键值对的key
     */
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (first == null) {
            return;
        }
        if (first.key.equals(key)) {
            first = first.next;
            n--;
            return;
        }
        for(Node parent = first; parent != null;) {
            Node son = parent.next;
            if (son.key.equals(key)) {
                parent.next = son.next;
                return;
            }
            parent = son;
        }
    }

    public Iterable<K> keys() {
        return new STIterable();
    }

    private class STIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new STIterator(first);
        }

        private class STIterator implements Iterator<K> {

            private Node temp;

            public STIterator(Node temp) {
                this.temp = temp;
            }

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                K ret = temp.key;
                temp = temp.next;
                return ret;
            }
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        String[] strings = "S E A R C H E X A M P L E".split(" ");
        for (int i = 0; i < strings.length; i++) {
            String key = strings[i];
            st.put(key, i);
        }
        for (String s : st.keys())
            System.out.print(s + " " + st.get(s) + ", ");
        System.out.println("");
        System.out.println("-------------");
        st.delete("S");
        for (String s : st.keys())
            System.out.print(s + " " + st.get(s) + ", ");
    }
}
