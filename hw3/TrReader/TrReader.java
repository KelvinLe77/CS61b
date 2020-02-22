import java.io.Reader;
import java.io.IOException;

/** Translating Reader: a stream that is a translation of an
 *  existing reader.
 *  @author your name here
 */
public class TrReader extends Reader {
    Reader reader;
    String toChange;
    String changeTo;
    /** A new TrReader that produces the stream of characters produced
     *  by STR, converting all characters that occur in FROM to the
     *  corresponding characters in TO.  That is, change occurrences of
     *  FROM.charAt(i) to TO.charAt(i), for all i, leaving other characters
     *  in STR unchanged.  FROM and TO must have the same length. */
    public TrReader(Reader str, String from, String to) {
        // TODO: YOUR CODE HERE
        reader = str;
        toChange = from;
        changeTo = to;
    }

    /* TODO: IMPLEMENT ANY MISSING ABSTRACT METHODS HERE
     * NOTE: Until you fill in the necessary methods, the compiler will
     *       reject this file, saying that you must declare TrReader
     *       abstract. Don't do that; define the right methods instead!
     */
    @Override
     public int read(char[] cbuf, int off, int len) throws IOException {
        int start = reader.read(cbuf, off, len);
            for (int i = off; i < off + len; i += 1) {
                if (toChange.indexOf(cbuf[i]) != -1) {
                    cbuf[i] = this.changeTo.charAt(toChange.indexOf(cbuf[i]));
                }
            }
        return Math.min(len, start);
    }
    @Override
    public void close() throws IOException {
         this.reader.close();
    }
}

