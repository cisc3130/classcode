import java.util.*;
import java.util.stream.Collectors;

// State node class
class State {
    String name;
    List<State> neighbors;

    State(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    void addNeighbor(State neighbor) {
        if (!this.neighbors.contains(neighbor)) {
            this.neighbors.add(neighbor);
            neighbor.neighbors.add(this); // For undirected graph
        }
    }

    public boolean equals(State other) {
      return this.name.equals(other.name);
    }
} // end state node class

// Graph class
class Graph {
    Map<String, State> states;

    Graph() {
        states = new HashMap<>();
    }

    void addState(String name) {
        states.putIfAbsent(name, new State(name));
    }

    void addEdge(String stateName1, String stateName2) {
        State state1 = states.get(stateName1);
        State state2 = states.get(stateName2);

        if (state1 != null && state2 != null) {
            state1.addNeighbor(state2);
        }
    }

    State getState(String name) {
      return states.get(name);
    }
} // end Graph class

// Main class
public class USStateGraph {
    public static Graph buildUSMap() {
        Graph graph = new Graph();

        // Add all 50 states
        String[] stateNames = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", 
            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", 
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", 
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", 
            "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", 
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", 
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", 
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", 
            "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
        };

        for (String stateName : stateNames) {
            graph.addState(stateName);
        }

        // Add neighboring states
        // Note: This list is not exhaustive and serves as a starting point. Some states may have more neighbors.
        // Alabama
        graph.addEdge("Alabama", "Florida");
        graph.addEdge("Alabama", "Georgia");
        graph.addEdge("Alabama", "Mississippi");
        graph.addEdge("Alabama", "Tennessee");

        // Alaska - No neighbors

        // Arizona
        graph.addEdge("Arizona", "California");
        graph.addEdge("Arizona", "Colorado");
        graph.addEdge("Arizona", "Nevada");
        graph.addEdge("Arizona", "New Mexico");
        graph.addEdge("Arizona", "Utah");

        // Arkansas
        graph.addEdge("Arkansas", "Louisiana");
        graph.addEdge("Arkansas", "Mississippi");
        graph.addEdge("Arkansas", "Missouri");
        graph.addEdge("Arkansas", "Oklahoma");
        graph.addEdge("Arkansas", "Tennessee");
        graph.addEdge("Arkansas", "Texas");

        // California
        graph.addEdge("California", "Nevada");
        graph.addEdge("California", "Oregon");

        // Colorado
        graph.addEdge("Colorado", "Kansas");
        graph.addEdge("Colorado", "Nebraska");
        graph.addEdge("Colorado", "New Mexico");
        graph.addEdge("Colorado", "Oklahoma");
        graph.addEdge("Colorado", "Utah");
        graph.addEdge("Colorado", "Wyoming");

        // Connecticut
        graph.addEdge("Connecticut", "Massachusetts");
        graph.addEdge("Connecticut", "New York");
        graph.addEdge("Connecticut", "Rhode Island");

        // Delaware
        graph.addEdge("Delaware", "Maryland");
        graph.addEdge("Delaware", "New Jersey");
        graph.addEdge("Delaware", "Pennsylvania");

        // Florida
        graph.addEdge("Florida", "Georgia");

        // Georgia
        graph.addEdge("Georgia", "North Carolina");
        graph.addEdge("Georgia", "South Carolina");
        graph.addEdge("Georgia", "Tennessee");

        // Hawaii - No neighbors

        // Idaho
        graph.addEdge("Idaho", "Montana");
        graph.addEdge("Idaho", "Nevada");
        graph.addEdge("Idaho", "Oregon");
        graph.addEdge("Idaho", "Utah");
        graph.addEdge("Idaho", "Washington");
        graph.addEdge("Idaho", "Wyoming");

        // Illinois
        graph.addEdge("Illinois", "Indiana");
        graph.addEdge("Illinois", "Iowa");
        graph.addEdge("Illinois", "Kentucky");
        graph.addEdge("Illinois", "Missouri");
        graph.addEdge("Illinois", "Wisconsin");

        // Indiana
        graph.addEdge("Indiana", "Kentucky");
        graph.addEdge("Indiana", "Michigan");
        graph.addEdge("Indiana", "Ohio");

        // Iowa
        graph.addEdge("Iowa", "Minnesota");
        graph.addEdge("Iowa", "Missouri");
        graph.addEdge("Iowa", "Nebraska");
        graph.addEdge("Iowa", "South Dakota");
        graph.addEdge("Iowa", "Wisconsin");

        // Kansas
        graph.addEdge("Kansas", "Missouri");
        graph.addEdge("Kansas", "Nebraska");
        graph.addEdge("Kansas", "Oklahoma");

        // Kentucky
        graph.addEdge("Kentucky", "Missouri");
        graph.addEdge("Kentucky", "Ohio");
        graph.addEdge("Kentucky", "Tennessee");
        graph.addEdge("Kentucky", "Virginia");
        graph.addEdge("Kentucky", "West Virginia");

        // Louisiana
        graph.addEdge("Louisiana", "Mississippi");
        graph.addEdge("Louisiana", "Texas");

        // Maine
        graph.addEdge("Maine", "New Hampshire");

        // Maryland
        graph.addEdge("Maryland", "Pennsylvania");
        graph.addEdge("Maryland", "Virginia");
        graph.addEdge("Maryland", "West Virginia");

        // Massachusetts
        graph.addEdge("Massachusetts", "New Hampshire");
        graph.addEdge("Massachusetts", "New York");
        graph.addEdge("Massachusetts", "Rhode Island");
        graph.addEdge("Massachusetts", "Vermont");

        // Michigan
        graph.addEdge("Michigan", "Ohio");
        graph.addEdge("Michigan", "Wisconsin");

        // Minnesota
        graph.addEdge("Minnesota", "North Dakota");
        graph.addEdge("Minnesota", "South Dakota");
        graph.addEdge("Minnesota", "Wisconsin");

        // Mississippi
        graph.addEdge("Mississippi", "Tennessee");

        // Missouri
        graph.addEdge("Missouri", "Nebraska");
        graph.addEdge("Missouri", "Oklahoma");
        graph.addEdge("Missouri", "Tennessee");

        // Montana
        graph.addEdge("Montana", "North Dakota");
        graph.addEdge("Montana", "South Dakota");
        graph.addEdge("Montana", "Wyoming");

        // Nebraska
        graph.addEdge("Nebraska", "South Dakota");
        graph.addEdge("Nebraska", "Wyoming");

        // Nevada
        graph.addEdge("Nevada", "Oregon");
        graph.addEdge("Nevada", "Utah");

        // New Hampshire
        graph.addEdge("New Hampshire", "Vermont");

        // New Jersey
        graph.addEdge("New Jersey", "New York");
        graph.addEdge("New Jersey", "Pennsylvania");

        // New Mexico
        graph.addEdge("New Mexico", "Oklahoma");
        graph.addEdge("New Mexico", "Texas");
        graph.addEdge("New Mexico", "Utah");

        // New York
        graph.addEdge("New York", "Pennsylvania");
        graph.addEdge("New York", "Vermont");

        // North Carolina
        graph.addEdge("North Carolina", "South Carolina");
        graph.addEdge("North Carolina", "Tennessee");
        graph.addEdge("North Carolina", "Virginia");

        // North Dakota
        graph.addEdge("North Dakota", "South Dakota");

        // Ohio
        graph.addEdge("Ohio", "Pennsylvania");
        graph.addEdge("Ohio", "West Virginia");

        // Oklahoma
        graph.addEdge("Oklahoma", "Texas");

        // Oregon
        graph.addEdge("Oregon", "Washington");

        // Pennsylvania
        graph.addEdge("Pennsylvania", "West Virginia");

        // Rhode Island - Additional neighbors are already covered by Connecticut and Massachusetts

        // South Carolina
        graph.addEdge("South Carolina", "Georgia");

        // South Dakota
        graph.addEdge("South Dakota", "Wyoming");

        // Tennessee
        graph.addEdge("Tennessee", "Virginia");

        // Texas
        graph.addEdge("Texas", "New Mexico");

        // Utah
        graph.addEdge("Utah", "Wyoming");

        // Vermont - Additional neighbors are already covered by New Hampshire and New York

        // Virginia
        graph.addEdge("Virginia", "West Virginia");

        // Washington - Additional neighbors are already covered by Idaho and Oregon

        // West Virginia
        // Additional neighbors are already covered by Kentucky, Maryland, Ohio, Pennsylvania, and Virginia

        // Wisconsin
        // Additional neighbors are already covered by Illinois, Iowa, Michigan, and Minnesota

        // Wyoming
        // Additional neighbors are already covered by Colorado, Idaho, Montana, Nebraska, South Dakota, and Utah

        return graph;
    } // end buildUSMap


    public static void main(String[] args) {
        
    }

}