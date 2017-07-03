package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.util.Bag;
import com.github.joemsu.algorithms.util.In;

import java.util.HashSet;
import java.util.Set;

/**
 * @author joemsu 2017-07-02 下午1:53
 *         加权无向图
 */
public class EdgeWeightedGraph {

    private int v;

    private int e;

    private Bag<Edge>[]  edges;

    private static final String NEWLINE = System.getProperty("line.separator");

    public EdgeWeightedGraph(int v) {
        edges = (Bag<Edge>[]) new Bag[v];
        this.v = v;
    }

    public EdgeWeightedGraph(In in) {

    }

    public int V() {
        return this.v;
    }

    public int E() {
        return this.e;
    }

    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        edges[v].add(edge);
        edges[w].add(edge);
    }

    public Iterable<Edge> adj(int v) {
        return edges[v];
    }

    /**
     *
     * @return 图的所有的边
     */
    public Iterable<Edge> edges() {
        Set<Edge> edgeSet = new HashSet<>();
        for(int v = 0; v < V(); v++) {
            for (Edge e : adj(v)) {
                edgeSet.add(e);
            }
        }
        return edgeSet;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " " + e + NEWLINE);
        for (int v = 0; v < v; v++) {
            s.append(v + ": ");
            for (Edge e : edges[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
