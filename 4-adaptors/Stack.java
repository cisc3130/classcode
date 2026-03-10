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

    public E findElementInStack(E target) {
        Deque<E> aux = new ArrayDeque<>();
        E found = null;
        while (!this.isEmpty()) {
            E elt = this.pop();
            if (!elt.equals(target)) {
                aux.addFirst(elt);
            } else {
                found = elt;
                break;
            }
        }
        // either the stack is empty or the target was found
        while (!aux.isEmpty()) {
            this.push(aux.removeFirst());
        }
        return found;
    }


    public static <E> void removeEveryOtherElement(Deque<E> s) {
        Deque<E> aux = new LinkedList<>();
        int i = 0;
        while (!s.isEmpty()) {              // save every other element on the aux stack
            E elt = s.removeFirst();
            if (i++ % 2 == 0) {
                aux.addFirst(elt);
            }
        }
        while (!aux.isEmpty()) {            // cleanup loop restoring the stack
            s.addFirst(aux.removeFirst());
        }
    }

    public static <E> void moveToTop(Deque<E> s, int p) {       // 1-based indexing
        Deque<E> aux = new ArrayDeque<>();
        for (int i = 0; i < p-1; i++) {
            aux.addFirst(s.removeFirst());
        }
        // element p is now at the top of the stack
        E pth = s.removeFirst();
        // empty aux back into the original stack
        while (!aux.isEmpty()) {
            s.addFirst(aux.removeFirst());
        }
        s.addFirst(pth);
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
        Deque<String> state = new ArrayDeque<>(), redo = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter one or more characters to add to the string, or enter 'undo', 'redo', or 'quit'");
        String input = scanner.nextLine();
        while (!input.strip().equals("quit")) {
            if (input.strip().equals("undo")) {
                if (state.isEmpty()) System.out.println("Nothing to undo.");
                else {
                    redo.addFirst(state.removeFirst());
                    System.out.println("Current string: " + state.getFirst());
                }
            } else if (input.equals("redo")) {
                if (redo.isEmpty()) System.out.println("Nothing to redo.");
                else {
                    state.addFirst(redo.removeFirst());
                    System.out.println("Current string: " + state.getFirst());
                }
            } else {
                if (state.isEmpty()) {
                    state.addFirst(input);
                }
                else { state.addFirst(state.getFirst() + input); }
                System.out.println("Current string: " + state.getFirst());
            }

            System.out.println("Enter one or more characters to add to the string, or enter 'undo', 'redo', or 'quit'");
            input = scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Stack<Character> s = new Stack<>();
        // s.push('H');
        // s.push('E');
        // s.pop();
        // s.push('L');
        // s.push('S');
        // for (char c : "abcdefghijklmnopqrst".toCharArray()) {
        //     s.push(c);
        // }
        // // s.pop();
        // // s.pop();

        // s.findElementInStack('l');

        // System.out.println(s.peek());
        // // moveToTop(s, 2);

        // String ex1 = "<([{}()])>",
        //     ex2 = "<({]})>",
        //     ex3 = "<(",
        //     ex4 = "<>>";
        // System.out.println(balancedParens(ex1));
        // System.out.println(balancedParens(ex2));
        // System.out.println(balancedParens(ex3));
        // System.out.println(balancedParens(ex4));

        undoRedo();
    }
}