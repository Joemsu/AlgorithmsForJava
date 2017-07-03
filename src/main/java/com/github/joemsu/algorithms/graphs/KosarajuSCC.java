package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-07-01 上午10:13
 *         有向图的强连通性，根据数学证明，就是将有向图逆序之后，在获得其逆后序
 */
public class KosarajuSCC {

    private int[] id;

    private boolean[] marked;

    private int count;


    public KosarajuSCC(DirectedGraph directedGraph) {
        id = new int[directedGraph.v()];
        marked = new boolean[directedGraph.v()];
        DirectedGraph reverseDirectedGraph = directedGraph.reverse();
        DepthFirstOrder order = new DepthFirstOrder(reverseDirectedGraph);
        for (int v : order.reversePost()) {
            if (!marked[v]) {
                dfs(directedGraph, v);
                count++;
            }

        }
    }

    private void dfs(DirectedGraph directedGraph, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : directedGraph.adj(s)) {
            if (!marked[w]) {
                dfs(directedGraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }
}
