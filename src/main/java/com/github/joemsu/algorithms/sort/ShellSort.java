package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-22 下午7:22
 *         希尔排序，基于插入排序的快速排序算法
 *         时间复杂度：~NlogN?  ~N^(6/5) 唯一无法确定的排序算法， 空间复杂度：1
 *         是原地排序，不需要额外的空间
 *         不可以保留数组中重复元素的相对位置，是不稳定的，
 */
public class ShellSort extends BaseSort {

    /**
     *
     * @param a 需要排序的数组
     * @return 与插入排序类似，不同的就是插入排序每次是和相邻的元素比较，希尔排序可以产生间隔为h的递增序列：1，4，13，40...
     * 形成互相独立的有序数组，优化性能。
     * 这样权衡了子数组的规模和有序性， 各个子数组都很短，然后排序之后的子数组都是部分有序的，可以用于大型数组、
     */
    public static Comparable[] sort(Comparable[] a) {
        int N = a.length, step = 1;
        while (step < N / 3)
            //1, 4, 13, 40, 121, 364...
            step = 3 * step + 1;
        while (step >= 1) {
            for (int i = step; i < N; i++) {
                for (int j = i; j >= step && less(a[j], a[j - step]); j-= step) {
                    exchange(a, j, j - step);
                }
            }
            step /= 3;
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] a = {4, 3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
        ShellSort selectionSort = new ShellSort();
        System.out.println(Arrays.toString(selectionSort.sort(a)));
    }
}
