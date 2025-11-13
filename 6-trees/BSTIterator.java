import java.util.*;

public class BSTIterator<E extends Comparable<E>> implements Iterator<E> {

    BST<E>.Node nd;

    protected BSTIterator(BST<E>.Node nd) {
        this.nd = nd;
    }

    protected BST<E>.Node whatIsNext() {
        BST<E>.Node nnd;
        if (nd.right != null) {         // nd has a right child: next is the leftmost child of its right subtree
            nnd = nd.right;
            while (nnd.left != null) nnd = nnd.left;
            return nnd;
        }
        // nnd has no right child: next is the parent of the first ancestor that is a left child
        nnd = nd;
        while (nnd.parent != null && nnd.parent.left != nnd) nnd = nnd.parent;
        return nnd.parent;
    }

    public boolean hasNext() {
        return whatIsNext() != null;
    }
    
    public E next() {
        E toReturn = nd.data;
        nd = whatIsNext();
        if (nd == null) throw new NoSuchElementException();
        return toReturn;
    }
}