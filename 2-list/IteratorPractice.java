import java.util.*;


public class IteratorPractice {

    public <E> void print(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public <E> void clear(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public <E> int removeIfMatch(Collection<E> c, E target) {
        int count = 0;
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            E elt = it.next();
            if (elt.equals(target)) {
                it.remove();
                count++;
            }
        }
        return count;
    }

    public <E extends Comparable<E>> void bubbleSort(List<E> lst) {
        if (lst.size() < 2) return;
        for (int i = 0; i < lst.size(); i++) {
            boolean swapped = false;
            ListIterator<E> lit1 = lst.listIterator(), lit2 = lst.listIterator(1);
            while (lit2.hasNext()) {
                E val1 = lit1.next(), val2 = lit2.next();
                if (val1.compareTo(val2) > 0) {
                    swapped = true;
                    lit1.set(val2);
                    lit2.set(val1);
                }
            }
            if (!swapped) return;
        }
    }




    public <E> void interleave(List<E> lst1, List<E> lst2) {
       ListIterator<E> lit1 = lst1.listIterator(),
                        lit2 = lst2.listIterator();
       while(lit1.hasNext() && lit2.hasNext()) {
            lit1.next();
            lit1.add(lit2.next());
            lit1.next();
       }
       while(lit2.hasNext()) {
            lit1.add(lit2.next());
            lit1.next();
       }
    }

    public boolean checkPalindrome(List<Character> str) {
        ListIterator<Character> fit = str.listIterator(), bit = str.listIterator(str.size());
        while (bit.previousIndex() - fit.nextIndex() > 0) {
            if (!fit.next().equals(bit.previous())) {
                return false;
            }
        }
        return true;

    }


    public <E> void removeEveryOther(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
            if (it.hasNext()) it.next();
        }
    }

    public <E> void printInReverse(List<E> c) {
        ListIterator<E> it = c.listIterator(c.size());
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    public boolean secondHalfSumIsGreater(List<Integer> lst) {
        int sum = 0;
        ListIterator<Integer> it = lst.listIterator();
        int midpoint = lst.size()/2;
        while (it.hasNext()) {
            if (it.nextIndex() < midpoint) sum += it.next();
            else sum -= it.next();
        }
        return sum < 0;
    }

    public int accumulate(List<Integer> lst) {
        ListIterator<Integer> lit = lst.listIterator();
        int sum = 0;
        while (lit.hasNext()) {
            sum += lit.next();
            lit.set(sum);
        }
        return sum;
    }

    public int accumulateWithoutVars(List<Integer> lst) {
        ListIterator<Integer> it1 = lst.listIterator(), it2 = lst.listIterator(1);
        while (it2.hasNext()) {
            it2.set(it1.next() + it2.next());
        }
        return it1.next();
    }

    public <E> void printStartingFrom(List<E> lst, int idx) {
        
    }

    public void func(List<String> lst) {
        ListIterator<String> lit = lst.listIterator();
        while (lit.hasNext()) {
            String str = lit.next();
            if (str.equals("hello")) {
                lit.remove();
            }
            if (str.equals("goodbye")) {
                lit.add("leaving");
            }
            if (str.equals("coffee")) {
                lit.set("tea");
            }
        }
    }

    public static void main(String[] args) {
        List<String> dll = new java.util.LinkedList<>();
        
        // fill the list up


    
        for (int i = 0; i < dll.size(); i++) {
            System.out.println(dll.get(i));
            if (dll.get(i).equals("hello")) {
                dll.remove(i);
            }
        }
        // This takes quadratic time
        // get takes linear time
        // remove starts again from head and takes linear time again


        for (String s : dll) {
            System.out.println(s);
        }
        // can't query i
        // can't modify the list at the location of s


        Iterator<String> it = dll.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
            if (s.equals("hello")) {
                it.remove();
            }
        }

        
    }
}