package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;
import com.github.joemsu.algorithms.util.In;
import com.github.joemsu.algorithms.util.StdOut;

/**
 * @author joemsu 2017-06-30 下午2:40
 *         使用深度优先搜索找出图中所有的连通分量
 */
public class CC {

    private boolean[] marked;

    /**
     * 连通分量的个数
     */
    private int count;

    private int[] id;

    public CC(Graph graph) {
        id = new int[graph.V()];
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                count ++;
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        id[s] =  count;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    public int count() {
        return count;
    }

    /**
     *
     * @param v 顶点v
     * @return v所在顶点的连通分量标识符（ 0 - count() -1）
     */
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }


}
