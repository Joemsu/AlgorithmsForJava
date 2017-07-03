package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-06-30 下午1:37
 *         深度优先搜索(单点连通性，即从s-v是否连通)
 */
public class DepthFirstSearch {

    /**
     * 这个顶点上是否调用过dfs()
     */
    private boolean[] marked;

    /**
     * 与s连同的顶点的数量
     */
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        count++;
        for (int v : graph.adj(s)) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    /**
     * @param v 顶点v
     * @return v和s是否是连通的
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * @return 与s连通的顶点的数量
     */
    public int count() {
        return count;
    }
}
