public class BST<E extends Comparable<E>> {
    class Node {
        E data;
        Node left, right, parent;
        Node(E data) { this.data = data; }
    }

    Node root;

    public boolean add(E elt) {
        Node nnd = new Node(elt);
        if (root == null) {
            root = nnd;
            return true;
        }
        Node tnd = root;
        while (!tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0)  {  // elt is smaller than tnd.data
                if (tnd.left == null) {
                    tnd.left = nnd;
                    nnd.parent = tnd;
                    return true;
                }
                tnd = tnd.left;
            } else {                             // elt is larger than tnd.data
                if (tnd.right == null) {
                    tnd.right = nnd;
                    nnd.parent = tnd;
                    return true;
                }
                tnd = tnd.right;
            }
        }
        return false;
    }

    public boolean contains(E elt) {
        Node tnd = root;
        while (tnd != null && !tnd.data.equals(elt)) {
            if (elt.compareTo(tnd.data) < 0) {
                tnd = tnd.left;
            } else {
                tnd = tnd.right;
            }
        }
        return tnd != null;
    }


    public static void main(String[] args) {
        int [] arr = { 7, 1, 8, 9, 5, 1, 3, 16 };
        BST<Integer> tree = new BST<>();
        for (int i : arr) {
            tree.add(i);
        }
        System.out.println(tree.contains(9));
        System.out.println(tree.contains(11));
    }
}