package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-22 下午7:05
 *         插入排序
 *         时间复杂度：介于 N - N ^ 2之间（取决于输入元素的排列情况）， 空间复杂度： 1
 *         为原地排序 不需要额外的空间，
 *         可以保留数组中重复元素的相对位置，是稳定的，
 *         适用于小型的数组 以及 部分有序的数组交换！！！
 */
public class InsertionSort extends BaseSort {

    /**
     * @param a 要排序的元素
     * @return 从外循环开始的指定元素，每次比较和前一个的大小（这里排序按照小进行），然后进行交换，知道前一个元素比它小或者到1停止
     */
    public static Comparable[] sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] a = {3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
//        String[] c = "E A S Y Q U E S T I O N".split(" ");
        InsertionSort selectionSort = new InsertionSort();
        System.out.println(Arrays.toString(selectionSort.sort(a)));
    }
}
