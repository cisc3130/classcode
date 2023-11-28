import java.util.*;
import java.util.stream.*;
import java.io.*;

public class SpellingBee {
    Set<String> dictionary;
    Set<String> alreadyFound;
    String puzzle;
    int total;

    public SpellingBee(String puzzle) {
        loadDictionary();
        alreadyFound = new TreeSet<>();
        this.puzzle = puzzle.toUpperCase();
        total = 0;
    }

    public String processString(String str) {
        return str.toUpperCase();
    }

    private void loadDictionary() {
        dictionary = new HashSet<String>();
        try {
            Scanner scanner = new Scanner(new File("../words_alpha.txt"));
            while (scanner.hasNext()) {
                dictionary.add(scanner.next().toUpperCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int scoreWord(String word) {
        if (!(checkLetters(word) && isLegalWord(word) && addAndCheckIfNotAlreadyFound(word))) return 0;
        if (word.length() == 4) return 1;
        if (word.length() >= 7) {
            Set<Character> wordChars = new HashSet<>();
            for (char c : word.toCharArray()) wordChars.add(c);
            if (wordChars.size() == 7) {
                System.out.println("Pangram!");
                return 7 + word.length();
            }
        }
        return word.length();
    }

    public boolean isLegalWord(String word) {
        if (!dictionary.contains(word)) {
            System.out.println("Not in dictionary");
            return false;
        }
        return true;
    }

    public boolean addAndCheckIfNotAlreadyFound(String word) {
        if(!alreadyFound.add(word)) {
            System.out.println("Already found");
            return false;
        }
        return true;
    }

    public boolean checkLetters(String word) {
        boolean containsCenterLetter = false;
        for (char c : word.toCharArray()) {
            if (!puzzle.contains(c + "")) {
                System.out.println("Bad letters");
                return false;
            }
            if (c == puzzle.charAt(0)) containsCenterLetter = true;
        }
        if (!containsCenterLetter) {
            System.out.println("Does not contain center letter");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SpellingBee game = new SpellingBee("BGVNRAI");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        do {
            System.out.println("Enter a word:");
            word = scanner.next().toUpperCase();
            int score = game.scoreWord(word);
            game.total += score;
            System.out.println(String.format("%d points", score));
            System.out.println(String.format("You now have %d points", game.total));
        }
        while (word != "ENDEND");
    }
}