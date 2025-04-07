import java.util.*;
import java.util.function.BinaryOperator;

public class OpTree {

    Map<String, BinaryOperator<Double>> eval = Map.of(
        "+", (x1, x2) -> x1 + x2,
        "-", (x1, x2) -> x1 - x2,
        "*", (x1, x2) -> x1 * x2,
        "/", (x1, x2) -> x1 / x2
    );

    abstract class OpTreeNode {
        String name;
        public String toString() { return name; }
        public abstract Double evaluate();
        public abstract String toInfixString();
        public abstract String toPrefixString();
        public abstract String toPostfixString();
    }

    class OperatorNode extends OpTreeNode {
        OpTreeNode leftChild, rightChild;
        BinaryOperator<Double> op;

        public OperatorNode(String op) {
            this.name = op;
            this.op = eval.get(op);
        }

        public Double evaluate() {
            if (leftChild == null || rightChild == null) throw new IllegalStateException("OperandNode must have exactly two children.");
            return op.apply(leftChild.evaluate(), rightChild.evaluate());
        }

        public String toInfixString() {
            String res = String.join(" ", leftChild.toInfixString(), this.name, rightChild.toInfixString());
            if (this.name.equals("+") || this.name.equals("-")) {
                return "( " + res + " )";
            } else {
                return res;
            }
        }

        public String toPrefixString() {
            return String.join(" ", this.name, leftChild.toPrefixString(), rightChild.toPrefixString());
        }

        public String toPostfixString() {
            return String.join(" ", leftChild.toPostfixString(), rightChild.toPostfixString(), this.name);
        }

    }

    class OperandNode extends OpTreeNode {
        Double val;
        public OperandNode(Double val) {
            this.val = val;
            this.name = val.toString();
        }
        public Double evaluate() { return val; }

        public String toPrefixString() { return val.toString(); }
        public String toPostfixString() { return val.toString(); }
        public String toInfixString() { return val.toString(); }
    }

    OpTreeNode root;

    public OpTree(String postfixEq) {
        Stack<OpTreeNode> ops = new Stack<>();
        for (char c : postfixEq.toCharArray()) {
            if (Character.isDigit(c)) {
                ops.push(new OperandNode((double) (c - '0')));
            } else {
                OperatorNode op = new OperatorNode(Character.toString(c));
                op.rightChild = ops.pop();
                op.leftChild = ops.pop();
                ops.push(op);
            }
        }
        root = ops.pop();
    }

    public double evaluate() {
        return root.evaluate(); 
    }

    public String toInfixString() { return root.toInfixString(); }
    public String toPrefixString() { return root.toPrefixString(); }
    public String toPostfixString() { return root.toPostfixString(); }


    public static void main(String[] args) {
        String arg = "34+2*5-3/";
        // for (String arg : args) {
            OpTree tree = new OpTree(arg);
            System.out.println("The value of this expression is " + tree.evaluate());
            System.out.println("Postfix notation: " + tree.toPostfixString());
            System.out.println("Prefix notation: " + tree.toPrefixString());
            System.out.println("Infix notation: " + tree.toInfixString());
        }
    // }

    
}