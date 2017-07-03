package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;
import com.github.joemsu.algorithms.sort.MinPriorityQueues;

/**
 * @author joemsu 2017-07-02 下午2:39
 *         最小生成树的Kruskal算法
 *         所需空间:E,所需时间ElogE
 *         主要思路就是先得到一个个由最短路径组成的森林， 然后再通过是否连通来连接起来
 */
public class KruskalMST {

    private MinPriorityQueues<Edge> pq;

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        int n = 1;
        pq = new MinPriorityQueues<>();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        for (Edge e : G.edges())
            pq.insert(e);
        while (n < G.V() && !pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            //已经连通了 直接跳过
            if(uf.connected(v, w)) continue;
            uf.connected(v, w);
            mst.enqueue(e);
        }
    }
}
