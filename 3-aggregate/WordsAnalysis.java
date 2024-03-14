import java.util.*;
import java.io.*;

public class WordsAnalysis {
    List<String> words;


    public WordsAnalysis() {
        words = new MArrayList<>();
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

        // average length of words that start with x
        w.words.stream()
            .filter(s -> s.length() > 12 && s.charAt(0) == s.charAt(s.length()-1))
            .filter(s -> s.charAt(0) != 't' && s.charAt(0) != 's')
            .forEach(System.out::println);

        // average length of words in list
        w.words.stream()
            .mapToDouble(s -> s.length())
            .average()
            .getAsDouble();
        
    }

}