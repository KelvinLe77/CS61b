package enigma;

import static enigma.EnigmaException.*;

/** Class that represents a rotating rotor in the enigma machine.
 *  @author
 */
class MovingRotor extends Rotor {

    /** A rotor named NAME whose permutation in its default setting is
     *  PERM, and whose notches are at the positions indicated in NOTCHES.
     *  The Rotor is initally in its 0 setting (first character of its
     *  alphabet).
     */
    MovingRotor(String name, Permutation perm, String notches) {
        super(name, perm);
        // FIXME
        _notches = notches;
    }

    // FIXME?

//    @Override
//    void advance() {
//        // FIXME
//        super.set(_permutation.wrap(super.setting() + 1));
//    }
//
//    @Override
//    boolean atNotch() {
//        for (int i = 0; i < _notches.length(); i += 1) {
//            if (alphabet().toChar(super.setting()) == _notches.charAt(i)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    boolean atNotch() {
        for (int i = 0; i < _notches.length(); i += 1) {
            if (alphabet().toInt(_notches.charAt(i)) == this.setting()) {
                return true;
            }
        }
        return false;
    }

    @Override
    void advance() {
        this.set(this.setting() + 1);
    }

    @Override
    boolean rotates() {
        return true;
    }


    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED
    private String _notches;
}
