package main.program.java.core;

import main.program.java.constants.Regex;

public class Enigma {

    private static Enigma instance;

    private Rotor r1;
    private Rotor r2;
    private Rotor r3;

    private Enigma() {/*Do nothing*/}

    public static Enigma getInstance() {
        if (instance == null) {
            instance = new Enigma();
        }
        return instance;
    }

    public void setup(EnigmaSetup enigmaSetup) {
        r1 = enigmaSetup.getR1();
        r2 = enigmaSetup.getR2();
        r3 = enigmaSetup.getR3();
        r1.setHead(enigmaSetup.getR1Head());
        r2.setHead(enigmaSetup.getR2Head());
        r3.setHead(enigmaSetup.getR3Head());
    }

    public String encode(String parameters) {
        String result = "";

        result = encodeWord(parameters);
        return result;
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
        int positionOfInputChar = Regex.INPUT_CHAR_LIST.indexOf(character);
        temp = r1.encodeChar(positionOfInputChar);
        rotateRotors();
        temp = r2.encodeChar(temp);
        rotateRotors();
        temp = r3.encodeChar(temp);
        rotateRotors();
        temp = r3.encodeChar(temp);
        rotateRotors();
        temp = r2.encodeChar(temp);
        rotateRotors();
        temp = r1.encodeChar(temp);
        rotateRotors();
        result = Regex.INPUT_CHAR_LIST.get(temp);
        return result;
    }

    private void rotateRotors() {
        r1.rotate();
        if (r1.getHead() == 0) {
            r2.rotate();
        }
        if (r2.getHead() == 0) {
            r3.rotate();
        }
    }
}
