import java.util.*;

public class SpellingBee {
    Set<String> dictionary;
    Set<String> alreadyFound;

    public SpellingBee() {
        dictionary = loadDictionary();
        alreadyFound = new TreeSet<>();
    }

    private Set<String> loadDictionary() {

    }

    public boolean isLegalWord(String word) {
        return dictionary.contains(word);
    }

    public boolean isAlreadyFound(String word) {
        return alreadyFound.contains(word);
    }
}