import java.util.*;

public class SetPractice {

    static Set<String> dictionary = loadDictionary();

    

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
        Set<Integer> sieve = new TreeSet<>();
        for (int i = 2; i < ceiling; i++) {
            sieve.add(i);
        }
        for (int f = 2; f < Math.sqrt(ceiling); f++) {
            for (int c = f * 2; c < ceiling; c = c + f) {
                sieve.remove(c);
            }
        }

        // all composite numbers have now been removed from the sieve
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
    }

    public static <E> List<E> findDuplicatesWithoutSets(List<E> lst) {
        long startTime = System.nanoTime();
        List<E> duplicates = new LinkedList<>();
        for (ListIterator<E> it1 = lst.listIterator(); it1.hasNext(); ) {
            E currElt = it1.next();
            ListIterator<E> it2 = lst.listIterator(it1.nextIndex());
            while (it2.hasNext()) {
                if (it2.next().equals(currElt)) {
                    duplicates.add(it2.previous());
                    break;
                }
            }
        }
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time without set: " + (timeElapsed / 1_000_000.0) + " ms");
        return duplicates;
    }

    public static <E> List<E> findDuplicates(List<E> lst) {
        long startTime = System.nanoTime();
        List<E> duplicates = new LinkedList<>();
        Set<E> seen = new TreeSet<>();
        for (E elt : lst) {
            boolean alreadySeen = !seen.add(elt);
            if(alreadySeen) {
                duplicates.add(elt);
            }
        }
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time with set: " + (timeElapsed / 1_000_000.0) + " ms");
        return duplicates;
    }



    public static void main(String[] args) {
        // findPrimes(30);
        // List<Integer> lst = new LinkedList<>();
        // Random r = new Random();
        // for (int i = 0; i < Math.pow(10, 6); i++) {
        //     lst.add(r.nextInt(10000));
        // }
        // System.out.println("There are " + findDuplicates(lst).size() + " duplicates");
        // System.out.println("There are " + findDuplicatesWithoutSets(lst).size() + " duplicates");

    }

}