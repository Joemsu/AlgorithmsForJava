package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.util.Bag;
import com.github.joemsu.algorithms.util.In;
import com.github.joemsu.algorithms.util.StdOut;

/**
 * @author joemsu 2017-07-01 上午10:09
 *         有向图的单点和多点可达性
 */
public class DirectedDFS {

    /**
     * 是否被标记过
     */
    private boolean[] marked;

    public DirectedDFS(DirectedGraph directedGraph, int s) {
        marked = new boolean[directedGraph.v()];
        dfs(directedGraph, s);
    }

    /**
     * @param directedGraph 有向图
     * @param sources       给定的顶点集合
     *                      在G中找到从sources中的所有顶点可达的所有顶点
     */
    public DirectedDFS(DirectedGraph directedGraph, Iterable<Integer> sources) {
        marked = new boolean[directedGraph.v()];
        for (int goal : sources) {
            if (!marked[goal])
                dfs(directedGraph, goal);
        }

    }

    private void dfs(DirectedGraph G, int s) {
        marked[s] = true;
        for (int w : G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        DirectedGraph G = new DirectedGraph(in);

        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        DirectedDFS dfs = new DirectedDFS(G, sources);

        for (int v = 0; v < G.v(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
    }

}

