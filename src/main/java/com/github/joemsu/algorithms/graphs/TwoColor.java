package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-06-30 下午3:08
 *         图G是否是二分图
 */
public class TwoColor {

    private boolean[] marked;

    private boolean[] colored;

    private boolean isTwoColor = true;

    public TwoColor(Graph graph) {
        colored = new boolean[graph.V()];
        marked = new boolean[graph.V()];
        for(int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                bfs(graph, i);
            }
        }
    }


    private void bfs(Graph graph, int s) {
        marked[s] = true;
        colored[s] = true;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                colored[w] = !colored[s];
                bfs(graph, w);
            } else if(colored[w] == colored[s]) isTwoColor = false;
        }
    }

}
