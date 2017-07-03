package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.Queue;

import java.util.Stack;

/**
 * @author joemsu 2017-07-01 上午10:11
 *         深度优先的顶点排序
 */
public class DepthFirstOrder {


    private boolean[] marked;
    /**
     * 所有顶点的前序排列
     */
    private Queue<Integer> pre;

    /**
     * 所有顶点的后序排列
     */
    private Queue<Integer> post;

    /**
     * 所有顶点的逆后序排列
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(DirectedGraph directedGraph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[directedGraph.v()];
        for (int v = 0; v < directedGraph.v(); v++) {
            if (!marked[v]) dfs(directedGraph, v);
        }
    }

    private void dfs(DirectedGraph directedGraph, int s) {
        pre.enqueue(s);
        marked[s] = true;
        for (int w : directedGraph.adj(s)) {
            if (!marked[w]) {
                marked[w] = true;
                dfs(directedGraph, w);
            }
        }
        post.enqueue(s);
        reversePost.push(s);
    }


    /**
     * @return dfs的调用顺序
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * @return 顶点遍历完成的顺序
     */
    public Iterable<Integer> post() {
        return post;
    }

    /**
     * @return 逆后序，也就是拓扑排序，将所有的顶点排序，
     * 使得所有的有向边均从排在前面的元素指向排在后面的元素(适用于有优先级的任务调度，有些任务要在某个任务完成前完成)
     */
    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
