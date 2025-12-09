public class RedBlackTree<E extends Comparable<E>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        E value;
        Node left, right, parent;
        boolean color;

        Node(E value) {
            this.value = value;
            this.color = RED; // New nodes are red by default
        }
    }

    private Node root;

    // Left rotate around node x
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Fix the red-black tree properties after insertion
    private void fixInsert(Node node) {
        if (node == null) return;
        if (node.parent == null) {      // node is the root. Just color it black
            node.color = BLACK;
            return;
        }
        // node is not root
        // if the node's parent is black, no properties are violated
        if (node.parent.color == BLACK) return;
                // node's parent is red
        // case 1: uncle is red. Recolor the parent and uncle to BLACK, and grandparent to RED
        // then move up the tree to check for further violations
        Node parent = node.parent, grandparent = parent.parent;
        Node uncle = node.parent == grandparent.left ? grandparent.right : grandparent.left;
        if (uncle.color == RED) {
            uncle.color = BLACK;
            parent.color = BLACK;
            grandparent.color = RED;
            fixInsert(grandparent);
        } else if (node.parent == grandparent.left && node == parent.right) {
            // case 2: node is right child of left parent
            leftRotate(node.parent);
            fixInsert(node.left);
        } else if (node.parent == grandparent.right && node == parent.left) {
            // case 3: node is left child of right parent
            // right rotate around parent
            rightRotate(node.parent);
            fixInsert(node.right);
        } else {
            // case 4: node is left child of left parent or right child of right parent
            // recolor parent to BLACK and grandparent to RED
            parent.color = BLACK;
            grandparent.color = RED;
            if (node == parent.left) {
                rightRotate(grandparent);
            } else {
                leftRotate(grandparent);
            }

        }
    }

    public boolean contains(E elt) {
        Node current = root;
        while (current != null) {
            int cmp = elt.compareTo(current.value);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public boolean add(E elt) {
        Node newNode = new Node(elt);
        if (root == null) {
            root = newNode;
            root.color = BLACK; // Root must be black
            return true;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = elt.compareTo(current.value);
            if (cmp == 0) {
                return false; // Duplicate value
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;
        if (elt.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsert(newNode);
        return true;
    }
