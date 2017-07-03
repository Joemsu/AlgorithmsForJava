package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-07-03 上午11:14
 *         加权有向边
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

    /**
     * 起始顶点
     */
    private int v;

    /**
     * 终止顶点
     */
    private int w;

    /**
     * 权重
     */
    private double weight;


    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }

    @Override
    public int compareTo(DirectedEdge o) {
        if (getWeight() < o.getWeight()) return -1;
        else if (getWeight() > o.getWeight()) return 1;
        else  return 0;
    }
}
