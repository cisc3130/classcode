import java.util.*;

public class MapPractice {

    Map<String, Long> dictionary;

    public void loadBook(String filename) {
        Map<String, Long> wordCounts = new HashMap<>();
        Scanner sc = new Scanner(filename);
        while (sc.hasNext()) {
            String word = sc.next().toLowerCase();
            // if (wordCounts.containsKey(word)) {
            //     wordCounts.put(word, wordCounts.get(word) + 1);
            // } else {
            //     wordCounts.put(word, Long.valueOf(1));
            // }    // this is three queries
            // wordCounts.put(word, wordCounts.getOrDefault(word, 0L) + 1); // this is two queries
            wordCounts.compute(word, (k, v) -> v == null ? 1 : v + 1); // this is one query

            // // recreate putIfAbsent
            // wordCounts.compute(word, (k, v) -> v == null ? newValue : v);

            // // recreate putIfPresent (replace)
            // wordCounts.compute(word, (k, v) -> v == null ? null : newValue);

            // // recreate replace (key, oldValue, newValue)
            // wordCounts.compute(word, (k, v) -> v.equals(oldValue) ? newValue : v);
        }

    }

    protected V complicatedValueMapping(K key, V oldValue) {
        if (key.startsWith('L') && oldValue > 10) {
            return "hello";
        }
    }

    map.compute(key, new class BiFunction<K, V> {
        public V apply(K key, V oldValue) {
            return new V();
        }
    });

    public static void main(String[] args) {
        Map<String, Double> items = new HashMap<>();
        items.put("ruler", 5.99);
        items.put("phone", 600.99);
        items.put("desk", 3999.99);
        System.out.println("The cost of a ruler is $" + items.get("ruler"));
        Double phonePrice = items.get("phone");
        if (phonePrice == null) System.out.println("We don't sell phones");
        else System.out.println("The cost of a phone is $" + phonePrice);
        Double oldRulerPrice = items.put("ruler", 6.99);
        System.out.println("Rulers used to cost " + oldRulerPrice + ", now they cost " + newRulerPrice);
        Double oldDeskPrice = items.remove("desk");
        System.out.println("We no longer sell desks. They used to cost " + oldDeskPrice);
        assert(items.get("desk") == null);
    }
}