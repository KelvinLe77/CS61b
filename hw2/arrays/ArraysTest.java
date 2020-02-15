package arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class ArraysTest {
    /** FIXME
     */
    @Test
    public void testcatenate() {
        int[] A = {1, 5, 7}; int[] B = {8, 9, 10};
        int[] result = {1, 5, 7, 8, 9, 10};
        assertArrayEquals(result, Arrays.catenate(A, B));
    }

    @Test
    public void testremove() {
        int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; int start = 3; int len = 5;
        int[] result = {0, 1, 2, 8, 9, 10};
        assertArrayEquals(result, Arrays.remove(A, start, len));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
