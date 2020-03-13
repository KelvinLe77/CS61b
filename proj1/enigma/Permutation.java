package enigma;

import java.util.ArrayList;
import java.util.HashMap;

import static enigma.EnigmaException.*;

/** Represents a permutation of a range of integers starting at 0 corresponding
 *  to the characters of an alphabet.
 *  @author Kelvin Le
 */
class Permutation {

    /** Set this Permutation to that specified by CYCLES, a string in the
     *  form "(cccc) (cc) ..." where the c's are characters in ALPHABET, which
     *  is interpreted as a permutation in cycle notation.  Characters in the
     *  alphabet that are not included in any cycle map to themselves.
     *  Whitespace is ignored. */
    Permutation(String cycles, Alphabet alphabet) {
        _alphabet = alphabet;
        _listCycles = new ArrayList<>();
        _perms = new HashMap<>();
        _cycles = cycles.trim();
        while (!_cycles.isEmpty()) {
            int openParen = _cycles.indexOf("(");
            int closeParen = _cycles.indexOf(")");
            if (openParen == -1 && closeParen == -1) {
                throw new EnigmaException("Malformed cycles, cycles does not "
                        + "contain cycle or there are characters "
                        + "outside of a cycle i.e. (abc)de");
            }
            if (openParen == closeParen + 1) {
                throw new EnigmaException("Malformed cycles, "
                        + "cycle contains no characters i.e. ()");
            }
            if (openParen == -1 || closeParen == -1) {
                throw new EnigmaException("Malformed cycles, "
                        + "uneven number of ( or )");
            }
            if (openParen > closeParen) {
                throw new EnigmaException("Malformed cycles, "
                        + "( is after ) i.e. )abc(");
            }
            String aCycle = _cycles.substring(openParen + 1, closeParen);
            _listCycles.add(aCycle);
            if (_cycles.length() == closeParen + 1) {
                _cycles = "";
            } else {
                _cycles = _cycles.substring(closeParen + 1);
            }
        }
        for (int arrIndex = 0; arrIndex < _listCycles.size(); arrIndex += 1) {
            String aCycle = _listCycles.get(arrIndex);
            for (int cycleInd = 0; cycleInd < aCycle.length(); cycleInd += 1) {
                if (!_alphabet.contains(aCycle.charAt(cycleInd))) {
                    throw new EnigmaException("Malformed cycles, "
                            + "contains character not in alphabet");
                } else {
                    if (_listCycles.get(arrIndex).length() == 1) {
                        _perms.put(aCycle.charAt(0), aCycle.charAt(0));
                    } else {
                        if (cycleInd == aCycle.length() - 1) {
                            char cha = aCycle.charAt(cycleInd);
                            _perms.put(cha, aCycle.charAt(0));
                        } else {
                            char cha = aCycle.charAt(cycleInd);
                            _perms.put(cha, aCycle.charAt(cycleInd + 1));
                        }
                    }
                }
            }
        }
    }

    /** Add the cycle c0->c1->...->cm->c0 to the permutation, where CYCLE is
     *  c0c1...cm. */
    private void addCycle(String cycle) {
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
        return _alphabet.size();
    }

    /** Return the result of applying this permutation to P modulo the
     *  alphabet size. */
    int permute(int p) {
        return _alphabet.toInt(permute(_alphabet.toChar(wrap(p))));
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {
        /** uwu */
        return _alphabet.toInt(invert(_alphabet.toChar(wrap(c))));
    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {
        if (!_perms.containsKey(p)) {
            return p;
        }
        return _perms.get(p);
    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {
        for (HashMap.Entry<Character, Character> aPerm : _perms.entrySet()) {
            if (aPerm.getValue().equals(c)) {
                return aPerm.getKey();
            }
        }
        return c;
    }

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
        return true;
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;
    /** Contains forward and inverse mappings of characters. */
    private HashMap<Character, Character> _perms;
    /** List containing cycles split apart. */
    private ArrayList<String> _listCycles;
    /** Copy of cycles after being trimmed. */
    private String _cycles;
}
