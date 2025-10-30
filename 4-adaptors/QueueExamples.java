import java.util.*;

public class QueueExamples {

    public static boolean checkPalindrome(String str) {
        Stack<Character> s = new Stack<>();
        Queue<Character> q = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            s.push(c);
            q.offer(c);
        }

        while (!s.isEmpty()) {
            if (!s.pop().equals(q.poll())) {
                return false;
            }
        }

        return true;
    }

    public boolean checkPalindrome2(String str) {
        List<Character> s = new java.util.LinkedList<>(), q = new java.util.LinkedList<>();

        for (char c : str.toCharArray()) {
            s.add(c);
            q.add(c);
        }

        while (!s.isEmpty()) {
            if (!s.remove(s.size()-1).equals(q.remove(0))) {
                return false;
            }
        }

        return true;
    }

    public boolean checkPalindrome3(String str) {
        Deque<Character> s = new java.util.LinkedList<>(), q = new java.util.ArrayDeque<>();

        for (char c : str.toCharArray()) {
            s.addFirst(c);
            q.addLast(c);
        }

        while (!s.isEmpty()) {
            if (!s.removeFirst().equals(q.removeFirst())) {
                return false;
            }
        }

        return true;
    }

    // queue move target to front
    public <E> void moveTargetToFront(Queue<E> q, E target) {
        Queue<E> aux = new LinkedList<>();
        while (!q.isEmpty()) {
            E x = q.poll();
            if (!x.equals(target)) {
                aux.offer(x);
            }
            while (!q.isEmpty()) {
                aux.add(q.remove());
            }
            q.add(target);

            while (!aux.isEmpty()) {
                q.add(aux.remove());
            }
        }
    }

    // queue move nth to front
    public <E> void moveNthToFront(Queue<E> q, int n) {
        Queue<E> aux = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            aux.add(q.remove());
        }
        E nth = q.remove();
        while (!q.isEmpty()) {
            aux.add(q.remove());
        }
        q.add(nth);     // this goes in once the queue has been emptied so it can be first in the queue
        // now add all the other elements in after it
        while (!aux.isEmpty()) {
            q.add(aux.remove());
        }
    }

    // reverse the first half of the queue
    public static <E> void reverseFirstHalfOfQueue(Queue<E> q) {
        int half = q.size()/2;
        Stack<E> s = new Stack<>();
        for (int i = 0; i < half; i++) {
            s.push(q.poll());
        }
        Queue<E> aq = new ArrayDeque<>();       // empty the second half into an auxiliary queue
        while (!q.isEmpty()) {                  // so we can put the first half back into an empty queue
            aq.offer(q.poll());                 // so it can still be first
        }
        while (!s.isEmpty()) {      // now that the queue is empty put first half back in reverse order
            q.offer(s.pop());
        }
        while (!aq.isEmpty()) {     // and then put back second half
            q.offer(aq.poll());
        }
    }



    public static void main(String[] args) {
        Queue<Integer> q1 = new java.util.LinkedList<>(),
            q2 = new ArrayDeque<>();

        for (int i = 0; i < 5; i++) {
            q1.add(i);
            q2.offer(i);
        }

        // while (!q1.isEmpty()) {
        //     System.out.println(q1.poll());
        //     System.out.println(q2.remove());
        // }
        System.out.println();

        for (int i : q2) {
            System.out.print(i);
        }
        System.out.println();

        reverseFirstHalfOfQueue(q2);

        for (int i : q2) {
            System.out.print(i);
        }

    }
}