package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;

/**
 * @author joemsu 2017-07-03 下午12:15
 *         基于队列的Bellman-Ford最短路径算法
 *         在任意含有V个顶点的加权有向图中给定起点s，从s无法到达任何负权重环，以下算法能解决其中单点的最短路径问题：
 *         将dist[s]初始化为0，其他distTo[]元素初始化为无穷大，以任意顺序放松有向图的所有变，重复v轮
 */
public class BellmanFordShortestPath {

    private double[] distTo;

    /**
     * 从起点到某个顶点的最后一条边
     */
    private DirectedEdge[] edgeTo;

    private boolean[] onQ;

    /**
     * 存放正在被放松的顶点
     */
    private Queue<Integer> queue;

    /**
     * relax()调用的次数
     */
    private int cost;

    /**
     * edgeTo中是否有负权重环
     */
    private Iterable<DirectedEdge> cycle;

    public BellmanFordShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.v()];
        edgeTo = new DirectedEdge[G.v()];
        onQ = new boolean[G.v()];
        queue = new Queue<>();
        for (int i = 0; i < G.v(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adjE(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] = e.getWeight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            //如果所有的边放松V轮后，当且仅当队列非空时有向图中才存在从起点可达的负权重环
            if (cost++ % G.v() == 0) {
                findNegativeCycle();
            }
        }
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    private void findNegativeCycle() {
        //:TODO
    }
}
