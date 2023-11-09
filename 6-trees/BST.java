public class BST<E extends Comparable<E>> {
    class Node {
        E data;
        Node left, right, parent;
        Node(E data) { this.data = data; }
    }

    class Iterator {
        Node nd;
        Iterator(Node nd) { this.nd = nd; }
        public E next() {
            E retVal = nd.data;
            // if it has a right child, "next" is the smallest element in its right subtree
            if (nd.right != null) {
                nd = nd.right;
                while (nd.left != null) nd = nd.left;
            }
            // if it has no right, "next" is the parent of its first ancestor
            // that is a left child (including itself)
            else {
                while (nd.parent != null && nd == nd.parent.right) nd = nd.parent;
                nd = nd.parent; // this will either be the parent of the first ancestor that is a left child
                                // or null (if there is no ancestor that is a left child e.g. the node was the largest element in the tree)
            }
            return retVal;
        }
        public boolean hasNext() {
            return nd == null;
        }
    }

    Node root;
    int size;

    public Iterator iterator() {
        Node tnd = root;
        if (tnd == null) return new Iterator(null);
        while (tnd.left != null) tnd = tnd.left;
        return new Iterator(tnd);
    }

    public void printTree() {
        System.out.println("Printing the tree");
        Iterator it = iterator();
        System.out.println(it.nd.data);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

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
            int c = elt.compareTo(tnd.data);
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
        } else {        // target node has two children
            // replacement will be the smallest element in its right subtree
            replacement = tnd.right;
            while (replacement.left != null) {
                replacement = replacement.left;
            }
            replacement.left = tnd.left;
            if (replacement.parent != tnd) {        // if the replacement is the right child, then we're done
                if (replacement.right != null) {
                    replacement.parent.left = replacement.right;
                }
                replacement.right = tnd.right;
            }
        }

        // link tnd.parent.left or .right to replacement
        if (tnd.parent == null) root = replacement;
        else if (tnd.data.compareTo(tnd.parent.data) < 0) tnd.parent.left = replacement;
        else tnd.parent.right = replacement;
        // link replacement.parent to tnd.parent
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

        stree.add(testWord);
        System.out.println(String.format("stree %s %s", stree.contains(testWord) ? "contains" : "does not contain", testWord));
        stree.remove(testWord);
        System.out.println(String.format("stree %s %s", stree.contains(testWord) ? "contains" : "does not contain", testWord));


        stree.printTree();

    }

}