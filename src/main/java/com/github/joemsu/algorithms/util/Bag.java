package com.github.joemsu.algorithms.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-06-30 下午1:18
 *         背包
 */
public class Bag<T extends Comparable<T>> implements Iterable<T> {


    /**
     * 链表头部
     */
    private Node<T> first;

    /**
     * 背包中的数量
     */
    private int n;

    @Override
    public Iterator<T> iterator() {
        return new ListBagIterator(first);
    }

    private class ListBagIterator implements Iterator<T> {

        private Node<T> current;

        public ListBagIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T ret = this.current.t;
            this.current = this.current.next;
            return ret;
        }
    }


    private class Node<T> {
        private T t;
        private Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
    }

    public void add(T t) {
        Node lastFirst = first;
        first = new Node(t, first);
        n++;
    }

    /**
     * @return 背包是否是空的
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return 背包中的元素数量
     */
    public int size() {
        return n;
    }


}
