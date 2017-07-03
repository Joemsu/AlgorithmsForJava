package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.sort.IndexMinPriorityQueues;

import java.util.Stack;

/**
 * @author joemsu 2017-07-03 上午11:27
 *         最短路径的Dijkstra算法(可计算有向环)
 *         能解决边权重非负的加权有向图的单起点最短路径问题
 */
public class DijkstraShortestPath {

    private double[] distTo;

    private DirectedEdge[] pathTo;

    private IndexMinPriorityQueues<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.v()];
        pathTo = new DirectedEdge[G.v()];
        pq = new IndexMinPriorityQueues<>(G.v());
        for (int i = 1; i < G.v(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adjE(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                pathTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = pathTo[v]; e != null; e = pathTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
