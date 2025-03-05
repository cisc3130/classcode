import java.util.*;
import java.util.LinkedList;
import java.io.*;

public class WordsAnalysis {
    List<String> words;


    public WordsAnalysis() {
        words = new LinkedList<>();
        File f = new File("/workspaces/classcode/words_alpha.txt");
        try {
            Scanner freader = new Scanner(f);
            while (freader.hasNextLine()) {
                words.add(freader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        WordsAnalysis w = new WordsAnalysis();

        // average length of words 
        double wordLengthSum = 0.0;
        for (String word : w.words) wordLengthSum += word.length();
        double average = wordLengthSum / w.words.size();

        // average length of words using streams
        System.out.println(w.words.stream()
            .mapToInt(String::length)
            .average()
            .getAsDouble()
            );
        
        // how many words start with X
        System.out.println(w.words.stream()
            .filter(x -> x.startsWith("x"))
            .count()
             + " words start with 'x'");

        // do any words have 5 letters and start with 'dh'
        System.out.println(
            w.words.stream()
                .anyMatch(x -> x.length() == 5 && x.startsWith("dh"))
        );

        // print out words that have 5 letters and start with 'dh'
        w.words.stream()
            .filter(x -> x.length() == 5 && x.startsWith("dh"))
            .forEach(System.out::println);


        transactions.stream()
            .filter(t -> t.getCategory().equals("gasoline") && t.getYear() == 2022)
            .mapToDouble(Transaction::getAmount)
            .sum();

    }

}