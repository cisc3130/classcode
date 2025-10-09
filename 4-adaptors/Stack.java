import java.sql.Array;
import java.util.*;


public class Stack<E> {
    private MArrayList<E> data;

    public Stack() {
        data = new MArrayList<>();
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


    public static <E> void removeEveryOtherElement(Stack<E> s) {
        Deque<E> aux = new LinkedList<>();
        while (!s.isEmpty()) {
            aux.push(s.pop());
            if (!s.isEmpty()) s.pop();
        }
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
    }

    public static <E> void moveToTop(Stack<E> s, int p) {       // 1-based indexing
        Deque<E> aux = new ArrayDeque<>();
        for (int i = 0; i < p-1; i++) {
            aux.push(s.pop());
        }
        E x = s.pop();
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
        s.push(x);
    }

    public static <E> boolean balancedParens(String str) {
        // iterate over the string. for each char:
        // if it's not a bracket, ignore it
        // if it's a left bracket, push it onto a stack
        // if it's a right bracket, compare it to the left bracket on top of the stack
        // if they match, pop the stack
        // if not, error
        Deque<Character> leftBracketStack = new ArrayDeque<>();
        List<Character> leftBrackets = List.of('<', '[', '{', '('),
            rightBrackets = List.of('>', ']', '}', ')');
        
        for (char c : str.toCharArray()) {
            if (leftBrackets.contains(c)) leftBracketStack.addFirst(c);
            else {
                int rightBracketIndex = rightBrackets.indexOf(c);
                if (rightBracketIndex < 0) continue;
                if (leftBracketStack.isEmpty()) // there are no more unmatched left brackets to match this right bracket
                    return false;
                int leftBracketIndex = leftBrackets.indexOf(leftBracketStack.getFirst());
                if (rightBracketIndex != leftBracketIndex) return false;    // this right bracket doesn't match the most recent unmatched left bracket
                leftBracketStack.removeFirst();
            }
        }

        if (!leftBracketStack.isEmpty()) // there are still left brackets that were never matched
            return false;

        
        return true;
    }

    

    public static void undoRedo() {
        Stack<String> state = new Stack<>(), redo = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter one or more characters to add to the string, or enter 'undo', 'redo', or 'quit'");
        String input = scanner.nextLine();
        while (!input.strip().equals("quit")) {
            if (input.strip().equals("undo")) {
                if (state.isEmpty()) System.out.println("Nothing to undo.");
                else {
                    redo.push(state.pop());
                    System.out.println("Current string: " + state.peek());
                }
            } else if (input.equals("redo")) {
                if (redo.isEmpty()) System.out.println("Nothing to redo.");
                else {
                    state.push(redo.pop());
                    System.out.println("Current string: " + state.peek());
                }
            } else {
                if (state.isEmpty()) {
                    state.push(input);
                }
                else { state.push(state.peek() + input); }
                System.out.println("Current string: " + state.peek());
            }

            System.out.println("Enter one or more characters to add to the string, or enter 'undo', 'redo', or 'quit'");
            input = scanner.nextLine();
        }
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
        moveToTop(s, 2);

        // String ex1 = "<([{}()])>",
        //     ex2 = "<({]})>",
        //     ex3 = "<(",
        //     ex4 = "<>>";
        // System.out.println(balancedParens(ex1));
        // System.out.println(balancedParens(ex2));
        // System.out.println(balancedParens(ex3));
        // System.out.println(balancedParens(ex4));

        // undoRedo();
    }
}