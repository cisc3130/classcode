import java.util.*;


public class IteratorPractice {

    public boolean checkPalindrome(List<Character> str) {
        ListIterator<Character> fit = str.listIterator(),
                bit = str.listIterator(str.size());

        while (bit.previousIndex() - fit.nextIndex() > 0) {
            if (!fit.next().equals(bit.previous())) {
                return false;
            }
        }
        
        return true;
    }

    public <E> void print(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public <E> void printEveryOther(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
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
        ListIterator<Integer> lit = lst.listIterator();
        while (lit.hasNext()) {
            // are we in the first half or second half of the list
            if (lit.nextIndex() < lst.size()/2) {       // in the first half
                sum += lit.next();
            } else {        // in the second half
                sum -= lit.next();
                if (sum < 0) return true;
            }
        }
        return false;
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
        ListIterator<Integer> lit = lst.listIterator();
        if (lit.hasNext()) lit.next();
        while(lit.hasNext()) {
            int prev = lit.previous();
            lit.next();
            prev += lit.next();
            lit.set(prev);
        }
        return lit.previous();
    }

    public <E> void printStartingFrom(List<E> lst, int idx) {
        ListIterator<E> lit = lst.listIterator(idx);
        while (lit.hasNext()) {
            System.out.println(lit.next());
        }
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
        // add starts again from head and takes linear time again


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