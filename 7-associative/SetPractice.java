import java.util.*;

public class SetPractice {

    static Set<String> dictionary = loadDictionary();;

    

    public static Set<String> loadDictionary() {
        String dictfile = "../words_alpha.txt";
        Set<String> dictionary = new TreeSet<>();
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
        Set<Integer> sieve = new HashSet<>();
        // fill up the set with integers up to ceiling
        for (int i = 2; i < ceiling; i++) {
            sieve.add(i);
        }

        // iterate over factors up to sqrt(ceiling) and remove all multiples
        for (int f = 2; f < Math.sqrt(ceiling); f++) {
            if (sieve.contains(f)) {
                for (int m = f*2; m < ceiling; m += f) {
                    sieve.remove(m);
                }
            }
        }

        // whatever is left in the set is prime
        return sieve;
    }

    public void guessingGame() {
        Random r = new Random();
        int secretNumber = r.nextInt(100) + 1;
        int numGuesses = 0;
        System.out.println("Guess a number between 1 and 100");
        System.out.println("You guessed " + numGuesses + " times so far");
        Scanner sc = new Scanner(System.in);
        int guess = sc.nextInt();
        Set<Integer> alreadyGuessed = new TreeSet<>();
        while (guess != secretNumber) {
            boolean seen = !alreadyGuessed.add(guess);
            if (!seen) {
                numGuesses++;
                System.out.println("Wrong. You have guessed " + numGuesses + " times so far.");
                alreadyGuessed.add(guess);
            } else {
                System.out.println("You already guessed that.");
            }
            System.out.println("Guess a number between 1 and 100");
            guess = sc.nextInt();
        }
    }

    

    public static List<Integer> checkDocument(List<String> doc) {
        ListIterator<String> it = doc.listIterator();
        List<Integer> misspelledIdx = new LinkedList<>();

        while (it.hasNext()) {
            String word = it.next();
            if (!dictionary.contains(word)) {
                System.out.println(word + " is misspelled. Do you want to add it to the dictionary?");
                Scanner sc = new Scanner(System.in);
                String response = sc.next();
                if (response.equals("yes")) {
                    dictionary.add(word);
                }
                else { misspelledIdx.add(it.previousIndex()); }
            }
        }

        return misspelledIdx;
    }

    public static <E> boolean containsDuplicates(List<E> lst) {
        Set<E> s = new TreeSet<>(lst);
        return s.size() < lst.size();

        Set<E> s1 = new TreeSet<>();
        for (E elt : lst) {
            boolean alreadySeen = !s1.add(elt);
            if (alreadySeen) {
                return true;
            }
        }
        return false;
    }

    public static <E> void findDuplicates(List<E> lst) {
        Set<E> seen = new TreeSet<>();
        ListIterator<E> it = lst.listIterator();
        while (it.hasNext()) {
            E elt = it.next();
            boolean alreadySeen = !seen.add(elt);
            if (alreadySeen) {
                System.out.println(String.format("Element %s at %d is a duplicate", elt, it.previousIndex()));
            }
        }
    }



    public static void main(String[] args) {
        findPrimes(30);
    }

}