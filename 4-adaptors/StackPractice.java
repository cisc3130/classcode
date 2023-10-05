import java.util.*;

public class StackPractice {

    static String leftBrackets = "{[(";
    static String rightBrackets = "}])";
    


    public static boolean checkBalancedBrackets(String expression) {
        Stack<Character> leftBracketStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            // if char is a left bracket, push it onto the stack
            // if char is not a bracket, do nothing
            // if char is a right bracket, make sure it matches the left bracket on the top of the stack
            // if it does, pop the stack
            // if it doesn't, fail.
            char c = expression.charAt(i);
            int lbi = leftBrackets.indexOf(c);
            if (lbi > 0) leftBracketStack.push(c);      // List::add(c)
            else {
                int rbi = rightBrackets.indexOf(c);
                if (rbi > 0) {                          // c is a right bracket
                    if (leftBracketStack.isEmpty()) {
                        System.err.println(String.format("Error: right bracket %c unmatched by any left bracket", c));
                        return false;
                    }
                    char lc = leftBracketStack.pop();
                    int llbi = leftBrackets.indexOf(lc);
                    if (llbi != rbi) {
                        System.err.println(String.format("Error: right bracket %c matched by incompatible left bracket %c", c, lc));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // palindrome is a string that is the same in both directions
    public static boolean checkPalindrome(String str) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : str.toCharArray()) stack.push(c);
        for (int i = 0; i < str.length()/2.0; i++) {
            char fc = str.charAt(i), bc = stack.pop();
            if (fc != bc) {
                System.out.println(String.format("%s is not a palindrome: character %c does not match %c.", str, fc, bc));
                return false;
            }
        }
        System.out.println(str + " is a palindrome");
        return true;
    }

    // given two stacks, empty the contents of auxStack into origStack
    public static <E> void restoreStack(Stack<E> origStack, Stack<E> auxStack) {
        while (!auxStack.isEmpty()) {
            origStack.push(auxStack.pop());
        }
    }

    // search = find how far an element is from the top (top is 1)
    // use only stack methods
    public static <E> int searchStack(Stack<E> stack, E elt) {
        Stack<E> auxStack = new Stack<>();
        E top;
        int count = 0;
        do {
            if (stack.isEmpty()) {
                System.out.println(String.format("%s is not in the stack", elt));
                restoreStack(stack, auxStack);
                return -1;
            }
            top = stack.pop();
            auxStack.push(top);
            count++;
        } while (!top.equals(elt));

        System.out.println(String.format("Element %s is %d from the top.", elt, count));
        restoreStack(stack, auxStack);
        return count;
    }

    public static void main(String[] args) {
        String[] expressions = {"[]", "[[[((({}{}(){})))]]]", "[[(]()]", "[)", ")))))"};

        for (String exp : expressions) {
            System.out.println(String.format("Expression %s %s", exp, checkBalancedBrackets(exp) ? "does match" : "does not match"));
        }

        Stack<Character> charStack = new Stack<>();
        for (char c : "hello goodbye cat dog".toCharArray()) charStack.push(c);
        while (!charStack.isEmpty()) {
            System.out.println(charStack.pop());
        }

        String[] possiblePalindromes = {"hearth", "abba", "amanaplanacanalpanama", "madam", "racecar", "toyaot"};
        for (String p : possiblePalindromes) {
            checkPalindrome(p);
        }

        Stack<String> stringStack = new Stack<>();
        for (String str : possiblePalindromes) stringStack.push(str);
        searchStack(stringStack, "racecar");
        searchStack(stringStack, "hearth");
        searchStack(stringStack, "laptop");

    }
}