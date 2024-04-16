import java.util.*;

public class Wordle {
    String puzzle;
    Set<String> dictionary;
    Set<Character> puzzleChars;

    public Wordle(String puzzle) {
        this.puzzle = puzzle;
        puzzleChars = new TreeSet<>();
        for (char c : puzzle.toCharArray()) puzzleChars.add(c);
    }

    public boolean checkGuess(String guess) {
        if (!dictionary.contains(guess)) {
            System.out.println("Word not in dictionary");
            return false;
        }
        String result = "";
        for (int i = 0; i < puzzle.length(); i++) {
            if (guess.charAt(i) == puzzle.charAt(i)) {
                result += "greenSquare";
            } else if (!puzzleChars.contains(guess.charAt(i))) {
                    result += "graySquare";
            } else {
                result += "yellowSquare";
            }
        }
    }
}