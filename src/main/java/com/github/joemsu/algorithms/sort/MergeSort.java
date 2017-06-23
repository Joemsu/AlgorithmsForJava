package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-22 下午7:52
 *         自上而下的归并算法
 *         时间复杂度：NlogN, 空间复杂度：N
 *         因为需要辅助数组，所以不是原地排序
 *         可以保留数组中重复元素的相对位置，是稳定的，
 */
public class MergeSort extends BaseSort {

    private static final int CUTOFF = 7;  // cutoff to insertion sort

    public static Comparable[] sort(Comparable[] a) {
        Comparable[] temp = new Comparable[a.length];
        sort(a, temp, 0, a.length - 1);
        return a;
    }

    /**
     * 使用插入排序来排小数组
     * @param a 要排序的数组
     * @param lo 低位
     * @param hi 高位
     */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exchange(a, j, j-1);
    }

    public static void sort(Comparable[] a, Comparable[] temp, int lo, int hi) {
        //递归结束条件
        if (lo >= hi) return;
//        当长度小于7的时候 可以使用插入排序来提高速度，递归的方式对于小数组，太耗费性能
//        if (hi <= lo + CUTOFF) {
//            StdOut.printf("insert %d------%d\n", lo, hi);
//            insertionSort(dst, lo, hi);
//            return;
//        }
        int mid = lo + (hi - lo) / 2;
        sort(a, temp, lo, mid);
        sort(a, temp, mid + 1, hi);
//        配合上面的长度小于7，则使用插入排序使用
//        因为lo- mid 以及 mid - hi 都是已经排序完成的，如果说src[mid] < src[mid + 1] 说明顺序已经好了，就只需要循坏拷贝就行了
//        这里用System.arrarcopy()速度比循环拷贝快一点
//        if (less(src[mid], src[mid+1])) {
//            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
//            return;
//        }
        merge(a, temp, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] temp, int lo, int mid, int hi) {
        //比for循环拷贝快了10%-20%
        System.arraycopy(a, lo, temp, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (j > hi)
                a[k] = temp[i++];
            else if (i > mid)
                a[k] = temp[j++];
            else if (less(temp[i], temp[j]))
                a[k] = temp[i++];
            else
                a[k] = temp[j++];
        }

    }

    public static void main(String[] args) {
//        Integer[] a = {3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
        String[] c = "M E R G E S O R T E X A M P L E".split(" ");
        MergeSort selectionSort = new MergeSort();
        System.out.println(Arrays.toString(selectionSort.sort(c)));
    }
}
