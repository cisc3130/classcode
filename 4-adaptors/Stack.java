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
        Stack<E> auxStack = new Stack<>();
        while (!s.isEmpty()) {
            auxStack.push(s.pop());
            if (!s.isEmpty()) s.pop();
        }
        while (!auxStack.isEmpty()) {
            s.push(auxStack.pop());
        }
    }

    public static <E> void moveToTop(Stack<E> s, int p) {
        Stack<E> auxStack = new Stack<>();
        for (int i = 0; i < p-1; i++) {
            auxStack.push(s.pop());
        }
        E target = s.pop();
        while (!auxStack.isEmpty()) {
            s.push(auxStack.pop());
        }
        s.push(target); 
    }

    public static <E> boolean balancedParens(String str) {
        // iterate over the string. for each char:
        // if it's not a bracket, ignore it
        // if it's a left bracket, push it onto a stack
        // if it's a right bracket, compare it to the left bracket on top of the stack
        // if they match, pop the stack
        // if not, error
        Stack<Character> leftBracketStack = new Stack<>();
        List<Character> leftBrackets = List.of('<', '[', '{', '('),
            rightBrackets = List.of('>', ']', '}', ')');
        
        for (char c : str.toCharArray()) {
            if (leftBrackets.contains(c)) leftBracketStack.push(c);
            else {
                int rightBracketIndex = rightBrackets.indexOf(c);
                if (rightBracketIndex < 0) continue;
                if (leftBracketStack.isEmpty()) // there are no more unmatched left brackets to match this right bracket
                    return false;
                int leftBracketIndex = leftBrackets.indexOf(leftBracketStack.peek());
                if (rightBracketIndex != leftBracketIndex) return false;    // this right bracket doesn't match the most recent unmatched left bracket
                leftBracketStack.pop();
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

        String ex1 = "<({[}])>",
            ex2 = "<({]})>",
            ex3 = "<(",
            ex4 = "<>>";

        undoRedo();
    }
}