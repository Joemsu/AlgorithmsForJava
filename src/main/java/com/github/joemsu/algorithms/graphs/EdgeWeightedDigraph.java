package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.util.Bag;

/**
 * @author joemsu 2017-07-03 上午11:18
 *         加权有向图
 */
public class EdgeWeightedDigraph extends  DirectedGraph{

    private int v;

    private int e;

    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        super(v);
        this.v = v;
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
    }

    public int v() {
        return this.v;
    }

    public int e() {
        return this.e;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        this.e++;
    }

    public Iterable<DirectedEdge> adjE(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for(int i = 0; i < v; i++) {
            for (DirectedEdge e : adj[v]) {
                edges.add(e);
            }
        }
        return edges;
    }

}
