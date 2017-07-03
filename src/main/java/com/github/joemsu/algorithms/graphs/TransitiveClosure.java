package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-07-01 上午10:13
 *         顶点对的可达性,给定两个顶点对 查看是否连通
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(DirectedGraph directedGraph) {
        all = new DirectedDFS[directedGraph.v()];
        for(int i = 0 ; i < directedGraph.v(); i++) {
            all[i] = new DirectedDFS(directedGraph, i);
        }
    }

    public boolean rechable(int v, int w) {
        return all[v].marked(w);
    }
}
