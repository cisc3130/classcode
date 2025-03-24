import java.util.*;

public class SetPractice {

    static Set<String> dictionary = loadDictionary();;

    

    public static Set<String> loadDictionary() {
        String dictfile = "../words_alpha.txt";
        Set<String> dictionary = new HashSet<>();
        try (Scanner sc = new Scanner(SetPractice.class.getResource
                (dictfile).openStream())) {
            while (sc.hasNext()) {
                dictionary.add(sc.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static Set<Integer> findPrimes(int ceiling) {
        Set<Integer> numbers = new TreeSet<>();
        for (int i = 2; i < ceiling; i++) {
            numbers.add(i);
        }

        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            Integer x = it.next();
            for (int y = x*2; y < ceiling; y += x) {
                numbers.remove(y);
            }
        }

        return numbers;
    }

    public static List<Integer> checkDocument(List<String> doc) {
        ListIterator<String> it = doc.listIterator();
        List<Integer> misspelledIdx = new CLinkedList<>();

        while (it.hasNext()) {
            String word = it.next();
            if (!dictionary.contains(word)) {
                misspelledIdx.add(it.previousIndex());
            }
        }

        return misspelledIdx;
    }

    public static <E> boolean containsDuplicates(List<E> lst) {
        Set<E> s = new TreeSet<>(lst);
        return s.size() < lst.size();
    }

    public static <E> void findDuplicates(List<E> lst) {
        Set<E> seen = new TreeSet<>();
        ListIterator<E> it = lst.listIterator();
        while (it.hasNext()) {
            E elt = it.next();
            if (!seen.add(elt)) {
                System.out.println(String.format("Element %s at %d is a duplicate", elt, it.previousIndex()));
            }
        }
    }



    public static void main(String[] args) {
        findPrimes(30);
    }

}