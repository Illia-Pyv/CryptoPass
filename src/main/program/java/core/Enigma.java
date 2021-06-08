package main.program.java.core;

import main.program.java.constants.Regex;

import java.util.ArrayList;

public class Enigma {

    private final Rotor r1;
    private final Rotor r2;
    private final Rotor r3;
    private static final int CONSTANT = 42;

    // these following parameters are used for the rotation of the rotors
    private int r1Count = 0;
    private int r2Count = 0;
    private int maxCount = 2;
    private static final int MAX_DEPTH = 2000;
    private static final int PASS_LENGTH = 16;
    private int depth;

    public Enigma() {
        r1 = new Rotor(Regex.ROTOR1_CHARACTERS_IN, Regex.ROTOR1_CHARACTERS_OUT);
        r2 = new Rotor(Regex.ROTOR2_CHARACTERS_IN, Regex.ROTOR2_CHARACTERS_OUT);
        r3 = new Rotor(Regex.ROTOR3_CHARACTERS_IN, Regex.ROTOR3_CHARACTERS_OUT);
        this.depth = 0;
    }

    public String encode(String parameters) {
        String result = "";
        r1.setHead(calculatePositionR1(parameters));
        r2.setHead(calculatePositionR2(parameters));
        r3.setHead(calculatePositionR3(parameters));

        result = encodeWord(parameters);
        return result;
    }

    private String encodeWord(String parameters) {
        String result = "";
        for (int i = 0; i < parameters.length(); i++) {
            result += String.format("%s", encodeCharacter(parameters.charAt(i)));
        }
        result = result + calculateSuffix(parameters);
        if (!checkIfDivers(result)) {
            if (depth == MAX_DEPTH) {
                return result;
            }
            depth++;
            return encodeWord(result);
        }
        return result;
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

    private int calculatePositionR1(String parameters) {
        long num = parameters.hashCode();
        if (num < 0) {
            num = num * (-1);
        }
        return modulo(num, r1.getAmountOfCharacters());
    }

    private int calculatePositionR2(String parameters) {
        int num;
        int temp = 0;
        for (int i = 0; i < parameters.length(); i++) {
            temp += parameters.charAt(i);
        }
        num = parameters.length() * temp;
        return modulo(num, r2.getAmountOfCharacters());
    }

    private int calculatePositionR3(String parameters) {
        int result = 1;
        long num;
        char character;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < parameters.length(); i++) {
            character = parameters.charAt(i);
            if (!list.contains(character)) {
                result++;
                list.add(character);
            }
        }
        num = parameters.length() * result * CONSTANT;
        return modulo(num, r3.getAmountOfCharacters());
    }

    private char encodeCharacter(char character) {
        int temp;
        char result = 0;
        int positionOfInputChar = getPositionOfInputChar(character);
        temp = sendForwardThrough(r1,positionOfInputChar);
        rotateRotors();
        temp = sendForwardThrough(r2,temp);
        rotateRotors();
        temp = sendForwardThrough(r3,temp);
        rotateRotors();
        temp = sendBackwardThrough(r3,temp);
        rotateRotors();
        temp = sendBackwardThrough(r2,temp);
        rotateRotors();
        temp = sendBackwardThrough(r1,temp);
        rotateRotors();
        result = r1.getCharacterByPosition(temp, Rotor.IN);
        return result;
    }

    private void rotateRotors() {
        r1.rotate();
        r1Count++;
        if (r1Count == maxCount) {
            r1Count = 0;
            r2.rotate();
            r2Count++;
        }
        if (r2Count == maxCount) {
            r2Count = 0;
            r3.rotate();
        }
    }

    private int modulo(long num1, long mod) {
        long result;
        long temp = num1 / mod;
        result = num1 - (mod * temp);
        return (int)result;
    }

    private int getPositionOfInputChar(char character) {
        String allChars = Regex.CHARACTERS;
        for (int i = 0; i < allChars.length(); i++) {
            if (character == allChars.charAt(i)) {
                return i;
            }
        }
        return 0;
    }

    private int sendForwardThrough(Rotor rotor, int inputPosition) {
        return rotor.getOutCharactersRelativePos(rotor.getOutChar(rotor.getCharacterByPosition(inputPosition, Rotor.IN), Rotor.IN),Rotor.IN);
    }

    private int sendBackwardThrough(Rotor rotor, int inputPosition) {
        return rotor.getOutCharactersRelativePos(rotor.getOutChar(rotor.getCharacterByPosition(inputPosition, Rotor.OUT), Rotor.OUT),Rotor.OUT);
    }

    private boolean checkIfDivers(String encodedMessage) {
        ArrayList<Character> numbers = new ArrayList<Character>();
        ArrayList<Character> special = new ArrayList<Character>();
        ArrayList<Character> smallLetters = new ArrayList<Character>();
        ArrayList<Character> bigLetters = new ArrayList<Character>();
        int diversityLevel = encodedMessage.length() / 4;
        for (int i = 0; i < encodedMessage.length(); i++) {
            char c = encodedMessage.charAt(i);
            if (getPositionOfInputChar(c) < 26) {
                smallLetters.add(c);
            } else if (getPositionOfInputChar(c) < 52) {
                bigLetters.add(c);
            } else if (getPositionOfInputChar(c) < 62) {
                numbers.add(c);
            } else {
                special.add(c);
            }
        }
        if (numbers.size() < diversityLevel || special.size() < diversityLevel || smallLetters.size() < diversityLevel
                || bigLetters.size() < diversityLevel) {
            return false;
        } else {
            return true;
        }
    }
}
