package com.github.joemsu.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author joemsu 2017-06-22 下午9:08
 *         快速排序, 是最快的通用排序算法
 *         时间复杂度：NlogN, 空间复杂度: lgN
 *         是原地排序
 *         不可以保留数组中重复元素的相对位置，是不稳定的，
 *         TODO: 改进方法:
 *         (1）针对小数组，快速排序比插入排序慢，所以当数组在5- 15之间时， 可以使用插入排序，实现详见MergeSort
 *         (2) 取样大小设置为3，来决定用来切分数组的中位数，代替a[lo]
 */
public class QuickSort extends BaseSort {


    /**
     * 因为快速排序在切分不平衡时效率很低，所以通过随机来打乱，来保证效率
     *
     * @param a 打乱的随机数组
     */
    public static void shuffle(Object[] a) {
        if (a == null) throw new IllegalArgumentException("argument array is null");
        Random random = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n - i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static Comparable[] sort(Comparable[] a) {
        //打乱数组，增加运行效率
        shuffle(a);
        sort(a, 0, a.length - 1);
        return a;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
    }

    /**
     *
     * @param a 要切分的属猪
     * @param lo 低位
     * @param hi 高位
     * @return 默认选择lo为比较的元素，然后从低位中选择大于a[lo]的元素， 从高位中选择小于a[lo]的
     * 元素，直到低位和高位相遇，比较结束，最后把lo 和相遇的地方交换，则切分成两组，一组是小于lo的，一组是大于lo的
     * 在递归切分剩下的小数组
     */
    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            //找到比v大的索引，直到i和j相遇截止
            while (less(a[++i], v)) {
                if (i == hi)
                    break;
            }
            //找到比v小的索引，直到最后到lo截止，所以while里不需要break，没有找到的话最终会停在lo
            while (less(v, a[--j])) {

            }
            if(i >= j) break;
            exchange(a, i, j);

        }
        //交换lo 和j，使数组变成大于a[lo]和小于a[lo]的两个部分
        exchange(a, lo, j);
        return j;
    }


    public static void main(String[] args) {
        Integer[] a = {3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
        String[] c = "M E R G E S O R T E X A M P L E".split(" ");
        QuickSort quickSort = new QuickSort();
        System.out.println(Arrays.toString(quickSort.sort(a)));
    }
}
