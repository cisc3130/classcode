import java.util.*;

public class Tree<E> {
    class TreeNode {
        E data;
        TreeNode parent;
        List<TreeNode> children;
        TreeNode(E data) {
            this.data = data;
            children = new java.util.LinkedList<Tree<E>.TreeNode>();
        }
        public void addChild(TreeNode nd) { children.add(nd); }
    }

    TreeNode root;

    public boolean isEmpty() { return root == null; }

    public static Tree<String> buildFoodTree() {
        Tree<String> foodTree = new Tree<>();

        Tree<String>.TreeNode food = foodTree.new TreeNode("Food");
        foodTree.root = food;

        Tree<String>.TreeNode animal = foodTree.new TreeNode("Animal");
        Tree<String>.TreeNode plant = foodTree.new TreeNode("Plant");
        food.addChild(animal);
        food.addChild(plant);

        Tree<String>.TreeNode roots = foodTree.new TreeNode("Roots");
        Tree<String>.TreeNode leaves = foodTree.new TreeNode("Leaves");
        Tree<String>.TreeNode fruit = foodTree.new TreeNode("Fruit");
        plant.addChild(roots);
        plant.addChild(leaves);
        plant.addChild(fruit);

        Tree<String>.TreeNode bird = foodTree.new TreeNode("Bird");
        Tree<String>.TreeNode fish = foodTree.new TreeNode("Fish");
        Tree<String>.TreeNode mammal = foodTree.new TreeNode("Mammal");
        animal.addChild(bird);
        animal.addChild(fish);
        animal.addChild(mammal);

        Tree<String>.TreeNode chicken = foodTree.new TreeNode("Chicken");
        Tree<String>.TreeNode cow = foodTree.new TreeNode("Cow");
        bird.addChild(chicken);
        mammal.addChild(cow);



        return foodTree;
    }

    public void printTree() {
        if (root == null) return;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.addLast(root);
        while (!unvisited.isEmpty()) {
            // remove the next node from unvisited
            TreeNode nd = unvisited.removeFirst();
            // ****visit****
            System.out.print(nd.data + " ");
            // add its children to unvisited
            for (TreeNode c : nd.children) unvisited.addLast(c);
        }
        System.out.println();
    }

    public boolean contains(E elt) {
        if (root == null) return false;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.addLast(root);
        while (!unvisited.isEmpty()) {
            // remove the next node from unvisited
            TreeNode nd = unvisited.removeFirst();
            // ****visit****
            if (nd.data.equals(elt)) return true;
            // add its children to unvisited
            for (TreeNode c : nd.children) unvisited.addLast(c);
        }
        return false;
    }

    public boolean contains(E elt, TreeNode nd) {
        // base case
        if (nd == null) return false;
        // V
        if (nd.data.equals(elt)) return true;
        for (TreeNode cnd : nd.children) {
            if (contains(elt, cnd)) return true;
        }
        return false;
    }

    public int size() {
        if (root == null) return 0;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.addLast(root);
        int count = 0;
        while (!unvisited.isEmpty()) {
            // remove the next node from unvisited
            TreeNode nd = unvisited.removeFirst();
            // ****visit****
            count++;
            // add its children to unvisited
            for (TreeNode c : nd.children) unvisited.addLast(c);
        }
        return count;
    }

    public static void main(String[] args) {
        Tree<String> foodTree = buildFoodTree();
        foodTree.printTree();

        System.out.println(foodTree.contains("Bird"));
        System.out.println(foodTree.contains("Salmon"));

        System.out.println(foodTree.size());
    }
}