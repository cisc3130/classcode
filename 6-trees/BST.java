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

    public boolean contains(Node nd, E elt) {       // return true if elt is in the subtree rooted in nd, otherwise false   
        if (nd == null) return false;       // base case
        // return nd.data.equals(elt) || contains(nd.left, elt) || contains(nd.right, elt);
        // ^ this is the code we wrote for a regular tree, not a binary search tree
        // it checks every single node in the tree to see if it matches elt: takes O(n)
        // since a bst keeps its elements in order, we don't need to do this
        // every node comparison knocks out half the search space
        // **** we only ever need to recurse on one branch ****: O(log n)
        int c = elt.compareTo(nd.data);
        if (c == 0) return true;
        if (c < 0) return contains(nd.left, elt);
        else return contains(nd.right, elt);
    }

    public boolean remove(E elt) {
        Node tnd = root;
        while (tnd != null && !tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0) tnd = tnd.left;
            else tnd = tnd.right;
        }
        if (tnd == null) return false;

        // identify a replacement node
        Node rnd;       
        if (tnd.left == null && tnd.right == null) rnd = null;      // case 1: target node is a leaf: replace with null
        else if (tnd.left == null) {            // case 2 and 3: target node has just one child: replace with that child
            rnd = tnd.right;
        } else if (tnd.right == null) {
            rnd = tnd.left;
        } else {        //  case 4: tnd has two children. find the leftmost element in its right subtree
            rnd = tnd.right;    // move into the right subtree
            while (rnd.left != null) rnd = rnd.left;        // move as left as you can in the right subtree
        }

        // link rnd to tnd's parent
        if (rnd != null) {
            if (tnd.left != null && tnd.right != null && rnd.right != null)  {           // rnd has a right child: must relink it 
                rnd.parent.left = rnd.right;                                             // before overwriting it and before moving rnd's parent
                rnd.right.parent = rnd.parent;
            }
            rnd.parent = tnd.parent;
        }
        if (tnd.parent != null) {
            if (tnd.data.compareTo(tnd.parent.data) < 0) {      // tnd is a left child
                tnd.parent.left = rnd;
            } else {            // tnd is a right child
                tnd.parent.right = rnd;
            }
        } else {            // tnd has no parent: it was the root
            root = rnd;
        }

        // case 4. tnd has 2 children that need a replacement parent
        if (tnd.left != null && tnd.right != null) {
            tnd.left.parent = rnd;
            rnd.left = tnd.left;                // rnd doesn't have a left child: it's the leftmost descendant
            if (rnd.parent != tnd) {
                tnd.right.parent = rnd;
                rnd.right = tnd.right;              // rnd doesn't have a right child: if it did, we moved it before
            }
        }

        size--;
        return true;
    }





    public void print() { System.out.println(toString(root)); }

    public String toString() { return toString(root); }

    protected String toString(Node nd) {        // return a string containing the elements of the tree in order
        if (nd == null) return "";
        String stringOfLeftSubtree = toString(nd.left);         // L
        String stringOfCurrentNode = nd.data.toString();        // V
        String stringOfRightSubtree = toString(nd.right);       // R
        return stringOfLeftSubtree + stringOfCurrentNode + stringOfRightSubtree;
    }

    protected boolean isBST(Node nd) {
        if (nd == null) return true;                                // empty tree is considered BST
        if (nd.left == null && nd.right == null) return true;       // leaves are considered BST
        // L, R, V
        if (!isBST(nd.left)) return false;
        if (!isBST(nd.right)) return false;
        if (nd.data.compareTo(max(nd.left)) < 0) return false;      // current nd is smaller than the max of its left subtree
        if (nd.data.compareTo(min(nd.right)) > 0) return false;     // curretn nd is larger than the min of its right subtree
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