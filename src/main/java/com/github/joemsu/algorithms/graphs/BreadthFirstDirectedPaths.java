package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;

import java.util.Stack;

/**
 * @author joemsu 2017-07-01 上午10:10
 *         单点最短有向路径
 */
public class BreadthFirstDirectedPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;


    public BreadthFirstDirectedPaths(DirectedGraph directedGraph, int s) {
        marked = new boolean[directedGraph.v()];
        edgeTo = new int[directedGraph.v()];
        this.s = s;
        bfs(directedGraph, s);
    }

    private void bfs(DirectedGraph directedGraph, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : directedGraph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
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
