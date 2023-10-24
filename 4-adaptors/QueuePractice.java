import java.util.*;

public class QueuePractice {

    public static <E> void printQueue(Queue<E> q) {
        Queue<E> auxq = new LinkedList<>();
        while (!q.isEmpty()) {
            E elt = q.remove();
            System.out.print(elt + ", ");
            auxq.add(elt);
        }
        while (!auxq.isEmpty()) q.add(auxq.remove());
        System.out.println();
    }

    public static <E> void removeEveryOther(Queue<E> q) {
        Queue<E> auxq = new LinkedList<>();
        while (!q.isEmpty()) {
            auxq.add(q.remove());
            if (!q.isEmpty()) q.remove();
        }
        while (!auxq.isEmpty()) q.add(auxq.remove());
    }

    public static <E> void printStack(Stack<E> s) {
        Stack<E> auxs = new Stack<>();
        while (!s.isEmpty()) {
            E elt = s.pop();
            System.out.print(elt + ", ");
            auxs.push(elt);
        }
        while (!auxs.isEmpty()) s.push(auxs.pop());
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < 10; i++) {
            q.add(i*10);
            s.add(i*10);
        }
        printQueue(q);
        printStack(s);

        removeEveryOther(q);
        printQueue(q);
    }
}