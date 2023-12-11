import java.util.Map;

public class FinalPractice {
    /*
    Write a function replace that that takes two values, 
    findVal and repVal, and replaces each instance of
    findVal in a binary tree rooted at nd with repVal. 
    Return how many instances of findVal were found. You
    can write this recursively or iteratively (recall the 
    skeleton code for traversing a tree, think about what
    “work” is being done at each node). Assume structure node 
    has members T val and node<T>* left, right.
    */
    public static <T> int replace(Node nd, T findVal, T repVal) {
        if (nd == null) return 0;
        int replaced = 0;
        if (nd.val.equals(findVal)) {
            nd.val = repVal;
            replaced = 1;
        }
        int leftReplaced = replace(nd.left, findVal, repVal);
        int rightReplaced = replace(nd.right, findVal, repVal);
        return replaced + leftReplaced + rightReplaced;
    }

    /*
     Students are eligible for the ABC award if they have 
     a high GPA and have never yet received it. Given a
    set containing the students who have previously received 
    the award, and a list of students with high GPAs,
    create a new list containing students with high GPAs who 
    have not yet received the award. Add those
    students to the set of awardees, and return the list.
     */
    public static List<String> abcAward(Set<String> alreadyAwarded,
        List<String> highGpas) {
        List<String> toAward = new LinkedList<>();
        for (String student : highGpas) {
            /* Partial credit: costs a double search 
            if (!alreadyAwarded.contains(student)) {
                toAward.add(student);
                alreadyAwarded.add(student);
            }
            */
            boolean notAlreadyAwarded = alreadyAwarded.add(student);
            if (notAlreadyAwarded) toAward.add(student);
        }
        return toAward;
    }

    /*
     Write a function mostFrequent that takes a vector of elements, 
     uses a map to count how many times each one occurs, and 
     returns the element that occurs most frequently.
     */
    public static <E> E mostFrequent(List<E> lst) {
        Map<E, Integer> counts = new HashMap<>();
        for (E elt : lst) {
            counts.compute(elt, (k, v) -> (v == null) ? 1 : v + 1);
        }

        E maxKey;
        int maxVal = Integer.MIN_VALUE;
        for (Map.Entry<E, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxVal) {
                maxVal = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return maxKey();
    }
}