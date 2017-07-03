package com.github.joemsu.algorithms.graphs;

/**
 * @author joemsu 2017-06-19 下午3:59
 */
public class QuickUnion {

    private int[] id;

    private int count;

    public QuickUnion(int count) {
        this.count = count;
        id = new int[count];
        for(int i = 0; i < count; i++) {
            id[i] = i;
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
        id[p] = tempQ;
        count --;
    }

//    public static void main(String[] args) {
//        int N = StdIn.readInt();
//        QuickFind quickFind = new QuickFind(N);
//        int[] s = {4, 3, 3, 8, 6, 5, 9, 4, 2, 1, 5, 0, 7, 2, 6, 1};
//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//            if(quickFind.connected(p, q)) continue;
//            quickFind.union(p, q);
//            StdOut.println(quickFind.count() + "components");
//        }
//    }
}
