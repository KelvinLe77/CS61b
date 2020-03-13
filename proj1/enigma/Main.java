package enigma;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collection;

import static enigma.EnigmaException.*;


/** Enigma simulator.
 *  @author Kelvin Le
 */
public final class Main {

    /**
     * Process a sequence of encryptions and decryptions, as
     * specified by ARGS, where 1 <= ARGS.length <= 3.
     * ARGS[0] is the name of a configuration file.
     * ARGS[1] is optional; when present, it names an input file
     * containing messages.  Otherwise, input comes from the standard
     * input.  ARGS[2] is optional; when present, it names an output
     * file for processed messages.  Otherwise, output goes to the
     * standard output. Exits normally if there are no errors in the input;
     * otherwise with code 1.
     */
    public static void main(String... args) {
        try {
            new Main(args).process();
            return;
        } catch (EnigmaException excp) {
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    /**
     * Check ARGS and open the necessary files (see comment on main).
     */
    Main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            throw error("Only 1, 2, or 3 command-line arguments allowed");
        }

        _config = getInput(args[0]);

        if (args.length > 1) {
            _input = getInput(args[1]);
        } else {
            _input = new Scanner(System.in);
        }

        if (args.length > 2) {
            _output = getOutput(args[2]);
        } else {
            _output = System.out;
        }
    }

    /**
     * Return a Scanner reading from the file named NAME.
     */
    private Scanner getInput(String name) {
        try {
            return new Scanner(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /**
     * Return a PrintStream writing to the file named NAME.
     */
    private PrintStream getOutput(String name) {
        try {
            return new PrintStream(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /**
     * Configure an Enigma machine from the contents of configuration
     * file _config and apply it to the messages in _input, sending the
     * results to _output.
     */
    private void process() {
        try {
            boolean containsAsterisk = false;
            Machine mach = readConfig();
            while (_input.hasNextLine()) {
                String setting = _input.nextLine();
                if (setting.charAt(0) == '*') {
                    containsAsterisk = true;
                    setUp(mach, setting);
                } else {
                    if (!containsAsterisk) {
                        throw new EnigmaException("File does not"
                                + "start with an asterisk");
                    }
                    String msg = setting.replaceAll(" ", "");
                    printMessageLine(mach.convert(msg));
                }
            }
        } catch (NoSuchElementException excp) {
            throw error("config file truncated");
        }
    }

    /** Return an Enigma machine configured from the contents of configuration
     *  file _config. */
    private Machine readConfig() {
        try {
            Collection<Rotor> allRotors = new ArrayList<>();
            if (!_config.hasNext()) {
                throw new EnigmaException("File does not contain alphabet");
            }
            String alpha = _config.next();
            if (alpha.contains("*") || alpha.contains("(")
                    || alpha.contains(")")) {
                throw new EnigmaException("Alphabet has "
                        + "incorrect format");
            }
            _alphabet = new Alphabet(alpha);
            if (!_config.hasNextInt()) {
                throw new EnigmaException("File does not contain "
                        + "a number for rotors");
            }
            int numRotors = _config.nextInt();
            if (!_config.hasNextInt()) {
                throw new EnigmaException("File does not contain "
                        + "a number for pawls");
            }
            int pawls = _config.nextInt();
            while (_config.hasNext()) {
                allRotors.add(readRotor());
            }
            return new Machine(_alphabet, numRotors, pawls, allRotors);
        } catch (NoSuchElementException excp) {
            throw error("configuration file truncated");
        }
    }

    /** Return a rotor, reading its description from _config. */
    private Rotor readRotor() {
        try {
            String name = _config.next();
            String typeandNotches = _config.next();
            String cycles = "";
            while (_config.hasNext("\\(.+\\)")) {
                cycles += _config.next() + " ";
            }
            Permutation perm = new Permutation(cycles, _alphabet);
            if (typeandNotches.charAt(0) == 'R') {
                return new Reflector(name, perm);
            }
            if (typeandNotches.charAt(0) == 'N') {
                return new FixedRotor(name, perm);
            }
            if (typeandNotches.charAt(0) == 'M') {
                return new MovingRotor(name, perm, typeandNotches.substring(1));
            }
            throw new EnigmaException(
                    "Wrong input for rotor type or no input");
        } catch (NoSuchElementException excp) {
            throw error("bad rotor description");
        }
    }

    /** Set M according to the specification given on SETTINGS,
     *  which must have the format specified in the assignment. */
    private void setUp(Machine M, String settings) {
        settings = settings.replaceAll("\\s+", " ");
        String[] settingsInput = settings.split(" ");
        ArrayList<String> rotorNames = new ArrayList<>();
        int numRotors = M.numRotors();
        for (int i = 1; i <= numRotors; i += 1) {
            if (!rotorNames.contains(settingsInput[i])) {
                rotorNames.add(settingsInput[i]);
            } else {
                throw new EnigmaException("There is a duplicate rotor");
            }
        }
        String[] machInput = new String[numRotors];
        for (int i = 1; i <= numRotors; i += 1) {
            machInput[i - 1] = settingsInput[i];
        }
        M.insertRotors(machInput);
        String rotorSettings = settingsInput[M.numRotors() + 1];
        for (int i = 0; i < rotorSettings.length(); i += 1) {
            if (!_alphabet.contains(rotorSettings.charAt(i))) {
                throw new EnigmaException("Rotor setting input contains "
                        + "characters not in alphabet");
            }
        }
        M.setRotors(rotorSettings);
        String plugboard = "";
        int plugPermsInput = M.numRotors() + 2;
        if (plugPermsInput < settingsInput.length) {
            for (int i = plugPermsInput; i < settingsInput.length; i += 1) {
                plugboard = plugboard + settingsInput[i];
            }
        }
        M.setPlugboard(new Permutation(plugboard, _alphabet));
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private void printMessageLine(String msg) {
        for (int i = 0; i < msg.length(); i += 5) {
            int totalChars = msg.length() - i;
            if (totalChars < 6) {
                _output.println(msg.substring(i, i + totalChars));
            } else {
                _output.print(msg.substring(i, i + 5) + " ");
            }
        }
    }

    /** Alphabet used in this machine. */
    private Alphabet _alphabet;

    /** Source of input messages. */
    private Scanner _input;

    /** Source of machine configuration. */
    private Scanner _config;

    /** File for encoded/decoded messages. */
    private PrintStream _output;
}
