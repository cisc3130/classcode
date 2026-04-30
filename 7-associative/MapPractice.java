import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MapPractice {

    Map<String, Long> dictionary;
    static Map<Character, Integer> charIdxMap;


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

    public Map<String, Integer> wordCountBook(int gutenbergCodeString) {
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
        return wordCount;
    }

    // protected V complicatedValueMapping(K key, V oldValue) {
    //     if (key.startsWith('L') && oldValue > 10) {
    //         return "hello";
    //     }
    // }


    public static Map<Integer, Integer> fibMemo = new TreeMap<>();
    public static int fib(int n) {
        if (n == 0 || n == 1) return 1;
        // if (fibMemo.containsKey(n)) return fibMemo.get(n);
        
        // Integer m = fibMemo.get(n);
        // if (m != null) return m;
        // int fibRes = fib(n-1) + fib(n-2);
        // fibMemo.put(n, fibRes);
        // return fibRes;

        Integer fibRes = fibMemo.computeIfAbsent(n, k -> fib(n-1) + fib(n-2));
        return fibRes;
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

        MapPractice mp = new MapPractice();
        Map<String, Integer> frankensteinWordCount = mp.wordCountBook(84);
        int i = 0;
        for (Map.Entry<String, Integer> entry : frankensteinWordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            i++;
            if (i > 5) break;
        }
        Set<Map.Entry<String, Integer>> frankensteinWordCountEntrySet = frankensteinWordCount.entrySet();
        Iterator<Map.Entry<String, Integer>> it = frankensteinWordCountEntrySet.iterator();
        Map.Entry<String, Integer> maxEntry = it.next();
        for (Map.Entry<String, Integer> entry : frankensteinWordCount.entrySet()) {
            if (maxEntry == null || maxEntry.getValue().compareTo(entry.getValue()) < 0) {
                maxEntry = entry;
            }
        }
        System.out.println("The word that appears the most times in Frankenstein is " + maxEntry.getKey());
    }
}