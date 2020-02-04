import static org.junit.Assert.*;
import org.junit.Test;

public class MultiArrTest {

    @Test
    public void testMaxValue() {
        //TODO: Your code here!
        assertEquals(19, MultiArr.maxValue(new int[][] {{1, 2, 7, 4, 5}, {11, 19}, {1}}));

    }

    @Test
    public void testAllRowSums() {
        //TODO: Your code here!
        assertArrayEquals(new int[] {19, 30, 1}, MultiArr.allRowSums(new int[][] {{1, 2, 7, 4, 5}, {11, 19}, {1}}));
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(MultiArrTest.class));
    }
}
