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

   public <E> void printInReverse(List<E> lst) {
        ListIterator<E> lit = lst.listIterator(lst.size());
        // this returns an iterator to the end of the list
        // lit.hasNext() at this point would return false
        // and lit.next() would throw a NoSuchElementException
        while (lit.hasPrevious()) {
            System.out.println(lit.previous());
        }
   }

   public <E> void clearReverse(List<E> lst) {
        ListIterator<E> lit = lst.listIterator(lst.size());
        while (lit.hasPrevious()) {
            lit.previous();
            lit.remove();
        }
   }


   public boolean checkPalindrome(List<Character> clist) {
        ListIterator<Character> fit, bit;
        fit = clist.listIterator();     // iterator at the beginning of clist
        bit = clist.listIterator(clist.size());     // iterator at the end of clist
        while (fit.nextIndex() - bit.previousIndex() <= 0) {
            Character fVal = fit.next(), bVal = bit.previous();
            if (!fVal.equals(bVal)) {
                return false;
            }
        }
        return true;
   }


   public Integer accumulate (List<Integer> intList) {
        ListIterator<Integer> it = intList.listIterator();
        int total = 0;
        while (it.hasNext()) {
            total += it.next();
            it.set(total);
        }
        return total;
   }

   // a = { 1,2 3,4 5,6 7 8 10 12 14^ }
   // b = { 2, 4, 6, 8,10, 12,14 ^  }
   // after interleave: 
   // a = { 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14 }

   public <E> void interleave(List<E> a, List<E> b) {
        ListIterator<E> ait = a.listIterator(), bit = b.listIterator();
        while (bit.hasNext()) {
            if (ait.hasNext()) {
                ait.next();
            }
            ait.add(bit.next());
        }
   }


   // ^ 56 78 2 4 757 2 4 5 
    public <E extends Comparable<E>> void bubbleSort(List<E> lst) {
        boolean swapped;
        for (int i = 0; i < lst.size(); i++) {
            swapped = false;
            ListIterator<E> lit = lst.listIterator();
            for (int k = 0; k < lst.size()-i-1; k++) {
                E leftVal = lit.next();
                E rightVal = lit.next();
                if (leftVal.compareTo(rightVal) > 0) {    // if the two elements are out of order
                    lit.previous();
                    lit.set(leftVal);
                    lit.previous();
                    lit.set(rightVal);
                    swapped = true;
                    lit.next();
                }
            }
            if (!swapped) break;
        }
    }



   


    public <E> void removeEveryOther(Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                it.next();
                it.remove();
            }
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
        String[] arr = {"hello", "goodbye", "coffee", "water", "desk", "chair"};
        for (String s : arr) dll.add(s);

        IteratorPractice ip = new IteratorPractice();
        ip.clear(dll);

        List<Character> palindrome = new LinkedList<>(Arrays.asList('r', 'a', 'c', 'e', 'c', 'a', 'r')),
            notPalindrome = new LinkedList<>(Arrays.asList('r', 'a', 'd', 'e', 'c', 'a', 'r'));
        ip.checkPalindrome(palindrome);
        ip.checkPalindrome(notPalindrome);
    
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