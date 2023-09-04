public class OrderedPair<K, V> {
  private K key;
  private V value;

  public OrderedPair(K key, V value) {
    this.key = key;
    this.value=value;
  }

  public K getKey() { return key; }
  public V getValue() { return value; }

  public void setKey(K key) { this.key = key; }

  public static void main(String[] args) {
    OrderedPair<Integer, String> op = new OrderedPair<>(3, "hello");

    String str = op.getValue();
  }

}
