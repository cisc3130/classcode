import java.util.*;

public class BSTIterator<E extends Comparable<E>> implements Iterator<E> {

    BST<E>.Node nd;

    protected BSTIterator(BST<E>.Node nd) {
        this.nd = nd;
    }

    protected BST<E>.Node whatIsNext() {
        if (nd == null) return null;
        BST<E>.Node nextNode;
        if (nd.right != null) {
            nextNode = nd.right;
            while (nextNode.left != null) nextNode = nextNode.left;
            return nextNode;
        }
        else {
            nextNode = nd;
            while (nextNode.parent != null && nextNode.parent.right == nextNode) nextNode = nextNode.parent;
            // now nextNode is a left child
            nextNode = nextNode.parent;             // this is the next node
            return nextNode;
        }
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