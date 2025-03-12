import java.util.List;
import java.util.ListIterator;

public class S25MidtermPractice {
    // F23 coding question 1
    public <E extends Comparable<E>> void addToSortedList(E elt) {
        // allocate a new node
        Node nnd = new Node(elt);

        if (head == null)  {        // list is empty
            head = nnd;
            return;
        }
        
        // create a tracker node and get to the right spot
        Node tnd = head;
        while (!tnd.data.compareTo(elt) < 0) {
            if (tnd.next == null) {     // this is the last element; elt is the largest and will be added at the end
                tnd.next = nnd;
                nnd.prev = tnd;
                return;
            }
            tnd = tnd.next;
        }
        // tnd.data is now greater than the element we're inserting
        // insert nnd before tnd
        nnd.prev = tnd.prev;
        if (nnd.prev != null) {
            nnd.prev.next = nnd;
        } else {    // this is the new first node
            head = nnd;
        }
        nnd.next = tnd;
        nnd.next.prev = nnd;
    }

    // filter elements of a list based on a condition
    public <E> void filter(List<E> lst, Predicate<E> p)) {
        ListIterator<E> it = lst.listIterator();
        while (it.hasNext()) {
            if(p.test(it.next())) {
                it.remove();
            }
        }
    }

    // F23 coding question #3: get average length of words in list
    public double getAverageWordLength(List<String> words) {
        return words.stream()
            .mapToInt(String::length)       // or .mapToInt(str -> str.length())
            .average()
            .getAsDouble();
    }       

    // reverse a word
    public String reverseString(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            stack.push(c);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    // s24 midterm coding question #1
    public boolean removeFirstMatch(E elt) {
        Node tnd = head;

        while (tnd != null && !tnd.data.equals(elt)) {
            tnd = tnd.next;
        }

        if (tnd == null) {
            return false;
        }

        // tnd.data is equal to elt
        if (tnd.prev != null) {             // tnd is not the first element
            tnd.prev.next = tnd.next;
        } else {        // tnd is the first element
            head = tnd.next;
        }
        if (tnd.next == null) {             // tnd is the last element
            tnd.next.prev = tnd.prev;
        }

        return true;
    }

    // s25 midterm coding question #2
    public <E> void replace(List<E> lst, E findValue, E replaceValue) {
        ListIterator<E> it = lst.listIterator();
        while (it.hasNext()) {
            if(it.next().equals(findValue)) {
                it.set(replaceValue);
            }
        }
    }

    // s25 midterm coding question #3
    bananaList.stream()
        .filter(b -> b.getSweetness() > 0.2)
        .collect(Collectors.toList);

    // s25 midterm coding question #4
    public <E> E getStackPos(Stack<E> s, int pos) {
        Stack<E> auxStack = new Stack<E>();
        for (int i = 0; i < pos; i++) {
            auxStack.push(s.pop());
        }
        E toReturn = s.peek();
        while (!auxStack.isEmpty()) {
            s.push(auxStack.pop());
        }
        return toReturn;
    }

    // s19 #6
    public void shrinkToFit() {
        Object[] newData = new Object[size];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // s19 #7
    public <E extends Comparable<E>> void merge(List<E> a, List<E> b) {
        ListIterator<E> ait = a.listIterator(), bit = b.listIterator();
        while (ait.hasNext() && bit.hasNext()) {
            E bval = bit.next();
            if (ait.next().compareTo(bval) > 0)  { // ait's value is larger
                ait.previous();
                ait.add(bval);
                ait.next();
            } else {    // ait's value is smaller
                bit.previous();
            }
        }
    }

    // f18 #3
    public void repeat() {
        if (head == null) return;

        Node whereToAdd = head;
        while (whereToAdd.next != null) {
            whereToAdd = whereToAdd.next;
        }
        // tnd is now pointing to the last element
        Node endOfOriginalList = whereToAdd, whereToCopy = head;
        while (whereToCopy != endOfOriginalList.next) {
            Node nnd = new Node(whereToCopy.data);
            whereToAdd.next = nnd;
            nnd.prev = whereToAdd;
            whereToCopy = whereToCopy.next;
            whereToAdd = whereToAdd.next;
        }

    }
}