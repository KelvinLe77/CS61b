package enigma;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import static enigma.TestUtils.*;

/** The suite of all JUnit tests for the Permutation class.
 *  @author
 */
public class PermutationTest {

    Permutation getNewPermutation(String cycles, Alphabet alphabet) {
        return new Permutation(cycles, alphabet);
    }
    Alphabet getNewAlphabet(String chars) {
        return new Alphabet(chars);
    }
    Alphabet getNewAlphabet() {
        return new Alphabet();
    }

    /** Testing time limit. */
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /* ***** TESTING UTILITIES ***** */

    private Permutation perm;
    private String alpha = UPPER_STRING;

    /** Check that perm has an alphabet whose size is that of
     *  FROMALPHA and TOALPHA and that maps each character of
     *  FROMALPHA to the corresponding character of FROMALPHA, and
     *  vice-versa. TESTID is used in error messages. */
    private void checkPerm(String testId,
                           String fromAlpha, String toAlpha) {
        int N = fromAlpha.length();
        assertEquals(testId + " (wrong length)", N, perm.size());
        for (int i = 0; i < N; i += 1) {
            char c = fromAlpha.charAt(i), e = toAlpha.charAt(i);
            assertEquals(msg(testId, "wrong translation of '%c'", c),
                         e, perm.permute(c));
            assertEquals(msg(testId, "wrong inverse of '%c'", e),
                         c, perm.invert(e));
            int ci = alpha.indexOf(c), ei = alpha.indexOf(e);
            assertEquals(msg(testId, "wrong translation of %d", ci),
                         ei, perm.permute(ci));
            assertEquals(msg(testId, "wrong inverse of %d", ei),
                         ci, perm.invert(ei));
        }
    }

    /* ***** TESTS ***** */
    @Test
    public void checkingInvertChar() {
        perm = new Permutation("(ABCD) (EFG)", UPPER);
        assertEquals('G', perm.invert('E'));
        assertEquals('E', perm.invert('F'));
        assertEquals('A', perm.invert('B'));
        assertEquals('D', perm.invert('A'));
    }

    @Test
    public void testInvertChar() {
        Permutation p = getNewPermutation("(BACD)", getNewAlphabet("ABCDEFG"));
        assertEquals('B', p.invert('A'));
        assertEquals('D', p.invert('B'));
        assertEquals('C', p.invert('D'));
        assertEquals('G', p.invert('G'));
    }

    @Test
    public void testInvertChar2() {
        Permutation p = getNewPermutation("", getNewAlphabet("ABCD"));
        assertEquals('A', p.invert('A'));
    }

    @Test
    public void testPermuteChar() {
        Permutation p = getNewPermutation("(BACD)", getNewAlphabet("ABCDEFG"));
        assertEquals('C', p.permute('A'));
        assertEquals('B', p.permute('D'));
        assertEquals('G', p.permute('G'));
    }

    @Test
    public void testPermuteChar2() {
        Permutation p = getNewPermutation("", getNewAlphabet("ABCDEFG"));
        assertEquals('G', p.permute('G'));

    }

    @Test
    public void testGetNewAlphabet() {
        Alphabet a = getNewAlphabet();
        assertEquals(26, a.size());
    }

    @Test
    public void testGetNewAlphabet2() {
        Alphabet a = getNewAlphabet("ABCD");
        assertEquals(4, a.size());
    }

    @Test
    public void testPermuteInt() {
        Permutation p = getNewPermutation("(ABCD)", getNewAlphabet("ABCDEFG"));
        assertEquals(3, p.permute(2));
        assertEquals(0, p.permute(3));
        assertEquals(5, p.permute(5));
        assertEquals(2, p.permute(8));
        assertEquals(6, p.permute(-1));
        assertEquals(3, p.permute(-5));

    }

    @Test
    public void testPermuteInt2() {
        Permutation p = getNewPermutation("", getNewAlphabet("ABCDEFG"));
        assertEquals(2, p.permute(2));
    }

    @Test
    public void testInvertInt() {
        Permutation p = getNewPermutation("(BACD)", getNewAlphabet("ABCDEFG"));
        assertEquals(1, p.invert(0));
        assertEquals(3, p.invert(1));
        assertEquals(2, p.invert(3));
        assertEquals(6, p.invert(6));
        assertEquals(3, p.invert(8));
        assertEquals(6, p.invert(-1));
        assertEquals(0, p.invert(-5));
    }

    @Test
    public void testInvertInt2() {
        Permutation p = getNewPermutation("", getNewAlphabet("ABCDEFG"));
        assertEquals(1, p.invert(1));
    }

    @Test
    public void testDerangement() {
        Permutation p = getNewPermutation("(ABCDEFGHIJKL"
                + "MNOPQRSTUVWXYZ)", getNewAlphabet());
        Permutation p2 = getNewPermutation("(ABCDEFGHIJKL"
                + "MNOPQRSTUVWXY)", getNewAlphabet());
        Permutation p3 = getNewPermutation("", getNewAlphabet());
        Permutation p4 = getNewPermutation("(A)(BC)", getNewAlphabet("ABC"));
        assertTrue(p.derangement());
        assertFalse(p2.derangement());
        assertFalse(p3.derangement());
        assertFalse(p4.derangement());

    }

    @Test
    public void testAlphabet() {
        Alphabet newA = getNewAlphabet();
        Alphabet newA2 = getNewAlphabet("(ABCD)");
        Permutation p = getNewPermutation("(ABCDEFGHIJKLMNOPQRSTUVWXYZ)", newA);
        Permutation p2 = getNewPermutation("(ABCD)", newA2);
        assertEquals(p.alphabet(), newA);
        assertEquals(p2.alphabet(), newA2);
    }

    @Test
    public void testSize() {
        Permutation p = getNewPermutation("", getNewAlphabet("ABCD"));
        Permutation p2 = getNewPermutation("", getNewAlphabet());
        assertEquals(4, p.size());
        assertEquals(26, p2.size());
    }
}
