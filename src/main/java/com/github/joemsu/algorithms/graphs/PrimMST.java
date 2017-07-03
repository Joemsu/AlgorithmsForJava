package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.sort.IndexMinPriorityQueues;

/**
 * @author joemsu 2017-07-02 下午2:23
 *         最小生成树的Prim算法
 *         所需空间： V, 所需时间： ElogV
 */
public class PrimMST {

    private boolean[] marked;

    private IndexMinPriorityQueues<Double> pq;

    private double[] distTo;

    private Edge[] edgeTo;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        pq = new IndexMinPriorityQueues<Double>(graph.V());
        pq.insert(0, 0.0);
        for(int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        while (!pq.isEmpty())
            visit(graph, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v) {
        //将v添加到树种
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            // v-w失效，两个都存在于树中 且已经达到最短了
            if(marked[w]) continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
}
