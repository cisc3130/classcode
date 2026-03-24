import java.util.*;

public class QueueExamples {

    // RADAR
    // s: D A R
    // q: D A R
    public static boolean checkPalindrome(String str) {
        Stack<Character> s = new Stack<>();
        Queue<Character> q = new LinkedList<>();
        for (char c : str.toCharArray()) {
            s.push(c);
            q.add(c);
        }
        while (!s.isEmpty()) {
            if (!s.pop().equals(q.remove())) {
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
        E found = null;
        while (!q.isEmpty()) {
            E elt = q.remove();
            if (!elt.equals(target)) {
                aux.add(elt);
            } else {
                found = elt;
            }
        }
        // the queue is now empty
        if (found != null) {
            q.add(found);   // add the target to the front of the queue
        }
        while (!aux.isEmpty()) {
            q.add(aux.remove());
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
    // 1 2 3 4 5 6
    // 3 2 1 4 5 6

    // q:    3 2 1 4 5 6          
    // aux stack:    
    // aux queue:     
    public static <E> void reverseFirstHalfOfQueue(Queue<E> q) {
        Stack<E> auxStack = new Stack<>();
        Queue<E> auxQueue = new LinkedList<>();

        // empty the first half of the queue onto the aux stack
        int n = q.size();
        for (int i = 0; i < n/2; i++) {
            auxStack.push(q.remove());
        }

        // empty the second half onto the aux queue
        while (!q.isEmpty()) {
            auxQueue.add(q.remove());
        }

        // now that the queue is empty, empty the auxStack back into the queue
        // so the first half will go back in the front
        while (!auxStack.isEmpty()) {
            q.add(auxStack.pop());
        }

        // now put the second half back so it will go behind the first half
        while (!auxQueue.isEmpty()) {
            q.add(auxQueue.remove());
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