import java.util.*;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Person {

  public enum Sex {
    MALE, FEMALE
  }
  
  String name;
  int age;
  Sex gender;
  String email;
  
  public int getAge() {
    return age;
  }

  public String getEmail() { return email; }

  public String getName() { return name; }

  public void setName(String newName) { this.name = newName; }
  
  public void printPerson() {
    System.out.println("Name: " + name);  
    System.out.println("Age: " + age);
    System.out.println("Email: " + email);
  }

  public Person() {
    Random r = new Random();
    int nameLength = 5;
    StringBuilder buf = new StringBuilder(nameLength);
    for (int i = 0; i < nameLength; i++) {
      char randChar = (char) (97 + (int) (r.nextFloat() * (122-97+1)));
      buf.append(randChar);
    }
    name = buf.toString();

    age = Math.abs(r.nextInt() % 100);

    email = name + "@company.com";
  }

  public static void printIfOlderThan(List<Person> peopleList, int age) {
    for (Person p : peopleList) {
        if (p.getAge() > age) p.printPerson();
    }
  }

  public static void printIfBetweenAges(List<Person> people, int startAge, int endAge) {
    for (Person p : people) {
        if (p.getAge() >= startAge && p.getAge() < endAge) p.printPerson();
    }
  }

  public static void printIfValidAge(List<Person> people, Predicate<Integer> filter) {
    for (Person p : people) {
        if (filter.test(p.getAge())) p.printPerson();
    }
  }

  public static void printWithFilter(List<Person> people, Predicate<Person> filter) {
    for (Person p : people) {
        if (filter.test(p)) p.printPerson();
    }
  }

  public static void processWithFilter(List<Person> roster,
        Predicate<Person> filter,
        Consumer<Person> action) {
    for (Person p : roster) {
      if (filter.test(p))
        action.accept(p);
    }
  }

  public static void main(String[] args) 
  {
    List<Person> people = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
      people.add(new Person());
    }

    // for (Person p : people) {
    //     p.printPerson();
    // }

    // print people older than 30
    // printIfOlderThan(people, 30)

    // print people older than 70
    // printIfOlderThan(people, 70);
    // printIfBetweenAges(people, 70, 100);

    // printIfOlderThan(people, 25);

    // // print people younger than 25
    // printIfBetweenAges(people, 0, 25);

    
    // System.out.println("People younger than 25");
    // printIfValidAge(people, new Predicate<Integer>() {
    //     public boolean test(Integer i) { return i < 25; }
    // }); // print people younger than 25
    // System.out.println("People older than 70");
    // printIfValidAge(people, (Integer i) -> i > 70); // print people older than 90
    // System.out.println("People whose age is divisible by 5");
    // printIfValidAge(people, (Integer i) -> i % 5 == 0); // print people whose age is divisible by 5

    // System.out.println("People older than 90");
    // printWithFilter(people, (Person p) -> p.getAge() > 90);
    // System.out.println("People whose name starts with 'a'");
    // printWithFilter(people, (Person p) -> p.getName().startsWith("a"));


    // System.out.println("Print people whose name starts with 'p'");
    // processWithFilter(people, 
    //         (Person p) -> p.getName().startsWith("p"), 
    //         (Person p) -> p.printPerson());
    // System.out.println("If someone's name starts with p, change it to 'PETROL'");
    // processWithFilter(people, 
    //         (Person p) -> p.getName().startsWith("p"),
    //         (Person p) -> p.setName("petrol"));
    // processWithFilter(people, 
    //         (Person p) -> p.getName().startsWith("p"), 
    //         (Person p) -> p.printPerson());

        System.out.println("Use aggregate operations");
        people.stream()
              .filter(p -> p.getAge() < 30 && p.getAge() > 10)
              //.map(p -> p.getEmail()) 
              .map(Person::getEmail)
              .map(email -> email.replaceAll("@company.com", ""))
              .forEach(System.out::println);
  }

}