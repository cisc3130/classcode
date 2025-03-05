import java.util.*;

public class Stack<E> {
    private ArrayList<E> data;

    public Stack() {
        data = new ArrayList<>();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // to implement LIFO behavior, push and pop at the same end
    // since this is an arraylist, for efficiency we will choose the end end

    public E push(E elt) {
        data.add(elt);
        return elt;
    }

    public E pop() {
        return data.remove(data.size()-1);
    }

    public E peek() {
        return data.get(data.size()-1);
    }

    public int search(E elt) {
        for (int i = 1; i < data.size(); i++) {
            if (data.get(data.size()-i).equals(elt)) return i; 
        }
        return -1;
    }

    public static <E> void removeEveryOtherElement(Stack<E> s) {
        Stack<E> auxStack = new Stack<>();
        while (!s.isEmpty()) {
            s.pop();
            if (!s.isEmpty()) auxStack.push(s.pop());
        }
        while (!auxStack.isEmpty()) {
            s.push(auxStack.pop());
        }
    }

    public static <E> void moveToTop(Stack<E> s, int p) {
        Stack<E> aux = new Stack<>();
        for (int i = 0; i < p; i++) {
            aux.push(s.pop());
        }
        E toTop = s.pop();
        // empty the aux stack back onto the original stack
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
        // now put the pth element on top of everything
        s.push(toTop);
    }

    public static <E> boolean balancedParens(String str) {
        // for every char c:
        // if it's not a bracket, ignore it
        // if it's a left bracket, push it onto a stack
        // if it's a right bracket, compare it to the left bracket currently at the top of the stack
        // if they match, pop the stack and continue
        // if they don't match (or if the stack is empty), return false
        // if we reach the end of the string and the stack is not empty,
        // there are left brackets without matching right brackets, return false
        List<Character> leftBrackets = List.of('<', '(', '[', '{'),
            rightBrackets = List.of('>', ')', ']', '}');
        Stack<Character> leftBracketStack = new Stack<>();
        for (Character c : str.toCharArray()) {
            if (leftBrackets.contains(c)) leftBracketStack.push(c);
            else {
                int rpos = rightBrackets.indexOf(c);
                if (rpos < 0) continue;
                if (leftBracketStack.isEmpty()) return false;
                int lpos = leftBrackets.indexOf(leftBracketStack.peek());
                if (lpos != rpos) return false;
                leftBracketStack.pop();
            }
        }
        if (!leftBracketStack.isEmpty()) return false;
        return true;

    }

    public static void main(String[] args) {
        Stack<Character> s = new Stack<>();
        s.push('H');
        s.push('E');
        s.pop();
        s.push('L');
        s.push('S');
        s.pop();
        s.pop();
        System.out.println(s.peek());

        String ex1 = "<({[}])>",
            ex2 = "<({]})>",
            ex3 = "<(",
            ex4 = "<>>";
    }
}