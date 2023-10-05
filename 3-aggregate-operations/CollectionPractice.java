import java.util.*;

public class CollectionPractice {
    class Transaction {
        double amount;

        public Transaction() {
          Random r = new Random();
          amount = 100 + Math.abs(r.nextDouble()) * (10000-100);
        }

        public Transaction(Transaction other) { this.amount = 
     other.amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public double getAmount() { return amount; }
      }

    List<Transaction> transactions;

    public CollectionPractice() {
        transactions = new ArrayList<Transaction>(100);
        for (int i = 0; i < 100; i++) transactions.add(new Transaction());
    }

    /* Given a Collection of Transactions, 
    discount by 20% transactions with an amount greater than $5000 */

    public static void main(String[] args) {
        CollectionPractice cp = new CollectionPractice();

        // how many are greater than $5000?
        int answer = (int) cp.transactions.stream()            // Stream<Transaction>
            .mapToDouble(t -> t.getAmount())    // Stream<Double>
            // alternatively:
            // .mapToDouble(Transaction::getAmount)
            .filter(a -> a > 5000)
            .count();

        System.out.println(String.format("There are %d transactions greater than $5000", answer));

        // how many are greater than $9000?
        int threshold = 5000;
        answer = (int) cp.transactions.stream()            // Stream<Transaction>
            .mapToDouble(t -> t.getAmount())    // Stream<Double>
            // alternatively:
            // .mapToDouble(Transaction::getAmount)
            .filter(a -> a > threshold)
            .count();
        System.out.println(String.format("There are %d transactions greater than $%d", answer, threshold));

        // what is the total amount of transactions greater than threshold?
        double amountGreaterThanThreshold = cp.transactions.stream()        // Stream<Transaction>
            .mapToDouble(Transaction::getAmount)        // Stream<Double>
            .filter(a -> a > threshold) 
            .sum(); 
        System.out.println(String.format("The total amount of the transactions greater than %d is $%.2f", threshold, amountGreaterThanThreshold));

        // what is the average amount of transactions greater than threshold?
        double averageAmountGreaterThanThreshold = cp.transactions.stream()        // Stream<Transaction>
            .mapToDouble(Transaction::getAmount)        // Stream<Double>
            .filter(a -> a > threshold) 
            .average().getAsDouble(); 
        System.out.println(String.format("The average amount of the transactions greater than %d is $%.2f", threshold, averageAmountGreaterThanThreshold));

        // print summary statistics about all transaction amounts
        System.out.println(cp.transactions.stream().mapToDouble(Transaction::getAmount).summaryStatistics());

        // Given a Collection of Transactions, subtract 10 from transactions with an amount greater than $5000
        cp.transactions.stream()
            .filter(t -> t.getAmount() > 5000)
            .forEach(t -> t.setAmount(t.getAmount()-10));
    
        amountGreaterThanThreshold = cp.transactions.stream()        // Stream<Transaction>
            .mapToDouble(Transaction::getAmount)        // Stream<Double>
            .filter(a -> a > threshold) 
            .sum(); 
        System.out.println(String.format("The total amount of the transactions greater than %d is $%.2f", threshold, amountGreaterThanThreshold));
    }
    
    
}