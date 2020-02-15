package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author
 */
class Arrays {

    /* C1. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        /* *Replace this body with the solution. */
        if (A == null) {
            return B;
        } if (B == null) {
            return A;
        }

        int[] zeroArray = new int[A.length + B.length];
        int currIndexAB = 0; int currindexB = 0;
        while (currIndexAB < A.length) {
            zeroArray[currIndexAB] = A[currIndexAB];
            currIndexAB += 1;
        }
        while (currindexB < B.length) {
            zeroArray[currIndexAB] = B[currindexB];
            currIndexAB += 1; currindexB += 1;
        }
        return zeroArray;
    }

    /* C2. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        /* *Replace this body with the solution. */
        if (A.length == 0) {
            return null;
        }
        if (start >= A.length) {
            return null;
        }
        if (len == 0) {
            return A;
        }
        int[] newArray = new int[A.length - len];
        int indexA = 0; int indexNewArr = 0;
        int length = A.length - len;
        while (start > 0) {
            newArray[indexNewArr] = A[indexA];
            indexNewArr += 1; indexA += 1;
            start -= 1;
        }
        while (start == 0 && len > 0) {
            indexA += 1;
            len -= 1;
        }
        while (indexNewArr < length) {
            newArray[indexNewArr] = A[indexA];
            indexNewArr += 1; indexA += 1;
        }
        return newArray;
    }

    /* C3. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        /* *Replace this body with the solution. */
        return null;
    }
}
