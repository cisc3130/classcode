public class BinaryTree<E> {
    class BTNode {
        E data;
        BTNode parent, left, right;
        BTNode(E data) { this.data = data; }
        public void addLeft(BTNode nd) {
            this.left = nd;
            nd.parent = this;
        }
        public void addRight(BTNode nd) {
            this.right = nd;
            nd.parent = this;
        }
    }

    BTNode root;

    public static BinaryTree<Integer> buildTree() {
        BinaryTree<Integer> t = new BinaryTree<>();
        t.root = t.new BTNode(18);
        BinaryTree<Integer>.BTNode nd7 = t.new BTNode(7);
        t.root.addLeft(nd7);
        BinaryTree<Integer>.BTNode nd25 = t.new BTNode(25);
        t.root.addRight(nd25);
        BinaryTree<Integer>.BTNode nd8 = t.new BTNode(8);
        nd7.addLeft(nd8); // 7's left child is 8
        BinaryTree<Integer>.BTNode nd10 = t.new BTNode(10);
        nd7.addRight(nd10); // 7's right child is 10
        BinaryTree<Integer>.BTNode nd20 = t.new BTNode(20);
        nd25.addLeft(nd20); // 25's left child is 20
        BinaryTree<Integer>.BTNode nd30 = t.new BTNode(30);
        nd25.addRight(nd30); // 25's right child is 30
        // The tree now looks like this:
        //          18
        //        /    \
        //       7      25
        //      / \    /  \
        //     6   10  20   30
        return t;
    }

    // LRV -> postorder traversal       6 10 7 20 30 25 18
    // VLR -> preorder traversal        18 7 6 10 25 20 30
    // LVR -> inorder traversal         6 7 10 18 20 25 30 



    public void printTree(BTNode nd) {
        if (nd == null) return;
        if (nd.left != null) printTree(nd.left);                     // L
        if (nd.right != null) printTree(nd.right);                    // R
        System.out.print(nd.data + " ");        // V
    }


    public int getSize(BTNode nd) {
        if (nd == null) return 0;
        if (nd.left == null && nd.right == null) return 1;
        return getSize(nd.left) + getSize(nd.right) + 1;
    }

    public boolean contains(BTNode nd, E elt) {
        if (nd == null) return false;
        return nd.data.equals(elt) || contains(nd.left, elt) || contains(nd.right, elt);
    }

    public int height(BTNode nd) {
        if (nd == null) return -1;
        return Math.max(height(nd.left), height(nd.right)) + 1;
    }

    public int countMatches(BTNode nd, E elt) {
        if (nd == null) return 0;
        int this_matches = nd.data.equals(elt) ? 1 : 0;
        return countMatches(nd.left, elt) + countMatches(nd.right, elt) + this_matches;
    }

    public int sumTree(BTNode nd) {
        if (nd == null) return 0;
        int sumOfLeftSubtree = sumTree(nd.left),
            sumOfRightSubtree = sumTree(nd.right);
        return nd.data + sumOfLeftSubtree + sumOfRightSubtree;
    }

    public Integer getMax(BTNode nd) {
        if (nd == null) return null;
        Integer maxValInLeftSubtree = getMax(nd.left),
            maxValInRightSubtree = getMax(nd.right);
        return Math.max(nd.data, Math.max(maxValInLeftSubtree, maxValInRightSubtree));
    }

    public BTNode copyTree(BTNode nd) {
        if (nd == null) return null;
        BTNode nnd = new BTNode(nd.data);
        nnd.left = copyTree(nd.left);
        nnd.right = copyTree(nd.right);
        if (nnd.left != null) nnd.left.parent = nnd;
        if (nnd.right != null) nnd.right.parent = nnd;
        return nnd;
    }

    



    public static void main(String[] args) {
        BinaryTree<Integer> t = buildTree();
        System.out.print("Inorder traversal of the tree: ");
        t.printTree(t.root);
        System.out.println(); // For a new line after printing the tree
        // Output should be: 8 7 10 18 20 25 30
        // int depth = t.getTreeDepth(t.root);
        // System.out.println("Depth of the tree: " + depth);

    }


}