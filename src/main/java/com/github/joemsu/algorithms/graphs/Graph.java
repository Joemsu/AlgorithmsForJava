package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.util.Bag;
import com.github.joemsu.algorithms.util.In;

/**
 * @author joemsu 2017-06-30 下午1:10
 *         图
 */
public class Graph {

    /**
     * 顶点的数量
     */
    private int V;

    /**
     * 边的数量
     */
    private int E;

    /**
     * 存放与某顶点相邻的顶点
     */
    private Bag<Integer>[] adj;


    public Graph(int v) {
        System.out.println(v);
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        //第一个输入的是顶点数目
        this(in.readInt());
        this.E = in.readInt();
        System.out.println(E);
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * @return 顶点数
     */
    public int V() {
        return V;
    }

    /**
     * @return 边数
     */
    public int E() {
        return E;
    }

    /**
     * @param v 顶点v
     * @param w 顶点w
     *          向图中添加一条边v-w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * @param v 顶点v
     * @return 和v相邻的所有顶点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];

    }

    @Override
    public String toString() {
        return "Graph{}";
    }
}
