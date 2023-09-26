import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class IteratorPractice {

    // print each element in a list twice (without saving values) 
    public <E> void printElementsTwice(List<E> lst) {
        ListIterator<E> lit = lst.listIterator();
        while (lit.hasNext()) {
            System.out.println(lit.next());
            System.out.println(lit.previous());
            lit.next();
        }
    }

    // reverse a list
    public <E> void reverseList(List<E> lst) {
        ListIterator<E> flit = lst.listIterator(),
                        blit = lst.listIterator(lst.size());
        int fi = flit.nextIndex(), bi = blit.previousIndex();
        while (fi - bi > 1) {
            E felt = flit.next(), belt = blit.previous();
            flit.set(belt);
            blit.set(felt);
        }
    }

    // find a specific element and remove it
    public <E> boolean removeElement(Collection<E> c, E target) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            if (it.next().equals(target)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // find elementA in a list. If it's in the first half
    // of the list, remove it. If it's in the second half of the list,
    // set it to elementB. If it's not in the list, add it
    public <E> void madeUpExample(List<E> lst, E elementA, E elementB) {
        ListIterator<E> lit = lst.listIterator();
        while (lit.hasNext()) {
            if (lit.next().equals(elementA)) {
                if (lit.previousIndex() < lst.size()/2) {
                    lit.remove();
                } else {
                    lit.set(elementB);
                }
            }
        }
        lit.add(elementA);
    }

    public <E> void removeElementUsingGet(List<E> lst, E target) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals(target)) lst.remove(i);
        }
    }

    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("hello");
        c.add("goodbye");
        c.add("cat");
        c.add("dog");

        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        List<String> lst = new ArrayList<>(c);
        ListIterator<String> lit = lst.listIterator(lst.size());
        while (lit.hasPrevious()) {
            System.out.println(lit.previous());
        }

        

    }
}