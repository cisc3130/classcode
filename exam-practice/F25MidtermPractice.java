import java.util.*;

public class F25MidtermPractice {
    public class DoublyLinkedList <E> {
        class Node {
            E data ;
            Node prev , next ;
            Node (E data ) { this . data = data ; }
        }
        Node head , tail ;

        public void reflect() {
            if (head == null) return;
            Node tnd = tail.prev;
            while (tnd != null) {
                Node nnd = new Node(tnd.data);
                tail.next = nnd;
                nnd.prev = tail;
                tail = tail.next;
                tnd = tnd.prev;
            }
        }

        public boolean removeFirst(E elt) {
            Node tnd = head;
            while (tnd != null && !tnd.data.equals(elt)) {
                tnd = tnd.next;
            }
            if (tnd == null) return false;
            if (tnd.prev != null) {
                tnd.prev.next = tnd.next;
            } else {
                head = head.next;
            }
            if (tnd.next != null) {
                tnd.next.prev = tnd.prev;
            } else {
                tail = tail.prev;
            }
            return true;
        }
    }

    public static <E> void doubleList(List<E> lst) {
        ListIterator<E> it = lst.listIterator();
        while (it.hasNext()) {
            it.add(it.next());
        }
    }

    public static <E extends Comparable<E>> void bubbleStack(Stack<E> stk) {
        Deque<E> auxStack = new ArrayDeque<>();
        E maxVal = stk.peek();
        while (!stk.isEmpty()) {
            if (maxVal.compareTo(stk.peek()) < 0) {
                maxVal = stk.peek();
            }
            auxStack.push(stk.pop());
        }

        while (!auxStack.isEmpty()) {
            E top = auxStack.pop();
            if (!top.equals(maxVal)) {
                stk.push(auxStack.pop());
            }
        }
        
        stk.push(maxVal);
    }

    public static void main(String[] args) {
        List<String> wordList;
        wordList.stream()
            .filter(w -> w.startsWith("z"))
            .mapToInt(String::length)
            .average()
            .getAsDouble();
    }
}

