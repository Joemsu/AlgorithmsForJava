package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-23 上午10:32
 *         堆排序
 *         时间复杂度：NlogN， 空间复杂度： 1
 *         为原地排序 不需要额外的空间，
 *         不可以保留数组中重复元素的相对位置，是不稳定的，
 */
public class HeapSort extends BaseSort {

    public static Comparable[] sort(Comparable[] comparables) {
        int n = comparables.length;
        //我们只需要扫描n/2的元素就能拿到最大元素，跳过了大小为1的子堆
        int i = n / 2;
        while (i >= 1) {
            sink(comparables, i--, n);
        }
        //每次把最大的放到底部，知道堆到达只有1个元素位置
        while (n > 1) {
            swap(comparables, 1, n--);
            sink(comparables, 1, n);
        }
        return comparables;
    }

    /**
     * @param i 由上至下的堆有序化（下沉）
     */
    public static void sink(Comparable[] pq, int i, int N) {
        while (2 * i <= N) {
            int k = 2 * i;
            if (k < N && less(pq, k, k + 1))
                k++;
            if (less(pq, k, i))
                break;
            swap(pq, i, k);
            i = k;
        }
    }

    /**
     * @param pq pq
     * @param i  i
     * @param j  j
     * @return 正常的堆是从1 开始的，这里由于从0 开始所以要 -1，交换也是
     */
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    /**
     * 交换两个元素的位置
     *
     * @param i i
     * @param j j
     */
    public static void swap(Comparable[] pq, int i, int j) {
        Comparable temp = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = {3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
        String[] c = "M E R G E S O R T E X A M P L E".split(" ");
        HeapSort heapSort = new HeapSort();
        System.out.println(Arrays.toString(heapSort.sort(a)));
    }
}
