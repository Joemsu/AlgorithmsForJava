package com.github.joemsu.algorithms.sort;

/**
 * @author joemsu 2017-06-23 下午7:27
 *         Kendall Tau距离
 *         例子：0 3 1 6 2 5 4 和 1 0 3 6 4 2 5两个数组之间的距离是4， 因为
 *         0-1， 3-1，2-4，5-4这4对数字在两组排列的相对顺序不同，而其他数字的相对顺序相同。
 */
public class KendallTau {

    /**
     *
     * @param a 数组a
     * @param b 数组b
     * @return 实现思路就是
     * 1、对于单个数组一般的反序的大小，思路就是找出a[i] > a[j](其中i<j)的数量，通过选择排序、插入排序都可以做到，
     * 但是归并排序的性能最好
     * 2、对于两个数组，思路是先合称为1个，先参照a作为基准的数组，然后b进行比较,通过设置ain数组，我们将 i-> a[i]（通过i得到a[i])以及
     * ain[a[i]] - > i(通过ain数组的索引a[i]来获得对应的i),则相当于构建了一个以a为基础的数列，然后如果我们取对应另外
     * 一个数组的元素，比如b[]来作为映射的key，则它返回的结果相当于经过ainv[]的一个转换运算
     *
     */
    public static long distance(int[] a, int[] b) {
        int[] ain = new int[a.length];
        //获得以a为基准的数组，ain中的索引a[i]的各个值按照 0,1,2,3的顺序
        for (int i = 0; i < a.length; i++) {
            ain[a[i]] = i;
        }
        //把b作为key，完成转换，顺序打乱。可能为3,5,1,2，然后按照0，1，2，3来重新排序，就可以获得距离
        int[] bnew = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            bnew[i] = ain[b[i]];
        }
        return count(bnew);
    }

    private static long count(int[] bnew) {
        int[] temp = new int[bnew.length];
        long inversions = count(bnew, temp, 0, bnew.length - 1);
        return inversions;
    }

    private static long count(int[] bnew, int[] temp, int lo, int hi) {
        if (lo >= hi) return 0;
        int inversions = 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(bnew, temp, lo, mid);
        inversions += count(bnew, temp, mid + 1, hi);
        inversions += merge(bnew, temp, lo, mid, hi);
        return inversions;
    }

    private static int merge(int[] bnew, int[] temp, int lo, int mid, int hi) {
        System.arraycopy(bnew, lo, temp, lo, hi - lo + 1);
        int i = lo, j = mid + 1, inversions = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) bnew[k] = temp[j++];
            else if (j > hi) bnew[k] = temp[i++];
            //只有temp[i] >temp[j]的情况才满足逆序
            else if (temp[i] > temp[j]) {
                bnew[k] = temp[j++];
                /**
                 * 一旦前面的某个元素比后面元素大，则从该元素起到该子数组里所有的元素都和后面的元素构成逆序。
                 * 而如果我们把后面的元素放入到合并后的数组中时，这些逆序都被消除了。注意到的是，这里消除的
                 * 是若干个逆序而不止一个。比如说前面2, 4, 5, 7和后面数组里的1就构成了4个逆序，如果把1放
                 * 到归并的结果数组中，这些逆序就消除了。
                 */
                inversions += (mid - i + 1);
            } else
                bnew[k] = temp[i++];
        }
        System.out.println(lo + "," + mid + "," + hi + "," + inversions);
        return inversions;
    }

    public static void main(String[] args) {
        int[] a = {0, 3, 1, 6, 2, 5, 4};
        int[] b = {1, 0, 3, 6, 4, 2, 5};
        System.out.println(distance(a, b));
    }

}
