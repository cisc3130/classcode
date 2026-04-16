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
        // if tree is empty
        if (root == null) {
            Node nnd = new Node(elt);
            root = nnd;
            size++;
            return true;
        }

        // tree is not empty
        // begin search
        Node tnd = root;
        while (true) {
            int c = elt.compareTo(tnd.data);
            if (c == 0) {       // the two values are equal
                return false;
            } 
            if (c < 0) {        // the value to be inserted is smaller than the value of the node
                if (tnd.left ==  null) {
                    tnd.addLeftChild(new Node(elt));
                    size++;
                    return true;
                } else {
                    tnd = tnd.left;
                }
            } else {            // the value to be inserted is larger than the value of the node
                if (tnd.right == null) {
                    tnd.addRightChild(new Node(elt));
                    size++;
                    return true;
                } else {
                    tnd = tnd.right;
                }
            }
        }
        
    }

    public boolean contains(E elt) {
        Node tnd = root;
        while (tnd != null && !tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0) {      // the target element is smaller than the current node's data
                tnd = tnd.left;
            } else {                    // the target element is larger than the current node's data
                tnd = tnd.right;
            }
        }
        return tnd != null;             // tnd will be null if elt is not in the tree
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
            if (elt.compareTo(tnd.data) < 0) tnd = tnd.left;
            else tnd = tnd.right;
        }
        if (tnd == null) return false;
        Node rnd;
        if (tnd.left == null && tnd.right == null) rnd = null;
        else if (tnd.left == null) {
            rnd = tnd.right;
        } else if (tnd.right == null) {
            rnd = tnd.left;
        } else {
            // tnd has two children. find the leftmost element in its right subtree
            rnd = tnd.right;    // move into the right subtree
            while (rnd.left != null) rnd = rnd.left;        // move as left as you can in the right subtree
        }

        // link rnd to tnd's parent
        if (rnd != null) rnd.parent = tnd.parent;
        if (tnd.parent != null) {
            if (tnd.data.compareTo(tnd.parent.data) < 0) {      // tnd is a left child
                tnd.parent.left = rnd;
            } else {            // tnd is a right child
                tnd.parent.right = rnd;
            }
        } else {
            root = rnd;
        }

        // link rnd to tnd's left child                 // THIS IS WHAT WE ARE UP TO 4/16
        if (rnd != null && rnd.left == null) {
            rnd.left = tnd.left;
            tnd.left.parent = rnd;
        }
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