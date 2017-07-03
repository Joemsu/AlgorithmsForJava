package com.github.joemsu.algorithms.graphs;

import java.util.Stack;

/**
 * @author joemsu 2017-06-30 下午1:37
 *         深度优先搜索查找图中的路径（单点路径， 从s-v的路径）
 *         用于是否存在一个路径能从顶点a到达顶点b，
 */
public class DepthFirstPath {

    /**
     * 这个顶点上是否调用过dfs()
     */
    private boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    private int count;

    private final int s;

    public DepthFirstPath(Graph graph, int s) {
        marked = new boolean[graph.V()];
        this.s = s;
        edgeTo = new int[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        count++;
        for (int v : graph.adj(s)) {
            if (!marked[v]) {
                // 假设第一次访问v时，s是s-v的最后一条已知的路径
                edgeTo[v] = s;
                dfs(graph, v);
            }
        }
    }

    /**
     * @param v 顶点v
     * @return 是否存在从s到v的路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     *
     * @param v 顶点v
     * @return 从s到v的路径，如果不存在则返回null
     */
    public Iterable<Integer> path(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int w = v; w != s; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
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
