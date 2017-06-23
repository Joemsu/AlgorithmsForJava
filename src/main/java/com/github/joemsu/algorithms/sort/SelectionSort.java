package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-22 下午6:50
 *         选择排序（冒泡排序）
 *         时间复杂度:N^2, 空间复杂度:1
 *         属于原地排序，不需要额外的空间
 *         不可以保留数组中重复元素的相对位置，是不稳定的，
 */
public class SelectionSort extends BaseSort {

    /**
     *
     * @param a 要排序的元素
     * @return 内循环每次找到最大或者最小的元素，然后跟外循环的元素交换
     */
    public static Comparable[] sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[max], a[j]))
                    max = j;
            }
            exchange(a, max, i);
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] a = {3, 1, 6, 5, 19, 2, 10, 16, 7, 9, 8};
        SelectionSort selectionSort = new SelectionSort();
        System.out.println(Arrays.toString(selectionSort.sort(a)));
    }
}
