public class BST<E extends Comparable<E>> {
    class Node {
        E data;
        Node left, right, parent;
        Node(E data) { this.data = data; }
    }

    Node root;
    int size;

    public boolean isEmpty() { return root == null; }

    public boolean add(E elt) {
        if (root == null) {
            root = new Node(elt);
            size++;
            System.out.println(String.format("Adding %s to empty tree", elt));
            return true;
        }
        Node tnd = root, pnd = null;
        while (tnd != null) {
            int c = elt.compareTo(tnd.data);
            if (c == 0) {
                System.out.println(String.format("%s is already in tree, cannot insert", elt));
                return false;
            }
            pnd = tnd;
            if (c < 0) {
                System.out.println(String.format("%s is less than %s, continuing into the left subtree", elt, tnd.data));
                tnd = tnd.left;
            } else {
                System.out.println(String.format("%s is greater than %s, continuing into the right subtree", elt, tnd.data));
                tnd = tnd.right;
            }
        }
        Node nnd = new Node(elt);
        nnd.parent = pnd;
        if (elt.compareTo(pnd.data) < 0) pnd.left = nnd;
        else pnd.right = nnd;
        size++;
        return true;
    }

    public boolean contains(E elt) {
        Node tnd = root;
        while (tnd != null) {
            int c = elt.compareTo(tnd.data);
            if (c == 0) return true;
            if (c < 0) tnd = tnd.left;
            else tnd = tnd.right;
        }
        return false;
    }

    public E remove(E elt) {
        Node tnd = root;
        while (tnd != null) {
            int c = elt.compareTo(tnd);
            if (c == 0) break;
            if (c < 0) tnd = tnd.left;
            else tnd = tnd.right;
        }
        if (tnd == null) return null;
        Node replacement = null;
        if (tnd.left == null && tnd.right == null) {// target node is a leaf
            replacement = null;
        } else if (tnd.left == null || tnd.right == null) { // target node has only one child
            replacement = tnd.left != null ? tnd.left : tnd.right;
        }

        if (elt.data.compareTo(tnd.parent.data) < 0) tnd.parent.left = replacement;
        else tnd.parent.right = replacement;
        if (replacement != null) replacement.parent = tnd.parent;
        size--;
        return tnd.data;
            
    }

    public static void main(String[] args) {
        BST<String> stree = new BST<>();
        // String[] words = {"hello", "goodbye", "cat", "dog", "desk", "coffee"};
        String[] words = {"dog", "coffee", "goodbye", "cat", "desk", "hello", "cat"};
        for (String w : words) {
            stree.add(w);
        }
        String testWord = "cat";
        System.out.println(String.format("stree %s %s", stree.contains(testWord) ? "contains" : "does not contain", testWord));
        testWord = "chair";
        System.out.println(String.format("stree %s %s", stree.contains(testWord) ? "contains" : "does not contain", testWord));
    }

}