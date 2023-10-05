import java.util.*;
import java.util.stream.*;

public class StringAggregateOperations {
    public static void main(String[] args) {
        String[] words = "on either side the river lie long fields of barley and of rye that clothe the wold and meet the sky".split(" ");

        // how many distinct words are there in this example?
        int distinct = (int) Arrays.stream(words)
            .distinct()
            .count();
        System.out.println(String.format("There are %d total words and %d distinct words in the example.", words.length, distinct));

        // how many words start with 'l'?
        int numStartWithL = (int) Arrays.stream(words)
            .distinct()
            .filter(w -> w.startsWith("a"))
            .count();
        System.out.println(String.format("%d words start with 'a'", numStartWithL));

        // average word length
        double averageWordLength = Arrays.stream(words)
            .distinct()
            .mapToInt(String::length)
            .average().getAsDouble();
        System.out.println(String.format("The average word length is %.2f", averageWordLength));

        // print words longer than c characters
        int c = 5;
        System.out.println(String.format("Words longer than %d characters:", c));
        Arrays.stream(words)
            .filter(w -> w.length() > c)
            .forEach(System.out::println);

        // list words longer than c characters
        // List<String> longWords = new ArrayList<String>();
        // for (String s : words) {
        //     if (s.length() > c) longWords.add(s);
        // }
        List<String> longWords = Arrays.stream(words)
            .filter(w -> w.length() > c)
            .collect(Collectors.toList());
        ListIterator<String> lit = longWords.listIterator();
        System.out.print("Long words: ");
        while (lit.hasNext()) {
            System.out.print(lit.next() + ", ");
        }
        System.out.println();

        // join all words starting with 's' into one string
        String wordsStartingWithS = Arrays.stream(words)
            .filter(w -> w.startsWith("s"))
            .collect(Collectors.joining(", "));
        System.out.println("Words starting with s: " + wordsStartingWithS);

        // replace all vowels with *
        System.out.println("Sorted words with vowels removed:");
        System.out.println(Arrays.stream(words)
            .sorted()
            .map(w -> w.replaceAll("[aeiou]", "*"))
            .collect(Collectors.joining(", ")));
    }
}