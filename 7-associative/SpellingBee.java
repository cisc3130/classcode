import java.util.*;
import java.io.*;

public class SpellingBee {
    String puzzleString;
    Set<Character> puzzle;
    Character centerLetter;
    Set<String> dictionary;
    Set<String> alreadyFound;
    int points;

    static final int MIN_LENGTH = 4;

    public SpellingBee(String puzzle) {
        this.puzzleString = puzzle;
        this.puzzle = new TreeSet<>();
        for (char c : puzzle.toCharArray()) this.puzzle.add(c);
        centerLetter = puzzle.charAt(0);

        dictionary = loadDictionary();

        alreadyFound = new TreeSet<>();

        points = 0;
    }

    public static Set<String> loadDictionary() {
        String dictfile = "/workspaces/classcode/words_alpha.txt";
        Set<String> dictionary = new TreeSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(dictfile));
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.toUpperCase());
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading dictionary file: " + e);
        }
        return dictionary;
    }

    public String score(String word) {
        int wordLength = word.length();
        if (wordLength < MIN_LENGTH) return "Too short";

        boolean containsCenterLetter = false;
        for (char c : word.toCharArray()) {
            if (!puzzle.contains(c)) return "Bad letters";
            if (c == centerLetter) containsCenterLetter = true;
        }
        if (!containsCenterLetter) return "Missing center letter";


        if (alreadyFound.contains(word)) return "Already found";

        // is it a legal word
        if (!dictionary.contains(word)) { return "Not in word list"; }

        alreadyFound.add(word);
        int points;
        String message = "";
        if (wordLength == 4) points = 1;
        else points = wordLength;

        // if it's a pangram, add another 7 points
        Set<Character> wordChars = new HashSet<>();
        for (char c : word.toCharArray()) wordChars.add(c);
        if (wordChars.size() == 7) {
            
        }            points += 7;
            message = "Pangram! ";
        }

        this.points += points;
        return message + points + " points";
        
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean keepGuessing = true;
        while (keepGuessing) {
            System.out.println(puzzleString);
            System.out.println("Enter your guess (or Q to quit): ");
            String word = sc.next();
            if (word.equals("Q")) keepGuessing = false;
            else System.out.println(score(word.toUpperCase()));
        }
        System.out.println("You scored " + points + " points");
        sc.close();
    }

    public static void main(String[] args) {
        SpellingBee sb = new SpellingBee("PKINGRA");
        sb.play();
    }
}