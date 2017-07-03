package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.util.Bag;
import com.github.joemsu.algorithms.util.In;

import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-07-01 上午10:14
 *         有向图model
 */
public class DirectedGraph {


    /**
     * 边数
     */
    private int e;

    /**
     * 顶点数目
     */
    private final int v;

    private Bag<Integer>[] adj;

    public DirectedGraph(int v) {
        this.v = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public DirectedGraph(In in) {
        //第一个输入的是顶点数目
        this(in.readInt());
        this.e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int v() {
        return this.v;
    }

    public int e() {
        return this.e;
    }

    /**
     * @param v 出发的顶点v
     * @param w 链接的顶点w
     */
    public void addEdge(int v, int w) {
        if(v > this.v || w > this.v || v < 0 || w < 0) throw new NoSuchElementException("顶点不存在!");
        adj[v].add(w);
        e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    /**
     *
     * @return 有向图的逆序
     */
    public DirectedGraph reverse() {
        DirectedGraph dg = new DirectedGraph(this.v);
        for(int i = 0; i < this.v; i++) {
            for (int w : adj(i)) {
                dg.addEdge(w, i);
            }
        }
        return dg;
    }
}
