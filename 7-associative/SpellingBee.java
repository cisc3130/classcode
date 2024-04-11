import java.util.*;

public class SpellingBee {
    Set<Character> puzzle;
    Character centerLetter;
    Set<String> dictionary;
    Set<String> alreadyFound;
    int points;

    static final int MIN_LENGTH = 4;

    public SpellingBee(String puzzle) {
        this.puzzle = new TreeSet<>();
        for (char c : puzzle.toCharArray()) this.puzzle.add(c);
        centerLetter = puzzle.charAt(0);

        dictionary = loadDictionary();

        alreadyFound = new TreeSet<>();

        points = 0;
    }

    public static Set<String> loadDictionary() {
        String dictfile = "../words_alpha.txt";
        Set<String> dictionary = new HashSet<>();
        try (Scanner sc = new Scanner(SpellingBee.class.getResource
                (dictfile).openStream())) {
            while (sc.hasNext()) {
                dictionary.add(sc.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public String score(String word) {
        if (word.length() < MIN_LENGTH) return "Too short";

        boolean containsCenterLetter = false;
        for (char c : word.toCharArray()) {
            if (!puzzle.contains(c)) return "Bad letters";
            if (c == centerLetter) containsCenterLetter = true;
        }
        if (!containsCenterLetter) return "Missing center letter";


        if (alreadyFound.contains(word)) return "Already found";

        // is it a legal word
        if (!dictionary.contains(word)) { return "Not in word list"; }
        
    }

}