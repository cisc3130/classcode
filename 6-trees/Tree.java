import java.util.*;

class Node<E> {
  E data;
  List<Node<E>> children;
  int level;
  public Node(E data) {
    this.data = data;
    children = new LinkedList<>();
  }
  public void addChildNodes(List<Node<E>> cs) {
    for (Node<E> c : cs) c.level = this.level + 1;
    children.addAll(cs);
  }

  public String toString() {
    return " ".repeat(level) + data;
  }
}

public class Tree<E> {
  protected Node<E> root;

  public boolean isEmpty() {
    return root == null;
  }

  public void printTreeBreadthFirst() {
    if (root == null) return;
    // 1. Create a data structure to hold the *unvisited* nodes
    Queue<Node<E>> unvisited = new LinkedList<>();
    // 2. Add the root to unvisited
    unvisited.add(root);
    // 3. loop over unvisited using standard adaptor consumer loop
    // 3a. loop until unvisited is empty
    while (!unvisited.isEmpty()) {
      // 3b. remove the first element from the queue
      Node<E> nd = unvisited.remove();
      // 4. add all the node's children to unvisited
      for (Node<E> child : nd.children) unvisited.add(child);
      // 5. visit the node
      System.out.print(nd.level + "/" + nd.data + " ");
    }
    System.out.println();
  }

  public void printTreeDepthFirst() {
    if (this.isEmpty()) return;
    Stack<Node<E>> unvisited = new Stack<>();
    unvisited.push(root);
    while (!unvisited.isEmpty()) {
      Node<E> nd = unvisited.pop();
      for (Node<E> child : nd.children) unvisited.push(child);
      System.out.print(nd.level + "/" + nd.data + " ");
    }
    System.out.println();
  }

  // check if tree contains an element
  public boolean contains(E elt) {
    if (root == null) return false;
    Stack<Node<E>> unvisited = new Stack<>();
    unvisited.push(root);
    while (!unvisited.isEmpty()) {
      Node<E> nd = unvisited.pop();
      if (nd.data.equals(elt)) return true;
      for (Node<E> child : nd.children) unvisited.push(child);
    }
    return false;
  }

  public boolean containsRecursive(E elt) {
    return containsRecursive(elt, root);
  }

  // * recursive method should take a node and return the correct
  // answer for the subtree rooted in that node
  public boolean containsRecursive(E elt, Node<E> nd) {
    // * base case: what if node is null (empty tree)
    if (nd == null) return false;
    // * visit the node
    if (nd.data.equals(elt)) return true;
    // * recurse on all children
    for (Node<E> child : nd.children) {
      if (containsRecursive(elt, child)) return true;
    }
    return false;    
  }

  public Node<E> get(E elt) {
    if (root == null) return null;
    Stack<Node<E>> unvisited = new Stack<>();
    unvisited.push(root);
    while (!unvisited.isEmpty()) {
      Node<E> nd = unvisited.pop();
      if (nd.data.equals(elt)) return nd;
      for (Node<E> child : nd.children) unvisited.push(child);
    }
    // reached the end without finding the value
    return null;
  }

  public int size() {
    if (root == null) return 0;
    Queue<Node<E>> unvisited = new LinkedList<>();
    unvisited.add(root);
    int count = 0;
    while (!unvisited.isEmpty()) {
      Node<E> nd = unvisited.remove();
      for (Node<E> child : nd.children) unvisited.add(child);
      count++;
    }
    return count;
  }

  public int sizeRecursive() {
    return sizeRecursive(root);
  }

  protected int sizeRecursive(Node<E> nd) {
    if (nd == null) return 0;
    int size = 1;
    for (Node<E> child : nd.children) {
      size += sizeRecursive(child);
    }
    return child;
  }

  public int findDepth(E elt, Node<E> nd) {
    if (nd.children.isEmpty()) return 0;
    int maxChildDepth = -1;
    for (Node<E> child : nd.children) {
      int childDepth = findDepth(elt, child);
      if (childDepth > maxChildDepth) maxChildDepth = childDepth;
    }
    return maxChildDepth + 1;
  }
  

  public static Tree<String> buildFoodTree() {
        Tree<String> foodTree = new Tree<>();
        String[] names = "Food Plant Animal Roots Leaves Fruits Fish Mammals Birds Potatoes Carrots Lettuce Cabbage Apples Pears Plums Oranges Salmon Tuna Beef Lamb Chicken Duck GrannySmith Gala".split(" ");
        Map<String, Node<String>> nodes = new HashMap<>();
        for (String n : names) nodes.put(n.toLowerCase(), new Node<String>(n));
        
        foodTree.root = nodes.get("food");
        foodTree.root.level = 0;
        nodes.get("food").addChildNodes(List.of(nodes.get("plant"), nodes.get("animal")));
        nodes.get("plant").addChildNodes(List.of(nodes.get("roots"), nodes.get("leaves"), nodes.get("fruits")));
        nodes.get("animal").addChildNodes(List.of(nodes.get("fish"), nodes.get("mammals"), nodes.get("birds")));
        nodes.get("roots").addChildNodes(List.of(nodes.get("potatoes"), nodes.get("carrots")));
        nodes.get("leaves").addChildNodes(List.of(nodes.get("lettuce"), nodes.get("cabbage")));
        nodes.get("fruits").addChildNodes(List.of(nodes.get("apples"), nodes.get("pears"), nodes.get("plums"), nodes.get("oranges")));
        nodes.get("fish").addChildNodes(List.of(nodes.get("salmon"), nodes.get("tuna")));
        nodes.get("mammals").addChildNodes(List.of(nodes.get("beef"), nodes.get("lamb")));
        nodes.get("birds").addChildNodes(List.of(nodes.get("chicken"), nodes.get("duck")));
        nodes.get("apples").addChildNodes(List.of(nodes.get("grannysmith"), nodes.get("gala")));

        return foodTree;
    }

  public static void main(String[] args) {
    Tree<String> foodTree = buildFoodTree();
    foodTree.printTreeBreadthFirst();
    foodTree.printTreeDepthFirst();
    System.out.println(String.format("There are %d nodes in the tree", foodTree.size()));
    // String someFood = "Coffee";
    // System.out.println(String.format("Tree %s %s", foodTree.contains(someFood) ? "contains" : "does not contain", someFood));
    // System.out.println(String.format("%s is at level %d", someFood, foodTree.findLevel(someFood)));
  }
}