import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MapPractice {

    Map<String, Long> dictionary;

    protected static Integer incrementCount(String word, Integer count) {
        if (count == null) {
            return 1;
        } else {
            return count+1;
        }
    }

    public Map<String, List<Integer>> buildConcordance(String filename) {
        Map<String, List<Integer>> concordance = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename))
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

    public Map<String, Integer> wordCountBook(String filename) {
        // count the number of times each word appears in the file
        Map<String, Integer> wordCounts = new HashMap<>();
        Scanner sc = new Scanner(filename);
        while (sc.hasNext()) {
            String word = sc.next().toLowerCase();
            // // if the word is already in the map
            // if (wordCounts.containsKey(word)) {
            //     // get its value and increment it by 1
            //     Integer c = wordCounts.get(word);
            //     wordCounts.put(word, c+1);
            // } else {
            //     // put it in with a value of 1
            //     wordCounts.put(word, 1);
            // }
            // // ^ this is three separate searches

            // Integer c = wordCounts.get(word);
            // if (c != null) wordCounts.put(word, c+1);
            // else wordCounts.put(word, 1);
            // // ^ this is two searches

            // // same thing with getOrDefault:
            // c = wordCounts.getOrDefault(word, 0);
            // wordCounts.put(word, c+1);
            // // ^ same two searches but we can avoid the if/else by giving it a default
            // // value instead of null

            // we can get it down to one search:
            wordCounts.compute(word, (k, v) -> v == null ? 1 : v+1);

            // wordCounts.compute(word, MapPractice::incrementCount);
            
            // // same thing using merge
            // wordCounts.merge(word, 1, (v1, v2) -> v1 + 1);
        }
        return wordCounts;
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
        Map<String, Double> items = new TreeMap<>((s1, s2) -> s1.length() - s2.length());
        items.put("ruler", 5.99);
        // items.put("phone", 600.99);
        items.put("desk", 3999.99);
        items.put("computer", 1299.99);

        System.out.println("The cost of a ruler is $" + items.get("ruler"));

        Double phonePrice = items.get("phone");
        if (phonePrice == null) System.out.println("We don't sell phones");
        else System.out.println("The cost of a phone is $" + phonePrice);

        Double newRulerPrice = 6.99;
        Double oldRulerPrice = items.put("ruler", newRulerPrice);
        if (oldRulerPrice != null) {
            System.out.println("Rulers used to cost " + oldRulerPrice + ", now they cost " + newRulerPrice);
        }

        Double oldDeskPrice = items.remove("desk");
        System.out.println("We no longer sell desks. They used to cost " + oldDeskPrice);
        assert(items.get("desk") == null);
    }
}