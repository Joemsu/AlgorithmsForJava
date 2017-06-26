package com.github.joemsu.algorithms.searching;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-06-18 下午2:10
 */
public class Queue<T> implements Iterable<T>{

    private Node<T> first;

    private Node<T> last;

    private int N;

    private class Node<P> {
        private P p;
        private Node<P> next;

        public Node(P p) {
            this.p = p;
        }
    }

    public Queue() {
        first = last = null;
    }

    public void enqueue(T t) {
        Node<T> newlast = new Node<>(t);
        if (first == null) {
            first = last = newlast;
        } else {
            last.next = newlast;
            last = newlast;
        }
        N++;
    }

    public T dequeue() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        T ret = first.p;
        if (first.next != null) {
            first = first.next;
        }
        N--;
        return ret;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

}
