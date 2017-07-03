package com.github.joemsu.algorithms.graphs;

import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-07-02 下午1:45
 *         图的边
 */
public class Edge implements Comparable<Edge> {

    /**
     * 顶点v
     */
    private final int v;

    /**
     * 另一个顶点
     */
    private final int w;

    /**
     * 边的权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * @return 边的权重
     */
    public double weight() {
        return this.weight;
    }

    /**
     * @return 边的一个顶点
     */
    public int either() {
        return this.v;
    }

    /**
     * @param v 顶点v
     * @return 返回出了v之外的另一个顶点w
     */
    public int other(int v) {
        if (v == this.v) return this.w;
        else if (w == this.v) return this.v;
        else throw new NoSuchElementException();
    }

    @Override
    public int compareTo(Edge e) {
        if (weight() < e.weight()) return -1;
        else if (weight() > e.weight()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
