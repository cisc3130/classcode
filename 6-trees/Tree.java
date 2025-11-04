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
        public void addChild(TreeNode nd) { 
            children.add(nd); 
            nd.parent = this;
        }
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
        Deque<TreeNode> unvisited = new ArrayDeque<>();     // create a data structure to hold all the addresses we see
        unvisited.offer(root);           // start it off with the root
        while (!unvisited.isEmpty()) {
            TreeNode toVisit = unvisited.poll();
            System.out.print(toVisit.data + " ");   // visit the node
            // add the node's children to unvisited
            for (TreeNode c : toVisit.children) {
                unvisited.offer(c);
            }
        }
    }

    public boolean contains(E elt) {
        if (root == null) return false;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.offer(root);
        while (!unvisited.isEmpty()) {
            TreeNode toVisit = unvisited.poll();
            if (toVisit.data.equals(elt)) {
                return true;
            }
            for (TreeNode c : toVisit.children) {
                unvisited.offer(c);
            }
        }
        return false;
    }

    public void findReplace(E findVal, E replaceVal) {
        if (root == null) return;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.offer(root);
        while (!unvisited.isEmpty()) {
            TreeNode toVisit = unvisited.poll();
            if (toVisit.data.equals(findVal)) {
                toVisit.data = replaceVal;
            }
            for (TreeNode c : toVisit.children) {
                unvisited.offer(c);
            }
        }
        return;
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

    public int countTarget(E target) {
        if (root == null) return 0;
        Deque<TreeNode> unvisited = new ArrayDeque<>();
        unvisited.push(root);
        int count = 0;
        while (!unvisited.isEmpty()) {
            TreeNode toVisit = unvisited.pop();
            if (toVisit.data.equals(target)) {
                count++;
            }
            for (TreeNode c : toVisit.children) {
                unvisited.push(c);
            }
        }
        return count;
    }

    public int size() {
        if (root == null) return 0;     // tree is empty, size is 0
        Stack<TreeNode> unvisited = new Stack<>();
        unvisited.push(root);
        int count = 0;
        while (!unvisited.isEmpty()) {
            TreeNode nd = unvisited.pop();
            // ***visit***
            count++;
            for (TreeNode c : nd.children) {
                unvisited.push(c);
            }
        }
        return count;
    }

    public int sizeRecursive(TreeNode nd) {
        if (nd == null) return 0;
        int size = 1;       // count yourself
        for (TreeNode c : nd.children) {
            size += sizeRecursive(c);
        }
        return size;
    }

    public boolean containsRecursive(TreeNode nd, E target) {
        if (nd == null) return false;
        if (nd.data.equals(target)) return true;
        for (TreeNode c : nd.children) {
            if (containsRecursive(c, target)) {
                return true;
            }
        }   
        return false;
    }

    public int depth(TreeNode nd) {
        if (nd == null) return -1;
        int maxDepth = -1;
        for (TreeNode c : nd.children) {
            int d = depth(c);
            if (d > maxDepth) {
                maxDepth = d;
            }
        }
        return maxDepth + 1;
    }

    public E maxVal(TreeNode nd) {
        if (nd == null) return null;
        E maxVal = nd.data;
        for (TreeNode c : nd.children) {
            E val = maxVal(c);
            if (maxVal.compareTo(val) < 0) {
                maxVal = val;
            }
        }
        return maxVal;
    }

    // pseudocode
    // public void killProcess(Process p) {
    //     if (p == null) return;
    //     for (Process cp : p.children) killProcess(cp);
    //     p.status = "terminated";
    //     delete p.memory;
    //     for (File f : p.file) f.close();

    // }

    public static void main(String[] args) {
        Tree<String> foodTree = buildFoodTree();
        foodTree.printTree();

        // System.out.println(foodTree.contains("Bird"));
        // System.out.println(foodTree.contains("Salmon"));

        // System.out.println(foodTree.size());
    }
}