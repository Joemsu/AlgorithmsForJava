package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-07-01 上午10:12
 *         拓扑排序
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(DirectedGraph directedGraph) {
        DirectedCycle cycle = new DirectedCycle(directedGraph);
        //要先保证图中不存在有向环
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(directedGraph);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     *
     * @return 拓扑排序的所有顶点
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     *
     * @return 图G是有向无环图吗
     */
    public boolean isDAG() {
        return order != null;
    }
}
