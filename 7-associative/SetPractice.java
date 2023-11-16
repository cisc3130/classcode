import java.util.*;
import java.util.stream.*;
import java.io.*;


public class SetPractice {

    public static void countDuplicates() {
        String poem = "on either side the river lie long fields of barley and of rye that clothe the wold and meet the sky and through the hill the road runs by to many towered camelot";
        String[] poemWords = poem.split(" ");
        int numWords = poemWords.length;
        Set<String> wordSet = new TreeSet<>();
        for (String w : poemWords) wordSet.add(w);
        int uniqueWords = wordSet.size();
        System.out.println(String.format("There are %d words and %d unique words in the string. The ratio of unique words to total words is %.2f", numWords, uniqueWords, (uniqueWords*1.0)/numWords));
    }

    public void basicPractice() {
        Set<String> s = new TreeSet<>();
        String[] words = { "hello", "goodbye", "coffee", "cat", "dog", "coffee", "desk"};
        for (String w : words) s.add(w);
        System.out.println(s.contains("hello"));
        System.out.println(s.contains("computer"));
        for (String w : s) {
            System.out.print(w + ", ");
        }
        System.out.println();
    }

    static SortedSet<String> englishWords;

    public static void spellcheckDemo() {
        if (englishWords == null) {
            englishWords = new TreeSet<>();
            try {
                Scanner scanner = new Scanner(new File("../words_alpha.txt"));
                while (scanner.hasNextLine()) {
                    englishWords.add(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter a word");
            String inputWord = scanner.nextLine();
            String checkedWord = spellcheck(inputWord);
            System.out.println(checkedWord.equals(inputWord) ? "Correct" : "Did you mean " + checkedWord);
        } while (scanner.hasNextLine());
    }

    public static String spellcheck(String word) {
        boolean c = englishWords.contains(word);
        if (c) return word;
        return englishWords.headSet(word).last();
    }

    public static void removeDuplicates() {
        String poem = "on either side the river lie long fields of barley and of rye that clothe the wold and meet the sky and through the hill the road runs by to many towered camelot";
        Set<String> s = new TreeSet<>();
        for (String w : poem.split(" ")) s.add(w);
        poem = String.join(", ", s);
        System.out.println(poem);
    }

    public static void removeDuplicatesWithoutSorting() {
        String poem = "on either side the river lie long fields of barley and of rye that clothe the wold and meet the sky and through the hill the road runs by to many towered camelot";
        
        // add all words in the string to a linked list
        String[] words = poem.split(" ");
        List<String> wordList = new LinkedList<>();
        for (String w : words) wordList.add(w);

        // iterate over the list. If a word has been seen before,
        // remove it.
        Set<String> seen = new TreeSet<>();
        ListIterator<String> it = wordList.listIterator();
        while (it.hasNext()) {
            String w = it.next();
            if (seen.contains(w)) {
                it.remove();
            } else {
                seen.add(w);
            }
        }
        String poemWithoutDuplicates = wordList.stream().collect(Collectors.joining(" "));
        System.out.println(poem);
        System.out.println(poemWithoutDuplicates);
    }


    public static void main(String[] args) {
        removeDuplicatesWithoutSorting();
    }
}