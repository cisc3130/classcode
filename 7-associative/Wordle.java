import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Wordle {
    String puzzle;
    Set<String> dictionary;
    // Set<Character> puzzleChars;
    Map<Character, Integer> puzzleCharCounts;
    final String GREENSQUARE = "🟩", YELLOWSQUARE = "🟨", GRAYSQUARE = "⬜";
    final int WORDLENGTH = 5, NUMGUESSES = 6;
    final String WINSTRING = "🟩🟩🟩🟩🟩";

    public Wordle(String puzzle) {
        this.puzzle = puzzle;
        // puzzleChars = new TreeSet<>();
        // for (char c : puzzle.toCharArray()) puzzleChars.add(c);
        puzzleCharCounts = new TreeMap<>();
        for (char c : puzzle.toCharArray()) {
            puzzleCharCounts.compute(c, (k, v) -> v == null ? 1 : v+1);
        }
        dictionary = loadDictionary();
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

    public String checkGuess(String guess) {
        if (guess.length() != WORDLENGTH) {
            System.out.println("Too short");
            return null;
        }
        if (!dictionary.contains(guess)) {
            System.out.println("Word not in dictionary");
            return null;
        }
        Map<Character, Integer> localPuzzleCharCounts = new TreeMap<>(puzzleCharCounts);    // deep copy
        // NOT Map<Character, Integer> localPuzzleCharCounts = puzzleCharCounts; // shallow copy
        String [] result = new String[WORDLENGTH];
        for (int i = 0; i < puzzle.length(); i++) {
            if (guess.charAt(i) == puzzle.charAt(i)) { 
                result[i] = GREENSQUARE;
                localPuzzleCharCounts.compute(guess.charAt(i), (k, v) -> v == 1 ? null : v - 1);
                /* 
                 * We could write this as:
                 * char c = guess.charAt(i);
                 * Integer charCount = localPuzzleCharCounts.get(c);
                 * charCount--;
                 * if (charCount == 0) localPuzzleCharCounts.remove(c);
                 * else (localPuzzleCharCounts.put(c, charCount));
                 */
            }
        }
        for (int i = 0; i < puzzle.length(); i++) {
            char c = guess.charAt(i);
            if (result[i] != null) continue;    // if result[i] currently holds a green square, don't change it
            if (localPuzzleCharCounts.containsKey(c)) {
                result[i] = YELLOWSQUARE;
                localPuzzleCharCounts.compute(c, (k, v) -> v == 1 ? null : v-1);
            } else {
                result[i] = GRAYSQUARE;
            }
        }

        String resultString = Arrays.stream(result).collect(Collectors.joining(""));
        return resultString;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        int guesses = 0;
        while (guesses < NUMGUESSES) {
            System.out.println("Enter your guess. You have " + (NUMGUESSES - guesses) + " guesses left.");
            String guess = sc.next();
            String guessResult = checkGuess(guess);
            if (guessResult != null) {
                guesses++;
                System.out.println(guessResult);
                if (guessResult.equals(WINSTRING)) {
                    System.out.println("You guessed the word in " + guesses + " guesses!");
                    sc.close();
                    return;
                }
            }
        }
        System.out.println("You lose.");
        System.out.println("The word was " + puzzle);
        sc.close();
        return;
    }

    public static void main(String[] args) {
        Wordle w = new Wordle(args[0]);
        w.play();
    }
}