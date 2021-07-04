package main.program.java.commands;

import main.program.java.constants.Errors;
import main.program.java.constants.UUPK;
import main.program.java.core.Enigma;
import main.program.java.constants.Regex;
import main.program.java.core.EnigmaSetup;

import java.io.IOException;
import java.util.ArrayList;

public class CommandEncode implements ICommand {

    private Enigma enigma;
    private ArrayList<Character> charList = new ArrayList<>();
    private static final int MAX_DEPTH = 2000;
    private static final int PASS_LENGTH = 16;

    private int depth;

    public CommandEncode() {
        enigma = Enigma.getInstance();
    }

    @Override
    public String execute(String parameters) throws IOException {
        this.depth = 0;

        if (isValid(parameters)) {
            enigma.setup(new EnigmaSetup(UUPK.getInstance("").getUUPK(), parameters));
            return encode(parameters);
        } else {
            if (charList.isEmpty()) {
                throw new IOException(Errors.EMPTY_INPUT);
            } else {
                throw new IOException(String.format(Errors.INVALID_INPUT_CHARS, invalidChars()));
            }
        }
    }

    private boolean isValid(String parameters) {
        charList.clear();
        boolean result = true;
        if (parameters.length() == 0) {
            return false;
        }
        for (int i = 0; i < parameters.length(); i++) {
            int c = parameters.charAt(i);
            if (!(c <= '~' && c >= '!')) {
                if (!charList.contains(parameters.charAt(i))) {
                    charList.add(parameters.charAt(i));
                }
                result = false;
            }
        }
        return result;
    }

    private String invalidChars() {
        String str = "";
        for (Character c : charList) {
            if (!str.equals("")) {
                str += ", ";
            }
            if (c == ' ') {
                str += "SPACE";
            } else {
                str += c;
            }
        }
        return str;
    }

    private String encode(String parameters) {
        String result = "";
        result = enigma.encode(parameters);
        if (result.length() < PASS_LENGTH) {
            return encode(result + calculateSuffix(parameters));
        }
        if (!checkIfDivers(result)) {
            if (depth == MAX_DEPTH) {
                return result;
            }
            depth++;
            return encode(result);
        }
        return result;
    }

    private boolean checkIfDivers(String encodedMessage) {
        int smallLetters = 0;
        int capitalLetters = 0;
        int numbers = 0;
        int special = 0;
        int diversityLevel = encodedMessage.length() / 4;
        for (int i = 0; i < encodedMessage.length(); i++) {
            String c = String.format("%s", encodedMessage.charAt(i));
            if (c.matches(Regex.SMALL_LETTERS)) {
                smallLetters++;
            } else if (c.matches(Regex.CAPITAL_LETTERS)) {
                capitalLetters++;
            } else if (c.matches(Regex.NUMBERS)) {
                numbers++;
            } else {
                special++;
            }
        }
        if (numbers < diversityLevel || special < diversityLevel || smallLetters < diversityLevel
                || capitalLetters < diversityLevel) {
            return false;
        } else {
            return true;
        }
    }

    private String calculateSuffix(String parameters) {
        String suffix = "";
        while (!((parameters + suffix).length() >= PASS_LENGTH)) {
            for (int i = 0; i < parameters.length(); i++) {
                suffix += parameters.charAt(i);
                if ((parameters + suffix).length() == PASS_LENGTH) {
                    return suffix;
                }
            }
        }
        return suffix;
    }
}
