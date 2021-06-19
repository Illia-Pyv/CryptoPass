package main.program.java.commands;

import main.program.java.constants.Errors;
import main.program.java.constants.UUPK;
import main.program.java.core.Enigma;
import main.program.java.constants.Regex;

import java.io.IOException;

public class CommandEncode implements ICommand {

    protected static final String PARAMETER_REGEX = Regex.INPUT_CHARACTERS;
    protected static final String CHARACTERS = Regex.ROTOR1_CHARACTERS_IN;

    protected static final int MAX_DEPTH = 2000;
    protected static final int PASS_LENGTH = 16;

    protected int depth;

    @Override
    public String execute(String parameters) throws IOException {
        Enigma enigma = new Enigma();
        this.depth = 0;

        if (parameters.matches(PARAMETER_REGEX)) {
            return encode(enigma, parameters, calculatePosR1(parameters), calculatePosR2(parameters), calculatePosR3(parameters));
        } else {
            throw new IOException(Errors.INVALID_INPUT);
        }
    }

    protected int calculatePosR1 (String parameters){
        long uupkHash = calculatePositiveHash(UUPK.getInstance("").getUUPK());
        long num = calculatePositiveHash(parameters) + uupkHash;
        return modulo(num, CHARACTERS.length());
    }

    protected int calculatePosR2 (String parameters){
        long uupkhash = calculatePositiveHash(String.format("%s", calculatePositiveHash(String.format("%s", calculatePositiveHash(UUPK.getInstance("").getUUPK())))));
        long hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters))))) + uupkhash;
        return modulo(hash, CHARACTERS.length());
    }

    protected int calculatePosR3 (String parameters){
        long uupkHash = calculatePositiveHash(String.format("%s", calculatePositiveHash(UUPK.getInstance("").getUUPK())));
        long hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters))) + uupkHash;
        return modulo(hash, CHARACTERS.length());
    }

    protected int calculatePositiveHash (String parameter){
        int hash = parameter.hashCode();
        if (hash < 0) {
            hash = hash * (-1);
        }
        return hash;
    }

    protected int modulo(long num1, long mod) {
        long result;
        long temp = num1 / mod;
        result = num1 - (mod * temp);
        return (int)result;
    }

    protected String encode(Enigma enigma, String parameters, int posR1, int posR2, int posR3) {
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

    protected boolean checkIfDivers(String encodedMessage) {
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

    protected String calculateSuffix(String parameters) {
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
