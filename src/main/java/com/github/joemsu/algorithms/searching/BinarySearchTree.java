package com.github.joemsu.algorithms.searching;

import java.util.NoSuchElementException;

/**
 * @author joemsu 2017-06-24 下午10:02
 *         二叉查找树
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：N， 插入:N
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：1.39lgN 插入：1.39lgN
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        private K key;

        private V value;

        private Node left, right;

        private int n;

        public Node(K key, V value, Node left, Node right, int n) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.n;
    }

    /**
     *
     * @param key 键
     * @return 可以使用递归的方法，也可以使用非递归的方法，使用递归是为了后面的平衡二叉树准备
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException();
        return get(root, key);
    }

    /**
     *
     * @param key 键
     * @return 非递归实现的get方法。效率比递归高
     */
    public V getNotRecursion(K key) {
        Node temp = root;
        while (temp != null) {
            int compare = key.compareTo(temp.key);
            if(compare == 0) return temp.value;
            if(compare > 0) temp = temp.right;
            if(compare < 0) temp = temp.left;
        }
        return null;
    }

    public V get(Node node, K key) {
        if (node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare < 0) return get(node.left, key);
        else if (compare > 0) return get(node.right, key);
        else return node.value;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public void putNotRecursion(K key, V value) {
        Node z = new Node(key, value, null, null, 1);
        if (root == null) {
            root = z;
            return;
        }

        Node parent = null, x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.value = value;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left  = z;
        else         parent.right = z;
    }

    public Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, null, null, 1);
        int compare = key.compareTo(node.key);
        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K min() {
        return min(root).key;
    }

    public Node min(Node node) {
        if (node == null) throw new NoSuchElementException("该表为空");
        if (node.left != null) return min(node.left);
        return node;
    }

    public K max() {
        return max(root).key;
    }

    public Node max(Node node) {
        if (node == null) throw new NoSuchElementException("该表为空");
        if (node.right != null) return max(node.right);
        return node;
    }

    /**
     * @param key key
     * @return 小于等于key的最大键
     */
    public K floor(K key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;

    }

    public Node floor(Node node, K key) {
        if (node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare == 0) return node;
        if (compare < 0) return floor(node.left, key);
        //说明key在结点的右侧，即大于该结点的key
        Node t = floor(node.right, key);
        if (t != null) return t;
            //如果为空则说明当前的node就是小于等于keu的最大键
        else return node;
    }

    /**
     * @param k 排名为k， 排名从0开始
     * @return 排名为k的键
     */
    public K select(int k) {
        return select(root, k).key;
    }

    public Node select(Node node, int k) {
        if (node == null) return null;
        //该size就是这个node结点的排名，因为排名是从0开始的，所以排名为n，则前面有n个结点，即左结点包含了n个结点
        int size = size(node.left);
        if (size == k)
            return node;
        else if (size > k)
            return select(node.left, k);
        else
            return select(node.right, k - size - 1);
    }

    /**
     * @param key 键
     * @return 小于key的键的数量
     */
    public int rank(K key) {
        return rank(root, key);
    }

    public int rank(Node node, K key) {
        if (node == null) return 0;
        int compare = key.compareTo(node.key);
        //如果相等，则左侧结点的数量就是小于key的数量，因为二叉查找树的左侧结点都小于该结点
        if (compare == 0) return size(node.left);
            //key小于该结点就继续递归查找
        else if (compare < 0) return rank(node.left, key);
            //大于该结点就是该结点的左侧数量 加上自己本身 在加上右侧结点的数量
        else return 1 + size(node.left) + rank(node.right, key);
    }

    /**
     * 删除最小的结点
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            //如果要删除的结点的子结点是单个结点，则把子结点返回回去就好了
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            //如果是双子结点，则从该删除结点的右侧来找最小的子结点来代替，使之保持有序，因为要保持有序，则
            // 新的结点要比左侧的都大，且比右侧的都小，所以从右侧选择最小的来代替删除的结点就满足了（当然从
            // 左侧的结点树中找到最大的结点也可以满足）
            //先记录要删除的结点
            Node t = node;
            //找到右侧最小的结点，来变成node
            node = min(t.right);
            //对原来的右侧进行删除最小结点，把得到的新树给新的根结点右侧
            node.right = deleteMin(t.right);
            //左侧的子结点不变
            node.left = t.left;
        } else if (compare < 0) {
            return node.left = delete(node.left, key);
        } else {
            return node.right = delete(node.right, key);
        }
        //重新更新结点的数量
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new Queue<K>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<K> queue, K lo, K hi) {
        if (node == null) return;
        int compareLo = lo.compareTo(node.key);
        int compareHi = hi.compareTo(node.key);
        if (compareLo < 0) keys(node.left, queue, lo, hi);
        if (compareLo <= 0 && compareHi >= 0) queue.enqueue(node.key);
        if (compareHi > 0) keys(node.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();
        String[] strings = "S E A R C H E X A M P L E".split(" ");
        for (int i = 0; i < strings.length; i++) {
            String key = strings[i];
            st.put(key, i);
        }
        System.out.println(st.height());
    }
}
