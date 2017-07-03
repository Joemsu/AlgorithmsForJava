package com.github.joemsu.algorithms.graphs;

import java.util.Stack;

/**
 * @author joemsu 2017-07-01 上午10:10
 *         单点有向路径
 */
public class DepthFirstDirectedPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;

    public DepthFirstDirectedPaths(DirectedGraph directedGraph, int s) {
        marked = new boolean[directedGraph.v()];
        edgeTo = new int[directedGraph.v()];
        this.s = s;
        dfs(directedGraph, s);
    }

    private void dfs(DirectedGraph directedGraph, int s) {
        marked[s] = true;
        for (int w : directedGraph.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(directedGraph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int w = v; w != s; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }

}
