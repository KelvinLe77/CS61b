package enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static enigma.EnigmaException.*;

/** Represents a permutation of a range of integers starting at 0 corresponding
 *  to the characters of an alphabet.
 *  @author
 */
class Permutation {

    /** Set this Permutation to that specified by CYCLES, a string in the
     *  form "(cccc) (cc) ..." where the c's are characters in ALPHABET, which
     *  is interpreted as a permutation in cycle notation.  Characters in the
     *  alphabet that are not included in any cycle map to themselves.
     *  Whitespace is ignored. */

    /** same num of ( as ) (this is probably taken care of by the two below this)
     * cant have ( and ) in wrong order (i.e. ")abc(" ) 3rd
     * cant have letters not between () 4th
     * characters in cycles have to be unique 2nd
     * characters in cycles have to be present in alphabet 1st
     * empty cycles 1st 1st*/

    Permutation(String cycles, Alphabet alphabet) {
        _alphabet = alphabet;
        _listCycles = new ArrayList<>();
        /** make hashset to bind characters together*/
        /** make _cycles a Character[] (wrapper) so it is easier to compare chars*/
        _perms = new HashMap<>();
        _cycles = cycles.trim();
        // FIXME
//        if (_cycles.equals("")) {
//            //map everything to itself
//        }
        /** checks for well formed cycles and puts cycles into list
         * havent decided what kind of list
         * char[]? Character[]? String[]?*/
        while (!_cycles.isEmpty()) {
            int openParen = _cycles.indexOf("(");
            int closeParen = _cycles.indexOf(")");
            if (openParen == -1 && closeParen == -1) {
                throw new EnigmaException("Malformed cycles, cycles does not contain cycle or " +
                        "there are characters outside of a cycle i.e. (abc)de");
            }
            if (openParen == closeParen + 1) {
                throw new EnigmaException("Malformed cycles, cycle contains no characters i.e. ()");
            }
            if (openParen == -1 || closeParen == -1) {
                throw new EnigmaException("Malformed cycles, uneven number of ( or )");
            }
            if (openParen > closeParen) {
                throw new EnigmaException("Malformed cycles, ( is after ) i.e. )abc(");
            }
            String aCycle = _cycles.substring(openParen + 1, closeParen);
            _listCycles.add(aCycle);
            if (_cycles.length() == closeParen + 1) {
                _cycles = "";
            } else {
                _cycles = _cycles.substring(closeParen + 1);
            }
        }

        /** check contents of cycles if they contain what theyre supposed to contain i.e. of malformed ((abc) and (a   bc)
         * also maps permutations uwuwuwuwuwuwuuwuwu*/
        for (int arrIndex = 0; arrIndex < _listCycles.size(); arrIndex += 1) {
            String aCycle = _listCycles.get(arrIndex);
            for (int cycleInd = 0; cycleInd < aCycle.length(); cycleInd += 1) {
                if (!_alphabet.contains(aCycle.charAt(cycleInd))) {
                    throw new EnigmaException("Malformed cycles, contains character not in alphabet");
                } else {
                    if (_listCycles.get(arrIndex).length() == 1) {
                        _perms.put(aCycle.charAt(0), aCycle.charAt(0));
                    } else {
                        if(cycleInd == aCycle.length() - 1) {
                            _perms.put(aCycle.charAt(cycleInd), aCycle.charAt(0));
                        } else {
                            _perms.put(aCycle.charAt(cycleInd), aCycle.charAt(cycleInd + 1));
                        }
                    }
                }
            }
        }
    }

    /** Add the cycle c0->c1->...->cm->c0 to the permutation, where CYCLE is
     *  c0c1...cm. */
    private void addCycle(String cycle) {
        // FIXME
    }

    /** Return the value of P modulo the size of this permutation. */
    final int wrap(int p) {
        int r = p % size();
        if (r < 0) {
            r += size();
        }
        return r;
    }

    /** Returns the size of the alphabet I permute. */
    int size() {
        return _alphabet.size(); // FIXME
    }

    /** Return the result of applying this permutation to P modulo the
     *  alphabet size. */
    int permute(int p) {
        return _alphabet.toInt(permute(_alphabet.toChar(wrap(p))));  // FIXME
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {
        /** uwu */
        return _alphabet.toInt(invert(_alphabet.toChar(wrap(c))));  // FIXME
    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {
        if (!_perms.containsKey(p)) {
            return p;
        }
        return _perms.get(p);  // FIXME
    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {
        for (HashMap.Entry<Character, Character> aPerm : _perms.entrySet()) {
            if (aPerm.getValue().equals(c)) {
                return aPerm.getKey();
            }
        }
        return c;
    } // FIXME

    /** Return the alphabet used to initialize this Permutation. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Return true iff this permutation is a derangement (i.e., a
     *  permutation for which no value maps to itself). */
    boolean derangement() {
        if (_perms.size() != _alphabet.size()) {
            return false;
        }
        for (int i = 0; i < _alphabet.size(); i += 1) {
            if (_perms.get(_alphabet.toChar(i)) == _alphabet.toChar(i)) {
                return false;
            }
        }
        return true;  // FIXME
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;

    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED

    /** Contains forward and inverse mappings of characters. */
    private HashMap<Character, Character> _perms;
    private ArrayList<String> _listCycles;
    private String _cycles;
}
