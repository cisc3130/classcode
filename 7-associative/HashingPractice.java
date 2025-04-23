public class HashingPractice {
    public static void main(String[] args) {
        Integer x = 75, y = 35, z = 2;
        String hello = "hello", goodbye = "goodbye", computer = "computer", hello2 = "hello";
        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(z.hashCode());
        System.out.println(hello.hashCode());
        System.out.println(hello2.hashCode());
        System.out.println(goodbye.hashCode());
        System.out.println(computer.hashCode());
        System.out.println(x.hashCode() % 10);
        System.out.println(y.hashCode() % 10);
        System.out.println(z.hashCode() % 10);
    }
}