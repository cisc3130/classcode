import java.util.*;
import java.io.*;

public class SpellingBee {
    private String puzzleString;
    private Set<Character> puzzleSet;
    private char centerLetter;
    private Set<String> dictionary;
    private int points;
    private Set<String> alreadyFound;
    private static final int MIN_LENGTH = 4;

    public SpellingBee(String puzzleString) {
        this.puzzleString = puzzleString;
        this.puzzleSet = new HashSet<>();
        for (char c : puzzleString.toCharArray()) puzzleSet.add(c);
        this.centerLetter = puzzleString.charAt(0);
        this.dictionary = loadDictionary();
        this.points = 0;
        this.alreadyFound = new HashSet<>();
    }

    private static Set<String> loadDictionary() {
        String dictfile = "/workspaces/classcode/words_alpha.txt";
        Set<String> dictionary = new HashSet<>();
        try {
            File file = new File(dictfile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.toUpperCase());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private String score(String guess) {
        String message;

        if (guess.length() < MIN_LENGTH) {
            return "Too short";
        }

        boolean usedCenterLetter = false;
        for (char c : guess.toCharArray()) {
            if (c == centerLetter) usedCenterLetter = true;
            else {
                if (!puzzleSet.contains(c)) {
                    return "Bad letters";
                }
            }
        }
        if (!usedCenterLetter) {
            return "Missing center letter";
        }

        if (!dictionary.contains(guess)) {
            return "Not in word list";
        }

        if (!alreadyFound.add(guess)) {
            return "Already found";
        }

        // survived all checks: this is a valid guess
        // now score

        int guessPoints;
        if (guess.length() == 4) guessPoints = 1;
        else guessPoints = guess.length();

        Set<Character> guessSet = new TreeSet<>();
        for (char c : guess.toCharArray()) guessSet.add(c);
        if (guessSet.size() == puzzleSet.size()) {
            guessPoints += puzzleSet.size();
            message = "Pangram! " + guessPoints;
        } else {
            message = "" + guessPoints;
        }


        points += guessPoints;
        return message;

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
        SpellingBee sb = new SpellingBee("CINLEMT");
        sb.play();
    }
}