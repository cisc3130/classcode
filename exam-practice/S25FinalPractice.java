import java.io.File;
import java.util.*;

import org.w3c.dom.Node;

public class S25FinalPractice {
    
    // s24 coding question 62
    // (a) Given two nodes representing employees, find the lowest-ranking manager they have in common.
    // (This may require an additional data structure.)
    public Node findCommonManager(Node e1, Node e2) {
        Set<Node> e1Managers = new TreeSet<>();
        Node e1m = e1.getManager();
        while (e1m != null) {
            e1Managers.add(e1m);
            e1m = e1m.getManager();
        }
        Node e2m = e2.getManager();
        while (e2m != null) {
            if (e1Managers.contains(e2m)) {
                return e2m;
            }
            e2m = e2m.getManager();
        }
        return null;
    }


    // (b) Given a node representing a manager, give the manager and everyone in their reporting structure
    // a 10% raise
    public void giveRaise(Node manager) {
        if (manager == null) return;                    // base case
        manager.setSalary(manager.getSalary()*1.1);     // visit
        for (Node c : manager.getEmployees()) {
            giveRaise(c);
        }
    }

    // (c) Given an employee’s name as a string, find their node in the tree.
    public Node findEmployee(String employeeName) {
        Deque<Node> unvisited = new ArrayDeque<>();
        unvisited.add(ceo);
        while (!unvisited.isEmpty()) {
            Node toVisit = unvisited.remove();
            if (toVisit.getName().equals(employeeName)) return toVisit;
            for (Node c : toVisit.getEmployees()) {
                unvisited.add(c);
            }
        }
        return null;
    }


    // s24 queston 63
    // (a) Declare and instantiate a Map that maps product names (Strings) to Integers (number of associ-
    // ated complaints)
    private Map<String, Integer> productComplaints = new HashMap<>();

    // (b) Given a list of Strings representing customer complaints, iterate over the list and update the map
    // so that it contains the number of complaints associated with each product
    public void updateComplaintsMap(List<String> complaints) {
        for (String c : complaints) {
            String product = findProductName(c);
            productComplaints.compute(product, (k, v) -> v == null ? 1 : v+1);
        }
    }

    // (c) Given a product name, return the number of complaints associated with it in the list.
    public int numComplaints(String product) {
        return productComplaints.getOrDefault(product, 0);
    }

    // (d) Find the product with the most associated complaints.
    public String findWorstProduct() {
        String worstProduct = null;
        int mostComplaints = -1;
        for (Map.Entry<String, Integer> e : productComplaints.entrySet()) {
            if (e.getValue().compareTo(mostComplaints) > 0) {
                worstProduct = e.getKey();
                mostComplaints = e.getValue();
            }
        }
        return worstProduct;
    }

    // 53. You are designing a directory structure for a file system. Each directory can contain
    // files and subdirectories. Write a recursive function calculateTotalSize that takes a
    // Directory object as input and returns the total size (in bytes) of all files within the
    // directory and its subdirectories.
    public int calculateTotalSize(Directory d) {
        int size = 0;
        for (File f : d.getFiles()) {       // visit
            size += f.getSize();
        }
        for (Directory sd : d.getSubdirectories()) {        // recurse
            size += calculateTotalSize(sd);
        }
        return size;
    }

    public static void removeDuplicates(List<Integer> inputList) {
        Set<Integer> seen = new HashSet<>();
        ListIterator<Integer> lit = inputList.listIterator();
        while (lit.hasNext()) {
            boolean alreadySeen = !seen.add(lit.next());
            if (alreadySeen) {
                lit.remove();
            }
        }
    }

    public static List<Integer> removeDuplicates(List<Integer> inputList) {
        List<Integer> toReturn = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        for (int i : inputList) {
            boolean alreadySeen = !seen.add(i);
            if (!alreadySeen) {
                toReturn.add(i);
            }
        }
        return toReturn;
    }

    // 55
    public void countAndPrintVotes(List<String> votes) {
        Map<String, Integer> voteCounts = new HashMap<>();
        for (String s : votes) {
            voteCounts.compute(s, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<String, Integer> e : voteCounts.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}