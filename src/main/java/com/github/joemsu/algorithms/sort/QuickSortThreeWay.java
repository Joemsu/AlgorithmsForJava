package com.github.joemsu.algorithms.sort;

import java.util.Arrays;

/**
 * @author joemsu 2017-06-23 上午10:01
 *         三向切分快速排序
 *         针对大量重复的元素，如果一个元素全部重复的子数组就不需要再排序，但是快速排序会继续切分，所以有了三向切分快速排序
 *         时间复杂度：N - NlogN之间, 空间复杂度: lgN
 *         是原地排序
 *         不可以保留数组中重复元素的相对位置，是不稳定的，
 *         运行效率由概率保证，同时也取决于输入元素的分布情况
 */
public class QuickSortThreeWay extends BaseSort {

    public static Comparable[] sort(Comparable[] a) {
        QuickSort.shuffle(a);
        sort(a, 0, a.length - 1);
        return a;
    }

    /**
     *
     * @param a 待切分的数组
     * @param lo 低位
     * @param hi 高位
     * 使用 lt i gt 使得 lo-lt之间的元素都小于a[lo]， lt-i之间的元素都等于a[lo] gt-hi之间的元素都大于a[lo]
     * 当找到比a[lo]小的元素时，交换i 和lt，lt++ i++, 大于时 gt--, 等于时 i++, 循环结束就是i>gt,
     * 然后再递归 lo -> lt -1 和 gt +1 -> hi
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            //a[i] < v
            if(less(a[i], v))
                exchange(a, lt++, i++);
            //a[i] > v
            else if(less(v, a[i]))
                exchange(a, i, gt--);
            //a[i] = v
            else
                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = {3, 1, 6, 5, 19, 22, 2, 38, 10, 16, 7, 9, 8};
        String[] c = "M E R G E S O R T E X A M P L E".split(" ");
        QuickSortThreeWay quickSortThreeWay = new QuickSortThreeWay();
        System.out.println(Arrays.toString(quickSortThreeWay.sort(a)));
    }
}
