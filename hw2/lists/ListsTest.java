package lists;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author FIXME
 */

public class ListsTest {
    /** FIXME
     */
    @Test
    public void testnaturalRuns() {
        IntList A = IntList.list(1, 4, 3, 4, 5);
        IntListList result = IntListList.list(IntList.list(1, 4), IntList.list(3, 4, 5));
        assertEquals(result, Lists.naturalRuns(A));

        IntList B = IntList.list(1, 4, 3, 2, 5);
        IntListList result2 = IntListList.list(IntList.list(1, 4), IntList.list(3), IntList.list(2, 5));
        assertEquals(result2, Lists.naturalRuns(B));
    }

    // It might initially seem daunting to try to set up
    // IntListList expected.
    //
    // There is an easy way to get the IntListList that you want in just
    // few lines of code! Make note of the IntListList.list method that
    // takes as input a 2D array.

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
