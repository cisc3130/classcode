public class FinalPractice {

  // Fall 2023 coding question #53
  public int calculateTotalSize(Directory d) {
    if (d == null) return 0;
    int size = 0;
    for (File f : d.getFile()) {
      size += f.getSize();
    }
    for (Directory sd : d.getSubdirectories()) {
      size += calculateTotalSize(sd);
    }
    return size;
  }

  // iterative solution
  public int calculateTotalSize(Directory d) {
    if (d == null) return 0;
    Deque<Directory> univisited = new ArrayDeque<>();
    unvisited.addLast(d);
    int size = 0;
    while (!unvisited.isEmpty()) {
      Directory nd = unvisited.removeFirst();
      for (File f : nd.getFiles()) size += f.getSize();
      for (Directory sd : nd.getSubdirectories()) unvisited.addLast(sd);
    }
    return size;
  }

  // Fall 2022 final question #6
  public String concatenate(Node nd) {
    if (nd == null) return "";
    StringBuilder sb = new StringBuilder();
    sb.append(concatenate(nd.left));
    sb.append(nd.data);
    sb.append(concatenate(nd.right));
    return sb.toString();
  }

  // Fall 2022 final question #3d
  public void question() {
    Deque<Character> queue = new ArrayDeque<>();
    Deque<Character> stack = new ArrayDeque<>();
    // fill up the queue and stack
    // remove every other character from the q and add it to the stack
    Deque<Character> auxq = new ArrayDeque<>();
    while (!queue.isEmpty()) {
      stack.addLast(queue.removeFirst());
      if (!queue.isEmpty()) auxq.addLast(queue.removeFirst());
    }
    while (!auxq.isEmpty()) {
      queue.addLast(auxq.removeFirst());
    }
  }

  // Spring 2019 final question #6
  public boolean flip(Node nd) {
    if (nd == null) return false;
    nd.data = !nd.data;
    boolean answer = nd.data;
    answer = flip(nd.data.left) && answer;
    answer = flip(nd.data.right) && answer;
    return answer;
  }

  // iterative solution
  public boolean flip(Node nd) {
    if (nd == null) return false;
    Deque<Node> unvisited = new ArrayDeque<>();
    unvisited.addLast(nd);
    boolean answer = true;
    while (!unvisited.isEmpty()) {
      Node vnd = unvisited.removeLast();
      vnd.data = !vnd.data;
      answer = answer & vnd.data;
      if (vnd.left != null) unvisited.addLast(vnd.left);
      if (vnd.right != null) unvisited.addLast(vnd.right);
    }
    return answer;
  }



  public static void main(String[] args) {

  }
}
