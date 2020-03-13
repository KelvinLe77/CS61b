package enigma;


import java.util.Collection;

import static enigma.EnigmaException.*;

/** Class that represents a complete enigma machine.
 *  @author Kelvin Le
 */
class Machine {

    /** A new Enigma machine with alphabet ALPHA, 1 < NUMROTORS rotor slots,
     *  and 0 <= PAWLS < NUMROTORS pawls.  ALLROTORS contains all the
     *  available rotors. */
    Machine(Alphabet alpha, int numRotors, int pawls,
            Collection<Rotor> allRotors) {
        _alphabet = alpha;
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = allRotors;
        _usedRotors = new Rotor[numRotors];
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        return _numRotors;
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {
        return _pawls;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        for (int i = 0; i < rotors.length; i += 1) {
            for (Rotor aRotor: _allRotors) {
                if (aRotor.name().equals(rotors[i])) {
                    _usedRotors[i] = aRotor;
                }
            }
        }
        for (int i = 0; i < _usedRotors.length; i += 1) {
            if (_usedRotors[i] == null) {
                throw new EnigmaException("Missing rotors "
                        + "or not enough rotors");
            }
            if (i == 0 && !_usedRotors[i].reflecting()) {
                throw new EnigmaException("Leftmost rotor is not a reflector");
            }
            if (i == 1) {
                if (!_usedRotors[i].rotates() && _usedRotors[i].reflecting()) {
                    throw new EnigmaException(i + "th rotor "
                            + "should not be a reflector");
                }
            }
        }
    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        if (setting.length() != numRotors() - 1) {
            throw new EnigmaException("Setting length does not "
                    + "match number of rotors minus reflector");
        } else {
            for (int i = 1; i < _usedRotors.length; i += 1) {
                _usedRotors[i].set(setting.charAt(i - 1));
            }
        }
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing

     *  the machine. */
    int convert(int c) {
        if (_usedRotors[_usedRotors.length - 1].atNotch()) {
            if (_usedRotors[_usedRotors.length - 1].rotates()
                    && _usedRotors[_usedRotors.length - 2].rotates()) {
                _usedRotors[_usedRotors.length - 2].advance();
            }
        }
        if (_usedRotors[_usedRotors.length - 2].atNotch()) {
            if (_usedRotors[_usedRotors.length - 3].rotates()
                    && _usedRotors[_usedRotors.length - 2].rotates()) {
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
        String code = "";
        char[] cMsg = msg.toCharArray();
        for (int i = 0; i < cMsg.length; i += 1) {
            if (cMsg[i] != (' ')) {
                code += _alphabet.toChar(convert(_alphabet.toInt(cMsg[i])));
            }
        }
        return code;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;
    /** Number of rotors used in the machine. */
    private int _numRotors;
    /** Number of pawls used in the machine. */
    private int _pawls;
    /** Collection of all rotors available. */
    private Collection<Rotor> _allRotors;
    /** List of rotors being used in the machine. */
    private Rotor[] _usedRotors;
    /** The permutation for plugboard. */
    private Permutation _plugboard;
}
