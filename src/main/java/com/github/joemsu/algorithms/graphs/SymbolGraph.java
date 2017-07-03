package com.github.joemsu.algorithms.graphs;

import com.github.joemsu.algorithms.searching.BinarySearchST;
import com.github.joemsu.algorithms.util.In;

/**
 * @author joemsu 2017-06-30 下午3:15
 *         符号图
 */
public class SymbolGraph {

    private BinarySearchST<String, Integer> st;

    private String[] keys;

    private Graph g;

    /**
     * @param filename 文件名
     * @param delim    分隔符
     *                 根据指定的filename的文件来构造图，然后使用delim来分隔顶点名
     */
    public SymbolGraph(String filename, String delim) {
        st = new BinarySearchST<>(10);
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        g = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i++) {
                g.addEdge(v, st.get(a[i]));
            }
        }

    }
}
