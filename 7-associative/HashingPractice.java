

public class HashingPractice {
    class Desk {
    String material;
    int numDrawers;

    public Desk(String material, int numDrawers) {
        this.material = material;
        this.numDrawers = numDrawers;
    }

}
    public static void main(String[] args) {
        Integer x = 75, y = 35, z = 2, x2 = 75;
        String hello = "hello", goodbye = "goodbye", computer = "computer", hello2 = "hello";
        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(z.hashCode());
        System.out.println(x2.hashCode());
        System.out.println(hello.hashCode());
        System.out.println(hello2.hashCode());
        hello2 += "!";
        System.out.println(hello2.hashCode());
        hello2 = "goodbye";
        System.out.println(hello2.hashCode());
        System.out.println(goodbye.hashCode());
        System.out.println(computer.hashCode());

        // Desk d1 = new Desk("oak", 3);
        // Desk d2 = new Desk("pine", 4);
        // Desk d3 = new Desk("oak", 3);
        // System.out.println(d1.hashCode());
        // System.out.println(d2.hashCode());
        // System.out.println(d3.hashCode());



        System.out.println(x.hashCode() % 10);
        System.out.println(y.hashCode() % 10);
        System.out.println(z.hashCode() % 10);
        System.out.println(hello.hashCode() % 10);
        System.out.println(goodbye.hashCode() % 10);
    }
}