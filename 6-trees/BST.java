import java.util.*;

public class BST<E extends Comparable<E>> implements Iterable<E> {
    class Node {
        E data;
        Node left, right, parent;
        Node(E data) { this.data = data; }
        public void addLeftChild(Node child) {
            this.left = child;
            if (child != null) child.parent = this;
        }
        public void addRightChild(Node child) {
            this.right = child;
            if (child != null) child.parent = this;
        }
    }

    Node root;
    int size;

    public Iterator<E> iterator() {
        Node nd = root;
        while (nd.left != null) nd = nd.left;
        return new BSTIterator<E>(nd);
    }

    public boolean add(E elt) {
        if (root == null) {         // tree is empty
            Node nnd = new Node(elt);
            root = nnd;
            size = 1;
            return true;
        }

        Node tnd = root;
        while (!tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0) {      // elt is smaller than tnd.data: branch left
                if (tnd.left == null) {
                    Node nnd = new Node(elt);
                    tnd.addLeftChild(nnd);
                    size++;
                    return true;
                } else {
                    tnd = tnd.left;
                }
            } else {        // elt is larger than tnd.data: branch right
                if (tnd.right == null) {
                    Node nnd = new Node(elt);
                    tnd.addRightChild(nnd);
                    size++;
                    return true;
                } else {
                    tnd = tnd.right;
                }
            }
        }

        return false;

    }

    public boolean contains(E elt) {
        Node tnd = root;
        while (tnd != null) {
            int c = elt.compareTo(tnd.data);
            if (c == 0)    {        // current node contains elt
                return true;
            } else if (c < 0) {     // elt is smaller than data in current node; branch left
                tnd = tnd.left;
            } else {                // elt is larger than data in current node; branch right
                tnd = tnd.right;
            }
        }
        return false;
    }

    public boolean contains(Node nd, E elt) {
        if (nd == null) return false;
        int c = elt.compareTo(nd.data);
        if (c == 0) return true;
        if (c < 0) return contains(nd.left, elt);
        return contains(nd.right, elt);
    }

    public boolean remove(E elt) {
        Node tnd = root;
        while (tnd != null && !tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0) {
                tnd = tnd.left;
            } else {
                tnd = tnd.right;
            }
        }
        if (tnd == null) return false;      // elt is not in the tree

        Node rnd;
        if (tnd.left == null && tnd.right == null) {    // case 1: tnd is a leaf
            rnd = null;
        } else if (tnd.right == null) {        // case 2: tnd only has a left child
            rnd = tnd.left;
        } else if (tnd.left == null) {         // case 3: tnd only has a right child
            rnd = tnd.right;
        } else {            // case 4: tnd has two children
            // rnd will be the leftmost child in tnd's right subtree
            rnd = tnd.right;        // start rnd in tnd's right subtree
            while (rnd.left != null) {
                rnd = rnd.left;             // move it as left as possible
            }
        }

        size--;
            
        if (rnd == null) return true;

        // link up rnd to replace tnd
        // link rnd as the correct child of tnd.parent
        if (tnd == tnd.parent.left) {
            tnd.parent.left = rnd;
        } else {
            tnd.parent.right = rnd;
        }
        // relocate rnd's right child if necessary
        if (rnd.parent != tnd && rnd.right != null) {
            rnd.parent.left = rnd.right;
            rnd.right.parent = rnd.parent;
        }
        // if tnd had two children, reassign them as children of rnd
        if (rnd.parent != tnd) {
            rnd.left = tnd.left;
            rnd.left.parent = rnd;
            rnd.right = tnd.right;
            rnd.right.parent = rnd;
        }
        // link rnd to its parent
        rnd.parent = tnd.parent;

        return true;
    }





    public void print() { System.out.println(toString(root)); }

    public String toString() { return toString(root); }

    protected String toString(Node nd) {
        if (nd == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString(nd.left));
        sb.append(nd.data.toString());
        sb.append(toString(nd.right));
        return sb.toString();
    }

    protected boolean isBST(Node nd) {
        if (nd == null) return true;
        E maxLeft = max(nd.left), minRight = min(nd.right);
        if (maxLeft != null && maxLeft.compareTo(nd.data) > 0) return false;
        if (minRight != null && minRight.compareTo(nd.data) < 0) return false;
        if (!isBST(nd.left)) return false;
        if (!isBST(nd.right)) return false;
        return true;
    }

    protected E min(Node nd) {
        if (nd == null) return null;
        while (nd.left != null) nd = nd.left;
        return nd.data;
    }

    protected E max(Node nd) {
        if (nd == null) return null;
        while (nd.right != null) nd = nd.right;
        return nd.data;
    }


    public static void main(String[] args) {
        int [] arr = { 7, 1, 8, 8, 9, 5, 1, 3, 16 };
        BST<Integer> tree = new BST<>();
        for (int i : arr) {
            Integer ii = Integer.valueOf(i);
            tree.add(ii);
        }
        // System.out.println(tree.contains(9));
        // System.out.println(tree.contains(11));

        tree.print();
    }
}