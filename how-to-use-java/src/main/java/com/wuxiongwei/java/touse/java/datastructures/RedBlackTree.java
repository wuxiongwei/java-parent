package com.wuxiongwei.java.touse.java.datastructures;

/**
 * 此文件包含红黑树的实现。RB 树是一种特殊的二进制树类型。自行平衡的树，以保持操作对数。
 */
public class RedBlackTree<T extends Comparable<T>> implements Iterable<T> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public class Node {

        // 节点颜色，默认所有节点颜色都是红色。
        public boolean color = RED;

        // 节点持有的值或是数据
        public T value;

        // 节点的左右父亲节点
        public Node left, right, parent;

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    // 跟节点
    public Node root;

    // 跟踪树内的节点数。
    private int nodeCount = 0;

    // 返回树内的节点数。
    public int size() {
        return nodeCount;
    }

    // 返回数是否是空的
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T value) {

        Node node = root;

        if (node == null || value == null) return false;

        while (node != null) {

            // 将当前值与节点中的值进行比较。
            int cmp = value.compareTo(node.value);

            // 挖到左边的子树。
            if (cmp < 0) node = node.left;

            //挖入右子树。
            else if (cmp > 0) node = node.right;

            //在树中找到值。
            else return true;
        }

        return false;
    }

    public boolean insert(T value) {

        if (value == null) throw new IllegalArgumentException();

        // 无根节点。
        if (root == null) {
            root = new Node(value, null);
            insertionRelabel(root);
            nodeCount++;
            return true;
        }

        for (Node node = root; ; ) {

            int cmp = value.compareTo(node.value);

            // 左子树。
            if (cmp < 0) {
                if (node.left == null) {
                    node.left = new Node(value, node);
                    insertionRelabel(node.left);
                    nodeCount++;
                    return true;
                }
                node = node.left;

            // 又子树。
            } else if (cmp > 0) {
                if (node.right == null) {
                    node.right = new Node(value, node);
                    insertionRelabel(node.right);
                    nodeCount++;
                    return true;
                }
                node = node.right;

            // 我们尝试插入的值已存在于树中。
            } else return false;
        }
    }

    private void insertionRelabel(Node node) {

        Node parent = node.parent;

        // 没有父亲说明node是跟节点 并标记黑色
        if (parent == null) {
            node.color = BLACK;
            root = node;
            return;
        }

        Node grandParent = parent.parent;
        if (grandParent == null) return;

        //红黑树不变已经满足。
        if (parent.color == BLACK || node.color == BLACK) return;

        boolean nodeIsLeftChild = (parent.left == node);
        boolean parentIsLeftChild = (parent == grandParent.left);
        Node uncle = parentIsLeftChild ? grandParent.right : grandParent.left;
        boolean uncleIsRedNode = (uncle == null) ? BLACK : uncle.color;

        if (uncleIsRedNode) {

            parent.color = BLACK;
            grandParent.color = RED;
            uncle.color = BLACK;

//            此时，父节点为红色，新子节点也是如此。
//            我们需要以某种方式重新平衡，因为没有两个红色节点可以彼此相邻。
        } else {

            // 父节点是左子节点。
            if (parentIsLeftChild) {

                // Left-left case.
                if (nodeIsLeftChild) {
                    grandParent = leftLeftCase(grandParent);

                    // Left-right case.
                } else {
                    grandParent = leftRightCase(grandParent);
                }

                // Parent node is a right child.
            } else {

                // Right-left case.
                if (nodeIsLeftChild) {
                    grandParent = rightLeftCase(grandParent);

                    // Right-right case.
                } else {
                    grandParent = rightRightCase(grandParent);
                }
            }
        }

        insertionRelabel(grandParent);
    }

    private void swapColors(Node a, Node b) {
        boolean tmpColor = a.color;
        a.color = b.color;
        b.color = tmpColor;
    }

    private Node leftLeftCase(Node node) {
        node = rightRotate(node);
        swapColors(node, node.right);
        return node;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotate(node.left);
        return leftLeftCase(node);
    }

    private Node rightRightCase(Node node) {
        node = leftRotate(node);
        swapColors(node, node.left);
        return node;
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotate(node.right);
        return rightRightCase(node);
    }

    private Node rightRotate(Node parent) {

        Node grandParent = parent.parent;
        Node child = parent.left;

        parent.left = child.right;
        if (child.right != null) child.right.parent = parent;

        child.right = parent;
        parent.parent = child;

        child.parent = grandParent;
        updateParentChildLink(grandParent, parent, child);

        return child;
    }

    private Node leftRotate(Node parent) {

        Node grandParent = parent.parent;
        Node child = parent.right;

        parent.right = child.left;
        if (child.left != null) child.left.parent = parent;

        child.left = parent;
        parent.parent = child;

        child.parent = grandParent;
        updateParentChildLink(grandParent, parent, child);

        return child;
    }

//    有时，父节点的左侧或右侧子节点发生更改，需要更新父节点的引用以指向新子节点。
    private void updateParentChildLink(Node parent, Node oldChild, Node newChild) {
        if (parent != null) {
            if (parent.left == oldChild) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        }
    }

    //帮助程序方法查找最左侧的节点（具有最小值）*
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    //帮助程序方法查找最右侧的节点（具有最大值）*
    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    // 返回迭代器以按顺序遍历树。
    @Override
    public java.util.Iterator<T> iterator() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {

        int[] values = {5, 8, 1, -4, 6, -2, 0, 7};
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        for (int v : values) rbTree.insert(v);

        System.out.printf("RB tree contains %d: %s\n", 6, rbTree.contains(6));
        System.out.printf("RB tree contains %d: %s\n", -5, rbTree.contains(-5));
        System.out.printf("RB tree contains %d: %s\n", 1, rbTree.contains(1));
        System.out.printf("RB tree contains %d: %s\n", 99, rbTree.contains(99));
    }
}
