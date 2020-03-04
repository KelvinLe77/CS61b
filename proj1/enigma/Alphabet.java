package enigma;

import javax.swing.text.AttributeSet;
import java.util.ArrayList;
import java.util.HashSet;

/** An alphabet of encodable characters.  Provides a mapping from characters
 *  to and from indices into the alphabet.
 *  @author
 */
class Alphabet {

    /** A new alphabet containing CHARS.  Character number #k has index
     *  K (numbering from 0). No character may be duplicated. */
    Alphabet(String chars) {
        // FIXME
        HashSet<Character> charSet = new HashSet<>();
        int charsLen = chars.length();
        validAlpha = new Character[charsLen];
        for (int i = 0; i < charsLen; i += 1) {
            charSet.add(chars.charAt(i));
        }
        if (charSet.size() != charsLen) {
            throw new EnigmaException("Alphabet contains duplicate characters");
        } else {
            for (int i = 0; i < charsLen; i += 1) {
                validAlpha[i] = chars.charAt(i);
            }
        }
    }

    /** A default alphabet of all upper-case characters. */
    Alphabet() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    /** Returns the size of the alphabet. */
    int size() {
        return validAlpha.length; // FIXME
    }

    /** Returns true if CH is in this alphabet. */
    boolean contains(char ch) {
        for (Character character : validAlpha) {
            if (character.equals(ch)) {
                return true;
            }
        }
        return false; // FIXME
    }

    /** Returns character number INDEX in the alphabet, where
     *  0 <= INDEX < size(). */
    char toChar(int index) {
        if (index < 0 || index >= validAlpha.length) {
            throw new EnigmaException("invalid index");
        }
        return validAlpha[index]; // FIXME
    }

    /** Returns the index of character CH which must be in
     *  the alphabet. This is the inverse of toChar(). */
    int toInt(char ch) {
        for (int i = 0; i < validAlpha.length; i += 1) {
            if (validAlpha[i].equals(ch)) {
                return i;
            }
        }
        throw new EnigmaException("Character not contained in Alphabet");
        // FIXME
    }

    /**
     * A list of characters in alphabet.
     */
    private Character[] validAlpha;
}


