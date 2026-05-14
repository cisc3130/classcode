import java.util.*;

public class PriorityQueuePractice {


    public static void main(String[] args) {
        PriorityQueue<Character> pq = new PriorityQueue<>();
        for (char c : "hello goodbye coffee water computer".toCharArray()) {
            pq.offer(c);
        }

        for (char c : pq) {
            System.out.print(c);
        }
        System.out.println();

        while (!pq.isEmpty()) {
            System.out.print(pq.poll());
        }
        System.out.println();
    }
}