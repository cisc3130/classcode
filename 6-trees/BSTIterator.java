import java.util.*;

public class BSTIterator<E extends Comparable<E>> implements Iterator<E> {

    BST<E>.Node nd;

    protected BSTIterator(BST<E>.Node nd) {
        this.nd = nd;
    }

    protected BST<E>.Node whatIsNext() {
        BST<E>.Node tnd;
        // if current nd has a right child, "next" is the leftmost 
        // element in its right subtree
        if (nd.right != null) {
            tnd = nd.right;
            while (tnd.left != null) tnd = tnd.left;   // move as left as you can
            return tnd;
        }
        // nd does not have a right child
        tnd = nd;
        while (tnd.parent != null && tnd == tnd.parent.right) {
            tnd = tnd.parent;               // move up until tnd is a left child
        }
        // tnd is a left child. its parent is either next or null
        return tnd.parent;
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