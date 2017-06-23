package com.github.joemsu.algorithms.sort;

/**
 * @author joemsu 2017-06-22 下午8:51
 *         自底向上的归并排序(当数组长度为2的幂时， 与自上向下的相同)
 *         时间复杂度：NlogN, 空间复杂度：N
 *         因为需要辅助数组，所以不是原地排序
 *         可以保留数组中重复元素的相对位置，是稳定的，
 */
public class MergeBUSort extends BaseSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] temp = new Comparable[a.length];
        //注意N不一定为2的幂次
        for(int sz = 1; sz < N; sz += sz) {
            for(int i = 0; i < N - sz ; i += sz + sz) {
                //最后一个子数组的大小只有在数组的大小是sz的偶数倍的时候才会等于sz，不然会比sz小，所有要比较与数组长度的大小，
                // 因为数组长度不一定为2的幂次
                //另外i从0开始，所以要 i + sz - 1，
                MergeSort.merge(a, temp, i, i + sz -1 , Math.min(i + sz +sz - 1, N -1));
            }
        }
    }
}