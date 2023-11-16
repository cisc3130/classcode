import java.util.*;
import java.util.stream.*;

public class MapPractice {

    public static Map<String, Integer> countWords() {
        String poem = "on either side the river lie long fields of barley and of rye that clothe the wold and meet the sky and through the hill the road runs by to many towered camelot";
        Map<String, Integer> wordCounts = new TreeMap<>();
        for (String w : poem.split(" ")) {
            /* Inefficient solution #1: 2 or 3 searches
            if (wordCounts.containsKey(w)) {
                int count = wordCounts.get(w);
                wordCounts.put(w, count+1);
            } else {
                wordCounts.put(w, 1);
            }
             */
            /* Solution #2 is better: only 1 or 2 searches.
            Integer count = wordCounts.putIfAbsent(w, 1);
            if (count != null) wordCounts.put(w, count+1);
            */
            // Solution #3: only 1 search.
            wordCounts.compute(w, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return wordCounts;
    }

    public static Map<Integer, List<String>> invertMap(Map<String, Integer> wordCounts) {
        Map<Integer, List<String>> invertedMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (invertedMap.containsKey(entry.getKey())) {
                List<String> lst = invertedMap.get(entry.getKey());
                lst.add(entry.getKey());
            } else {
                List<String> lst = new LinkedList<>();
                lst.add(entry.getKey());
                invertedMap.put(entry.getValue(), lst);
            }
        }

        for (Map.Entry<Integer, List<String>> entry : invertedMap.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.print(entry.getValue().stream().collect(Collectors.joining(", ")));
            System.out.println();
        }
        return invertedMap;
    }

    public static String mostCommonWord(Map<String, Integer> wordCounts) {
        String mostCommonWord = "";
        Integer mostCommonValue = 0;
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue().compareTo(mostCommonValue) > 0) {
                mostCommonValue = entry.getValue();
                mostCommonWord = entry.getKey();
            }
        }
        return mostCommonWord;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(String.format("%s : %d", entry.getKey(), entry.getValue()));
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> wordCount = countWords();
        printMap(wordCount);
        System.out.println(mostCommonWord(wordCount));
        invertMap(wordCount);
    }
}