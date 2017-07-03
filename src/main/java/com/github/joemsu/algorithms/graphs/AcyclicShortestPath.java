package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-07-03 上午11:55
 *         无环加权有向图的最短路径算法
 *         可解决无环加权有向图，与权的值是否为负数没有关系
 *         解决思路就是按照拓扑排序放松顶点，
 *         能在和E+V成正比的时间内解决无环加权有向图的最短路径问题
 *         将distTo初始化为Double.NEGATIVE_INFINITY, 并且在relax里将判断的条件反一下就可以求最长路径
 */
public class AcyclicShortestPath {

    private double[] distTo;

    private DirectedEdge[] edgeTo;

    public AcyclicShortestPath(EdgeWeightedDigraph G, int s) {

        distTo = new double[G.v()];
        edgeTo = new DirectedEdge[G.v()];
        for (int i = 1; i < G.v(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adjE(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
            }
        }
    }
}
