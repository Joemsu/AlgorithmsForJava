package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;
import com.github.joemsu.algorithms.util.In;
import com.github.joemsu.algorithms.util.StdOut;

import java.util.Stack;

/**
 * @author joemsu 2017-06-30 下午2:03
 *         广度优先搜索路径（从顶点a到达顶点b的最短路径是什么）
 *         基本思想就是从s开始，在所有由一条边开始就可以到达的顶点中找v，如果没有找到的话就在两条边的所有顶点中找v
 */
public class BreadthFirstPath {

    private boolean[] marked;

    private final int s;

    private int[] edgeTo;

    private Queue<Integer> queue;

    public BreadthFirstPath(Graph graph,int s) {
        this.s = s;
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int i : graph.adj(v)) {
                if (!marked[i]) {
                    marked[i] = true;
                    edgeTo[i] = v;
                    queue.enqueue(i);
                }
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
     * Unit tests the {@code DepthFirstPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        BreadthFirstPath dfs = new BreadthFirstPath(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.path(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }


}
