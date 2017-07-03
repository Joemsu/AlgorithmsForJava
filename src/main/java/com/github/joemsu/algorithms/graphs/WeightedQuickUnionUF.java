package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-06-19 下午4:06
 *         加权find
 */
public class WeightedQuickUnionUF {

    private int[] id;

    private int count;

    private int[] sz;

    public WeightedQuickUnionUF(int count) {
        this.count = count;
        id = new int[count];
        sz = new int[count];
        for(int i = 0; i < count; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public void union(int p, int q) {
        int tempP = find(p);
        int tempQ = find(q);
        if(tempP == tempQ) return;
        if (sz[tempP] > sz[tempQ]) {
            id[tempQ] = tempP;
            sz[tempP] += sz[tempQ];
        } else {
            id[tempP] = tempQ;
            sz[tempQ] += sz[tempP];
        }
        count--;
    }
}
