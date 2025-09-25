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
        ListIterator<Integer> lit = intList.listIterator();
        int sum = 0;
        while (lit.hasNext()) {
            sum += lit.next();
            lit.set(sum);
        }
   }

   public <E> void interleave(List<E> a, List<E> b) {
        ListIterator<E> ait, bit;
        ait = a.listIterator();
        bit = b.listIterator();
        while (ait.hasNext() && bit.hasNext()) {
            ait.next();
            ait.add(bit.next());
        }
        while (bit.hasNext()) {
            ait.add(bit.next());
        }
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

   


    public <E> void removeEveryOther(Collection<E> c) {
        Iterator<E> it = c.iterator();
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
        String[] arr = {"hello", "goodbye", "coffee", "water", "desk", "chair"};
        for (String s : arr) dll.add(s);

        IteratorPractice ip = new IteratorPractice();
        ip.clear(dll);

        List<Character> palindrome = new LinkedList<>(Arrays.asList('r', 'a', 'c', 'e', 'c', 'a', 'r')),
            notPalindrome = new LinkedList<>(Arrays.asList('r', 'a', 'd', 'e', 'c', 'a', 'r'));
        ip.checkPalindrome(palindrome);
        ip.checkPalindrome(notPalindrome);
    
        // for (int i = 0; i < dll.size(); i++) {
        //     System.out.println(dll.get(i));
        //     if (dll.get(i).equals("hello")) {
        //         dll.remove(i);
        //     }
        // }
        // // This takes quadratic time
        // // get takes linear time
        // // remove starts again from head and takes linear time again


        // for (String s : dll) {
        //     System.out.println(s);
        // }
        // // can't query i
        // // can't modify the list at the location of s


        // Iterator<String> it = dll.iterator();
        // while (it.hasNext()) {
        //     String s = it.next();
        //     System.out.println(s);
        //     if (s.equals("hello")) {
        //         it.remove();
        //     }
        // }

        
    }
}