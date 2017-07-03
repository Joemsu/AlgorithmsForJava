package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;
import com.github.joemsu.algorithms.sort.MinPriorityQueues;

/**
 * @author joemsu 2017-07-02 下午2:05
 *         最小生成树的Prim算法的延迟实现
 *         所需空间： E, 所需时间： ElogE
 */
public class LazyPrimMST {

    private boolean[] marked;

    private Queue<Edge> queue;

    private MinPriorityQueues<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        pq = new MinPriorityQueues<>();
        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge min = pq.delMin();
            int v = min.either(), w = min.other(v);
            if(marked[v] && marked[w]) continue;
            queue.enqueue(min);
            if(!marked[w]) {
                visit(graph, w);
            }
            if (!marked[v]) {
                visit(graph, v);
            }
        }

    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if(!marked[e.other(v)])
                pq.insert(e);
        }
    }

}
