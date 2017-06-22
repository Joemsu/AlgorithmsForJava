package com.github.joemsu.algorithms.sort;

/**
 * @author joemsu 2017-06-22 下午6:39
 *         排序的基本接口
 */
public interface BaseSort {

    /**
     * @param a        要执行交换的数组
     * @param exIndex  交换位置1
     * @param minIndex 交换位置2
     */
    default void exchange(Comparable[] a, int exIndex, int minIndex) {
        Comparable temp = a[exIndex];
        a[exIndex] = a[minIndex];
        a[minIndex] = temp;
    }

    /**
     *
     * @param a 比较的元素 a
     * @param b 比较的元素 b
     * @return 返回a是否小于b, 小于返回true， 大于返回false
     */
    default boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
