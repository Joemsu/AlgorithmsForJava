package com.github.joemsu.algorithms.graphs;

import java.util.Stack;

/**
 * @author joemsu 2017-07-01 上午10:11
 *         有向环检测,给定的有向图中寻找出有向环，并给出其中一条路径，在有向图里如果包含了有向环，如果是一个有优先级限制的问题，那么
 *         则无解
 */
public class DirectedCycle {

    private boolean[] marked;

    private int[] edgeTo;

    private Stack<Integer> cycle;

    private boolean[] onStack;

    public DirectedCycle(DirectedGraph directedGraph) {
        marked = new boolean[directedGraph.v()];
        edgeTo = new int[directedGraph.v()];
        onStack = new boolean[directedGraph.v()];
        for (int v = 0; v < directedGraph.v(); v++) {
            if (!marked[v]) dfs(directedGraph, v);
        }
    }

    private void dfs(DirectedGraph directedGraph, int s) {
        marked[s] = true;
        onStack[s] = true;
        for (int w : directedGraph.adj(s)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                marked[w] = true;
                edgeTo[w] = s;
                dfs(directedGraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = s; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(s);
            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
