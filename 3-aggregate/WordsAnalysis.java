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
                String word = freader.nextLine();
                words.add(word);
                // System.out.print(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        WordsAnalysis w = new WordsAnalysis();

        // // average length of words 
        // int sum = 0;
        // for (int i = 0; i < w.words.size(); i++) {
        //     String word = w.words.get(i);
        //     sum += word.length();
        // }
        // double average = (double) sum / w.words.size();
        // System.out.println("Average word length: " + average);

        int sum = 0;
        for (String s : w.words) {
            sum += s.length();
        }
        double average2 = (double) sum / w.words.size();
        System.out.println("Average word length (for-each): " + average2);

        double average3 = w.words.stream()
            .mapToInt(String::length)
            .average()
            .orElse(0.0);
        System.out.println("Average word length (stream): " + average3);

        // how many words start with q?
        long count = w.words.stream()
            .filter(s -> s.startsWith("q"))
            .count();
        System.out.println("Words starting with 'q': " + count);

        // do any words have 5 letters and start with "dh"
        boolean anyMatch = w.words.stream()
            .anyMatch(s -> s.length() == 5 && s.startsWith("dh"));
        System.out.println("Any words with 5 letters starting with 'dh': " + anyMatch);

        // print out the words that have 5 letters and start with "dh"
        w.words.stream()
            .filter(s -> s.length() == 5 && s.startsWith("dh"))
            .forEach(System.out::println);

        // find the longest word
        String longest = w.words.stream()
            .max(Comparator.comparingInt(String::length))
            .orElse("");
        System.out.println("Longest word: " + longest);

        
    }
}