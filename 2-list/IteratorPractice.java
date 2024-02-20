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

    public <E> void printInReverse(List<E> c) {
        ListIterator<E> it = c.listIterator(c.size());
        while (it.hasPrevious()) {
            System.out.println(it.previous());
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