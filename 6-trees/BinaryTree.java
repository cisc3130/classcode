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
        

    }

    public void printTree(BTNode nd) {
        if (nd == null) return;
        printTree(nd.left);
        System.out.print(nd.data);
        printTree(nd.right);
    }


}