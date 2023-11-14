import java.util.Queue;

public class Graph<String> {
    class Node {
        String data;
        List<Node> neighbors;
        Node(String data) { this.data = data; neighbors = new LinkedList<>(); }
    }

    public Graph() {
        Node ny = new Node("New York");
        Node nj = new Node("New Jersey");
        Node pa = new Node("Pennsylvania");
        Node ma = new Node("Massachusetts");
        ny.neighbors.add(List.of(ny, nj, pa, ms));
        nj.neighbors.add(ny);
        nj.neighbors.add(pa);
        pa.neighbors.add(ny);
        pa.neighbors.add(nj);
        ms.neighbors.add(ny);
        nodes = new LinkedList<>();
        nodes.add(List.of(ny, nj, pa, ma));
    }

    List<Node> nodes;

    public void traverse() {
        Queue<Node> unvisited = new LinkedList<>();
        Set<Node> seen = new TreeSet<>();
        unvisited.add(nodes.get(0));
        while (!unvisited.isEmpty()) {
            Node nd = unvisited.remove();
            System.out.println(nd.data);
            for (Node neighbor : nd.neighbors) 
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    unvisited.add(neighbor);
                }
        }
    }

    public static void main() {

    }
}