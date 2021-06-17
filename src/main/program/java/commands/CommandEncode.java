package main.program.java.commands;

import main.program.java.constants.Errors;
import main.program.java.core.Enigma;
import main.program.java.constants.Regex;

import java.io.IOException;

public class CommandEncode extends Command {

    protected static final String PARAMETER_REGEX = Regex.INPUT_CHARACTERS;
    protected static final String CHARACTERS = Regex.ROTOR1_CHARACTERS_IN;

    private static final int MAX_DEPTH = 2000;
    private static final int PASS_LENGTH = 16;

    private int depth;


    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandEncode() {
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public String execute(String parameters) throws IOException {
        Enigma enigma = new Enigma();
        this.depth = 0;

        if (matchesRegex(parameters) != -1) {
            return encode(enigma, parameters, calculatePosR1(parameters), calculatePosR2(parameters), calculatePosR3(parameters));
        } else {
            throw new IOException(Errors.INVALID_INPUT);
        }
    }

    private int calculatePosR1 (String parameters){
        long num = calculatePositiveHash(parameters);
        return modulo(num, CHARACTERS.length());
    }

    private int calculatePosR2 (String parameters){
        int num;
        int temp = 0;
        for (int i = 0; i < parameters.length(); i++) {
            temp += parameters.charAt(i);
        }
        num = parameters.length() * temp;
        return modulo(num, CHARACTERS.length());
    }

    private int calculatePosR3 (String parameters){
        long num;
        long hash = calculatePositiveHash(parameters);
        num = calculatePositiveHash(String.format("%s", hash));
        return modulo(num, CHARACTERS.length());
    }

    private int calculatePositiveHash (String parameter){
        int hash = parameter.hashCode();
        if (hash < 0) {
            hash = hash * (-1);
        }
        return hash;
    }

    private int modulo(long num1, long mod) {
        long result;
        long temp = num1 / mod;
        result = num1 - (mod * temp);
        return (int)result;
    }

    private String encode(Enigma enigma, String parameters, int posR1, int posR2, int posR3) {
        String result = "";
        int newPosR1;
        int newPosR2;
        int newPosR3;
        result = enigma.encode(parameters, posR1, posR2, posR3);
        result = result + calculateSuffix(parameters);
        if (!checkIfDivers(result)) {
            if (depth == MAX_DEPTH) {
                return result;
            }
            depth++;
            newPosR1 = enigma.getRotorHeadPos(Enigma.Rotors.R1);
            newPosR2 = enigma.getRotorHeadPos(Enigma.Rotors.R2);
            newPosR3 = enigma.getRotorHeadPos(Enigma.Rotors.R3);
            return encode(enigma, result, newPosR1, newPosR2, newPosR3);
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
