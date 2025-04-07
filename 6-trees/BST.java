public class BST<E extends Comparable<E>> {
    class Node {
        E data;
        Node left, right, parent;
        Node(E data) { this.data = data; }
    }

    Node root;

    public boolean add(E elt) {
        Node tnd = root;
        while (tnd != null) {
            int c = tnd.data.compareTo(elt);
            if (c == 0) {           // the current node's data is equal to the one we're trying to add
                return false;       // can't add a duplicate
            }
            if (c < 0) {            // the current node's data is less than the one we're trying to add
                if (tnd.left == null) {     // empty spot: put the new node here
                    Node nnd = new Node(elt);
                    tnd.left = nnd;
                    nnd.parent = tnd;
                    return true;    
                } else {
                    tnd = tnd.left;     // else: continue looking in the left branch
                }
            } else {                // the current node's data is greater than the one we're trying to add
                if (tnd.right == null) {    // empty spot: put the new node here
                    Node nnd = new Node(elt);
                    tnd.right = nnd;
                    nnd.parent = tnd;
                    return true;
                } else {
                    tnd = tnd.right;    // else: continue looking in the left branch
                }
            }
        }
        // tnd is null: this means the tree is empty and the new node should be the root
        root = new Node(elt);;
        return true;
    }

    public boolean contains(E elt) {
        Node tnd = root;
        while (tnd != null) {
            int c = tnd.data.compareTo(elt);
            if (c == 0) {       // the current node's data is equal to the one we're looking for
                return true;
            }
            if (c < 0) {        // the current node's data is smaller than the one we're looking for
                tnd = tnd.right;
            } else {            // the current node's data is greater than the one we're looking for
                tnd = tnd.left;
            }
        }
        return false;
    }

    public boolean contains(Node nd, E elt) {
        if (nd == null) return false;
        int c = nd.data.compareTo(elt);
        if (c == 0) return true;
        if (c > 0) return contains(nd.left, elt);
        return contains(nd.right, elt);
    }

    public void print() { print(root); }

    protected void print(Node nd) {
        // base case
        if (nd == null) return;
        // recurse left (L)
        if (nd.left != null) print(nd.left);
        // visit (V)
        System.out.print(nd.data + " ");
        // recurse right (R)
        if (nd.right != null) print(nd.right);
    }

    protected boolean isBST(Node nd) {
        if (nd == null) return false;
        if (!isBST(nd.left)) return false;
        if (!isBST(nd.right)) return false;
        if (nd.data.compareTo(max(nd.left)) > 0) return false;
        if (nd.data.compareTo(min(nd.right)) < 0) return false;
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
        int [] arr = { 7, 1, 8, 9, 5, 1, 3, 16 };
        BST<Integer> tree = new BST<>();
        for (int i : arr) {
            tree.add(i);
        }
        // System.out.println(tree.contains(9));
        // System.out.println(tree.contains(11));

        tree.print();
    }
}