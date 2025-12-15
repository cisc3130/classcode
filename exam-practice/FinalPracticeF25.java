public void giveRaise(Node manager) {
    if (manager == null) return;
    manager.salary = manager.salary * 1.1;
    for (Node emp : manager.subordinates) {
      giveRaise(emp);
    }
}

// Given the employee's name as a string, find their node in the tree
public Node findEmployee(Node manager, String name) {
    if (manager == null) return null;
    if (manager.name.equals(name)) return manager;
    for (Node emp : manager.subordinates) {
      Node found = findEmployee(emp, name);
      if (found != null) return found;
    }
    return null;
}

public Node findEmployee(Node manager, String name) {
    if (manager == null) return null;
    Deque<Node> unvisited = new ArrayDeque<>();
    unvisited.push(manager);
    while (!unvisited.isEmpty()) {
      Node current = unvisited.pop();
      if (current.name.equals(name)) return current;        // <- VISIT
      for (Node emp : current.subordinates) {
        unvisited.push(emp);
      }
    }
    return null;        // not found
}

Map<String, Integer> complaintCount = new HashMap<>();
for (String complaint : complaints) {
    complaintCount.compute(complaint, (k, v) -> (v == null) ? 1 : v + 1);
}

complaintCount.get("Toaster");

// Which product had the most complaints?
String mostComplainedProduct = null;
int maxComplaints = 0;
for (Map.Entry<String, Integer> entry : complaintCount.entrySet()) {
    if (entry.getValue() > maxComplaints) {
        maxComplaints = entry.getValue();
        mostComplainedProduct = entry.getKey();
    }
}

public List<String> findAwardees(Set<String> alreadyAwarded, List<String> nominees) {
    List<String> awardees = new ArrayList<>();
    for (String nominee : nominees) {
        boolean alreadyAwarded = !alreadyAwarded.add(nominee);
        if (!alreadyAwarded) {
            awardees.add(nominee);  
        }
    }
    return awardees;
}

public List<Integer> removeDuplicates(List<Integer> numbers) {
    Set<Integer> seen = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    for (Integer number : numbers) {
        if (seen.add(number)) {
            result.add(number);
        }
    }
    return result;
}

public List<Integer> removeDuplicates(List<Integer> numbers) {
    return new ArrayList<>(new LinkedHashSet<>(numbers));
}