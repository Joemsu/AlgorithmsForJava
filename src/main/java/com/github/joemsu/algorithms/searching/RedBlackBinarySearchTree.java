package com.github.joemsu.algorithms.searching;

/**
 * @author joemsu 2017-06-25 下午4:23
 *         红黑二叉树
 *         最坏情况下的运行时间增长数量级（N次插入后）： 查找：2lgN， 插入: 2lgN
 *         平均情况下的运行时间增长数量级（N次插入后）： 查找：1.00lgN 插入：1.00lgN
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> {

    /**
     * 树的根结点
     */
    private Node root;

    private static final boolean RED = true;

    /**
     * 约定空链接为黑色
     */
    private static final boolean BLACK = false;

    private class Node {

        private Key key;

        private Value value;

        private Node left, right;

        private boolean color;

        private int n;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.n = n;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color;
        //return node != null && node.color; 两个一样
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        //新的结点继承原来结点的颜色
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + 1 + size(h.right);
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = size(node.left) + 1 + size(node.right);
        return x;
    }

    /**
     * @param node 进行颜色转换的结点
     *             用于两个子节点都是红色的树
     */
    private void flipColors(Node node) {
        node.right.color = BLACK;
        node.left.color = BLACK;
        node.color = RED;
    }

    private int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void put(Key key, Value value) {
        put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, RED, 1);
        int compare = key.compareTo(node.key);
        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.value = value;
        //如果右子节点是红色的，而左子节点是黑色的，则左旋转
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        //如果左子节点是红色的，而右子节点是黑色的，则右旋转
        if (isRed(node.left) && !isRed(node.right)) node = rotateRight(node);
        //两个子节点都是红色的，则进行颜色转换
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        node.n = size(node.right) + 1 + size(node.left);
        return node;
    }

    public void deleteMin() {
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if(!isEmpty()) root.color = BLACK;
    }

    /**
     *
     * @param h
     * @return 假设结点h为红色，h.left和h.left.left都是黑色
     * 将h.left或者h.left的子结点之一变红
     */
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node deleteMin(Node h) {
        if(h.left == null) return null;
        if(!isRed(h.left) && !isRed(h.left.right))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node balance(Node h) {
        if(isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        //如果左子节点是红色的，而右子节点是黑色的，则右旋转
        if (isRed(h.left) && !isRed(h.right)) h = rotateRight(h);
        //两个子节点都是红色的，则进行颜色转换
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.n = size(h.right) + 1 + size(h.left);
        return h;

    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k - t - 1);
        else return node;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public int rank(Node node, Key key) {
        if (node == null) return 0;
        int compare = key.compareTo(node.key);
        if (compare < 0) return rank(node.left, key);
        else if (compare > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }




}
