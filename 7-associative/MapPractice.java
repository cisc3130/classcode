import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MapPractice {

    static Set<String> dictionary = loadDictionary();
    static Map<Character, Integer> charIdxMap;

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

    protected static Integer incrementCount(String word, Integer count) {
        if (count == null) {
            return 1;
        } else {
            return count+1;
        }
    }

    public String caesarCipherLinear(String toEncode, int secret) {
        long startTime = System.currentTimeMillis();
        StringBuilder encoded = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char c : toEncode.toCharArray()) {
            int c_idx = alphabet.indexOf(c);            // indexOf is linear in the alphabet
            char encoded_c = alphabet.charAt((c_idx + secret) % alphabet.length());
            encoded.append(encoded_c);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Linear time: " + (endTime - startTime) + " ms");
        return encoded.toString();
    }

    public String caesarCipher(String toEncode, int secret) {
        long startTime = System.currentTimeMillis();
        StringBuilder encoded = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        if (charIdxMap == null) {
            charIdxMap = new HashMap<>();
            for (int i = 0; i < alphabet.length(); i++) {
                charIdxMap.put(alphabet.charAt(i), i);
            }
        }
        for (char c : toEncode.toCharArray()) {
            int c_idx = charIdxMap.get(c);          // get is constant in a hash map
            char encoded_c = alphabet.charAt((c_idx + secret) % alphabet.length());
            encoded.append(encoded_c);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Constant time: " + (endTime - startTime) + " ms");
        return encoded.toString();
    }

    public Map<String, List<Integer>> buildConcordance(String filename) {
        Map<String, List<Integer>> concordance = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int lineNumber = 1;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            for (String word : words) {
                // for every word on line #x, add x to that word's list of line numbers
                word = word.toLowerCase();
                // if (concordance.containsKey(word)) {
                //     List<Integer> lst = concordance.get(word);
                //     lst.add(lineNumber);
                // } else {
                //     List<Integer> lst = new ArrayList<>();
                //     lst.add(lineNumber);
                //     concordance.put(word, lst);
                // }    ^ This is inefficient, requires two searches
                List<Integer> lineList = concordance.computeIfAbsent(word, word -> new LinkedList<Integer>());
                lineList.add(lineNumber);
            }
            lineNumber++;
        }
    }

    public <K, V> void printMap(Map<K, V> m) {
        for (K key : m.keySet()) {
            System.out.println(key + ": " + m.get(key));
        }
        for (Map.Entry<K, V> entry : m.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public <K> void incrementMap(Map<K, Integer> m) {
        for (K key : m.keySet()) {
            m.compute(key, (k, v) -> v+1);
        }
        for (Map.Entry<K, Integer> entry : m.entrySet()) {
            entry.setValue(entry.getValue()+1);
        }
    }

    public <K> void removeItemsGreaterThan(Map<K, Float> m, float threshold) {
        Collection<Float> c = m.values();
        Iterator<Float> it = c.iterator();
        while (it.hasNext()) {
            if (it.next().compareTo(threshold) > 0) {
                it.remove();
            }
        }
    }

    public static Map<String, Integer> wordCountBook(int gutenbergCodeString) {
        // load book from Project Gutenberg
        String gutenberg_url = "https://www.gutenberg.org/cache/epub/" + gutenbergCodeString + "/pg" + gutenbergCodeString + ".txt";

        Map<String, Integer> wordCount = new HashMap<>();
        try (Scanner sc = new Scanner(new URL(gutenberg_url).openStream())) {
            while (sc.hasNext()) {
                String word = sc.next().toLowerCase().replaceAll("[^a-z]", "");  // remove punctuation
                
                // if (wordCount.containsKey(word)) {
                //     Integer currentCount = wordCount.get(word);
                //     wordCount.put(word, currentCount + 1);
                // } else {
                //     wordCount.put(word, 1);
                // }   ^ This is inefficient, requires three or two searches

                // Integer currentCount = wordCount.get(word);
                // if (currentCount == null) {
                //     wordCount.put(word, 1);
                // } else {
                //     wordCount.put(word, currentCount + 1);
                // }  ^ This is more efficient, eliminates containsKey search, but still requires two searches

                // Integer currentCount = wordCount.getOrDefault(word, 0);
                // wordCount.put(word, currentCount + 1);   ^ This is more efficient, eliminates the branch but still requires two searches

                wordCount.compute(word, (k, v) -> v == null ? 1 : v+1);   // This is the most efficient way, does everything in one search
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        // print top five most frequent words in the book
        wordCount.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .limit(5)
            .forEach(System.out::println);

        return wordCount;
    }

    public static Map<String, Integer> createTitleToGutenbergCodeMap() {
        return Map.of(
            "Frankenstein", 84,
            "Moby Dick", 2701,
            "Pride and Prejudice", 1342,
            "Romeo and Juliet", 1513,
            "Crime and Punishment", 2554,
            "Alice in Wonderland", 11,
            "The Count of Monte Cristo", 1184,
            "Middlemarch", 145
        );
    }

    public static void getMostSignificantWords(String title) {
        // create corpus of word counts of a bunch of books
        Map<Integer, Map<String, Integer>> allWordCounts = new HashMap<>();
        for (Integer gutenbergCode : createTitleToGutenbergCodeMap().values()) {
            allWordCounts.put(gutenbergCode, wordCountBook(gutenbergCode));
        }

        // get a reference to the word count map of this book
        Integer thisGutenbergCode = createTitleToGutenbergCodeMap().get(title);
        Map<String, Integer> thisWordCounts = allWordCounts.get(thisGutenbergCode);

        // get total number of words in the book
        int totalWordsInBook = thisWordCounts.values().stream()
            .mapToInt(Integer::intValue)
            .sum();

        // calculate tf-idf for each word in the book
        Map<String, Double> thisTfIdf = new HashMap<>();
        for (Map.Entry<String, Integer> e : thisWordCounts.entrySet()) {
            double tf = (double) e.getValue() / totalWordsInBook;
            double numberOfDocumentsContainingWord = allWordCounts.values().stream()
                .filter(m -> m.containsKey(e.getKey()))
                .count();
            double idf = Math.log(allWordCounts.size() / numberOfDocumentsContainingWord);
            thisTfIdf.put(e.getKey(), tf * idf);
        }

        // print top five most important words in the book
        thisTfIdf.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .limit(5)
            .forEach(System.out::println);
    }

    public static void findAnagrams() {
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String word : dictionary) {
            // get the sorted string of the word
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            String sortedWord = new String(wordChars);

            // if sortedWord is already in the map with an associated list of anagrams,
            // add word to that list
            // otherwise, add a new list to the map keyed by sortedWord 
            // and add word to that list
            anagramsMap.computeIfAbsent(sortedWord, k -> new LinkedList<>()).add(word);
        }

        // find the longest word in English that has at least one anagram
        System.out.println("The longest word in English with at least one anagram is: " + 
        anagramsMap.entrySet().stream()
            .filter(e -> e.getValue().size() > 1)
            .max((e1, e2) -> e1.getKey().length() - e2.getKey().length())
            .get()
        );

        // find the largest set of anagrams
        System.out.println("The largest set of anagrams in English is: " +
        anagramsMap.values().stream().max((lst1, lst2) -> lst1.size() - lst2.size()).get()
        );
    }




    public static Map<Integer, Integer> fibMemo = new TreeMap<>();
    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        Integer fib_n = fibMemo.get(n);
        if (fib_n != null) return fib_n;
        fib_n = fib(n-1) + fib(n-2);
        fibMemo.put(n, fib_n);
        return fib_n;
    }

    public static Map<String, Boolean> reducibleMemo = new HashMap<>();
    public static boolean isReducible(String word) {
        if (word.length() == 0) return true;
        Boolean reducible = reducibleMemo.get(word);
        if (reducible != null) return reducible;
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            String new_word = sb.toString();
            if (!dictionary.contains(new_word)) continue;
            boolean new_word_is_reducible = isReducible(new_word);
            if (!new_word_is_reducible) continue;
            reducibleMemo.put(word, true);
            return true;
        }
        reducibleMemo.put(word, false);
        return false;
    }


    public static void main(String[] args) {
        // Map<String, Double> items = new TreeMap<>();
        // items.put("ruler", 5.99);
        // // items.put("phone", 600.99);
        // items.put("desk", 3999.99);
        // items.put("computer", 1299.99);

        // System.out.println("The cost of a ruler is $" + items.get("ruler"));

        // Double phonePrice = items.get("phone");
        // if (phonePrice == null) System.out.println("We don't sell phones");
        // else System.out.println("The cost of a phone is $" + phonePrice);

        // Double newRulerPrice = 6.99;
        // Double oldRulerPrice = items.put("ruler", newRulerPrice);
        // if (oldRulerPrice != null) {
        //     System.out.println("Rulers used to cost " + oldRulerPrice + ", now they cost " + newRulerPrice);
        // }

        // Double oldDeskPrice = items.remove("desk");
        // System.out.println("We no longer sell desks. They used to cost " + oldDeskPrice);
        // assert(items.get("desk") == null);

        // String toEncode = "hello";
        // int secret = 3;
        // MapPractice mp = new MapPractice();
        // System.out.println(mp.caesarCipherLinear(toEncode, secret));
        // System.out.println(mp.caesarCipher(toEncode, secret));  

        // String toEncodeLong = "the quick brown fox jumps over the lazy dog";
        // System.out.println(mp.caesarCipherLinear(toEncodeLong, secret));
        // System.out.println(mp.caesarCipher(toEncodeLong, secret));


        // System.out.println("Frankenstein: ");
        // getMostSignificantWords("Frankenstein");
        // System.out.println("Alice in Wonderland: ");
        // getMostSignificantWords("Alice in Wonderland");

        


        // System.out.println(isReducible("sprite"));

        // dictionary.stream()
        //     .filter(MapPractice::isReducible)
        //     .sorted((w1, w2) -> w2.length() - w1.length())
        //     .limit(5)
        //     .forEach(System.out::println);

        findAnagrams();
    }
}