package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-06-30 下午2:55
 *         判断图是否是无环图
 */
public class Cycle {

    private boolean[] marked;

    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for(int i = 0; i < graph.V(); i++) {
            if(!marked[i])
                dfs(graph, i, i);
        }
    }

    private void dfs(Graph g, int v, int p) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w, v);
            //因为都是从起始点s开始出发，如果能够找到已经标记过的点，则说明可以构成环（因为从s可以走到w，而从v可以到w，v也是从s出发的）
            } else if(w != p) {
                hasCycle = true;
            }
        }
    }
}
