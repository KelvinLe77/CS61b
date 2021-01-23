package lists;

/** HW #2, Problem #1. */

/** List problem.
 *  @author
 */
class Lists {

    /* B. */
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal strictly ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10, 10, 11),
     *  then result is the four-item list
     *            ((1, 3, 7), (5), (4, 6, 9, 10), (10, 11)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntListList naturalRuns(IntList L) {
        /* *Replace this body with the solution. */
        if ( L == null) {
            return null;
        }
        IntList head = L; IntList tail = null; IntList pointer = L;

        for (int i = L.head; L != null && i <= L.head; L = L.tail) {
            i = L.head; tail = L.tail; head = L;
            if (L.tail != null && i == L.tail.head) {
                break;
            }
        }
        if (head != null) {
            head.tail = null;
        }
        return new IntListList(pointer, naturalRuns(tail));
    }
}
