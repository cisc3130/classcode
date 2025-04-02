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
        //     8   10  20   30
        return t;
    }

    public void printTree(BTNode nd) {
        if (nd == null) return;
        printTree(nd.left);
        System.out.print(nd.data + " ");
        printTree(nd.right);
    }

    public int getSize(BTNode nd) {
        if (nd == null) return 0;
        int leftSize = getSize(nd.left);
        int rightSize = getSize(nd.right);
        return leftSize + rightSize + 1;
    }

    public boolean contains(BTNode nd, E elt) {
        if (nd == null) return false;
        return nd.data.equals(elt)         // is in the current node
            || contains(nd.left, elt)       // is in the left subtree
            || contains(nd.right, elt);      // is in the right subtree
    }

    public int depth(BTNode nd) {
        if (nd == null) return -1;
        return Math.max(depth(nd.left), depth(nd.right)) + 1;
    }

    public int countMatches(BTNode nd, E elt) {
        if (nd == null) return 0;
        int leftMatches = countMatches(nd.left, elt);
        int rightMatches = countMatches(nd.right, elt);
        int currentMatch = nd.data.equals(elt) ? 1 : 0;
        return leftMatches + rightMatches + currentMatch;
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

    



    public static void main(String[] args) {
        BinaryTree<Integer> t = buildTree();
        System.out.print("Inorder traversal of the tree: ");
        t.printTree(t.root);
        System.out.println(); // For a new line after printing the tree
        // Output should be: 8 7 10 18 20 25 30
        int depth = t.getTreeDepth(t.root);
        System.out.println("Depth of the tree: " + depth);
    }


}