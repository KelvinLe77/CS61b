package enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import static enigma.EnigmaException.*;

/** Class that represents a complete enigma machine.
 *  @author
 */
class Machine {

    /** A new Enigma machine with alphabet ALPHA, 1 < NUMROTORS rotor slots,
     *  and 0 <= PAWLS < NUMROTORS pawls.  ALLROTORS contains all the
     *  available rotors. */
    Machine(Alphabet alpha, int numRotors, int pawls,
            Collection<Rotor> allRotors) {
        _alphabet = alpha;
        // FIXME
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = allRotors;
        _usedRotors = new Rotor[numRotors];
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        return _numRotors; // FIXME
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {
        return _pawls; // FIXME
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        // FIXME
        for (int i = 0; i < rotors.length; i += 1) {
            for (Rotor aRotor: _allRotors) {
                if (aRotor.name().equals(rotors[i])) {
                    _usedRotors[i] = aRotor;
                }
            }
        }
        for (int i = 0; i < _usedRotors.length; i += 1) {
            if (_usedRotors[i] == null) {
                throw new EnigmaException("Missing rotors or not enough rotors");
            }
            if (i == 0 && !_usedRotors[i].reflecting()) {
                throw new EnigmaException("Leftmost rotor is not a reflector");
            }
            if (i == 1) {
                if (_usedRotors[i].rotates()) {
                    throw new EnigmaException(i + "th rotor should be a non-moving rotor");
                }
                if (!_usedRotors[i].rotates() && _usedRotors[i].reflecting()) {
                    throw new EnigmaException(i + "th rotor should not be a reflector");
                }
            }
            if (i > 1) {
                if (!_usedRotors[i].rotates()){
                    throw new EnigmaException(i + "th rotor should be a moving rotor");
                }
            }
        }
    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        // FIXME
        if (setting.length() != numRotors() - 1) {
            throw new EnigmaException("Setting length does not match number of rotors minus reflector");
        } else {
            for (int i = 1; i < _usedRotors.length; i += 1) {
                _usedRotors[i].set(setting.charAt(i - 1));
            }
        }
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        // FIXME
        _plugboard = plugboard;
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing

     *  the machine. */
    int convert(int c) {
        // FIXME
//        for (int i = _usedRotors.length - 1; i < 1; i -= 1) {
//            if (i == _usedRotors.length - 1) {
//                _usedRotors[i].advance();
//            }
//            if (i < _usedRotors.length - 2 && _usedRotors[i].rotates()) {
//                if (_usedRotors[i + 1].atNotch() && i + 1 == _usedRotors.length - 1) {
//                    _usedRotors[i].advance();
//                }
//                if (_usedRotors[i + 1].atNotch()) {
//                    _usedRotors[i].advance();
//                    _usedRotors[i + 1].advance();
//                }
//            }
//        }
//        int map = _plugboard.permute(c);
//        for (int i = _usedRotors.length - 1; i >= 0; i -= 1) {
//            map = _usedRotors[i].convertForward(map);
//        }
//        for (int j = 1; j < _usedRotors.length - 1; j += 1) {
//            map = _usedRotors[j].convertBackward(map);
//        }
//        map = _plugboard.permute(map);
//        return map;

        if (_usedRotors[_usedRotors.length - 1].atNotch()) {
            if (_usedRotors[_usedRotors.length - 1].rotates() &&
                    _usedRotors[_usedRotors.length - 2].rotates()) {
                _usedRotors[_usedRotors.length - 2].advance();
            }
        }
        if (_usedRotors[_usedRotors.length - 2].atNotch()) {
            if (_usedRotors[_usedRotors.length - 3].rotates() &&
                    _usedRotors[_usedRotors.length - 2].rotates()) {
                _usedRotors[_usedRotors.length - 3].advance();
                _usedRotors[_usedRotors.length - 2].advance();
            }
        }
        _usedRotors[_numRotors - 1].advance();

        int map = _plugboard.permute(c);
        for (int i = _numRotors - 1; i >= 0; i -= 1) {
            map = _usedRotors[i].convertForward(map);
        }
        for (int i = 1; i < _numRotors; i += 1) {
            map = _usedRotors[i].convertBackward(map);
        }
        return _plugboard.invert(map);
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        // FIXME
        String code = "";
        char[] cMsg = msg.toCharArray();
        for (int i = 0; i < cMsg.length; i += 1) {
//            if (cMsg[i] == (' ')) {
//                code += cMsg[i];
//            }
//            else {
//                code += _alphabet.toChar(convert(_alphabet.toInt(cMsg[i])));
//            }
//        }
            if (cMsg[i] != (' ')) {
                code += _alphabet.toChar(convert(_alphabet.toInt(cMsg[i])));
            }
        }
        return code;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;

    // FIXME: ADDITIONAL FIELDS HERE, IF NEEDED.
    private int _numRotors;
    private int _pawls;
    private Collection<Rotor> _allRotors;
    private Rotor[] _usedRotors;
    private Permutation _plugboard;
}
