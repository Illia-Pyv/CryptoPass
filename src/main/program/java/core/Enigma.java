package main.program.java.core;

import main.program.java.constants.Regex;

public class Enigma {

    private final Rotor r1;
    private final Rotor r2;
    private final Rotor r3;

    // these following parameters are used for the rotation of the rotors
    private int r1Count = 0;
    private int r2Count = 0;
    private int maxCount = 2;

    /**
     * This is the constructor of this class and it creates the three instances of the Rotor class.
     */
    public Enigma() {
        r1 = new Rotor(Regex.ROTOR1_CHARACTERS_IN, Regex.ROTOR1_CHARACTERS_OUT);
        r2 = new Rotor(Regex.ROTOR2_CHARACTERS_IN, Regex.ROTOR2_CHARACTERS_OUT);
        r3 = new Rotor(Regex.ROTOR3_CHARACTERS_IN, Regex.ROTOR3_CHARACTERS_OUT);
    }

    public String encode(String parameters, int posR1, int posR2, int posR3) {
        String result = "";
        r1.setHead(posR1);
        r2.setHead(posR2);
        r3.setHead(posR3);

        result = encodeWord(parameters);
        return result;
    }

    public int getRotorHeadPos(Rotors rotor) {
        switch (rotor) {
            case R1: {
                return r1.getHead();
            }
            case R2: {
                return r2.getHead();
            }
            case R3: {
                return r3.getHead();
            }
            default: {
                return 0;
            }
        }
    }

    private String encodeWord(String parameters) {
        String result = "";
        for (int i = 0; i < parameters.length(); i++) {
            result += String.format("%s", encodeCharacter(parameters.charAt(i)));
        }
        return result;
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

    public enum Rotors {
        /**
         * Rotor 1
         */
        R1,
        /**
         * Rotor 2
         */
        R2,
        /**
         * Rotor 3
         */
        R3
    }
}
