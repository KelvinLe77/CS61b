import java.util.ArrayList;

/** HW #7, Two-sum problem.
 * @author
 */
public class Sum {

    /** Returns true iff A[i]+B[j] = M for some i and j. */
    public static boolean sumsTo(int[] A, int[] B, int m) {
        // REPLACE WITH YOUR ANSWER
        ArrayList<Integer> c = new ArrayList<>();
        for (int a : A) {
            c.add(a);
            if (a < m && !c.contains(a)) {
                for (int b : B) {
                    if (b < m) {
                        if (b + a == m) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
