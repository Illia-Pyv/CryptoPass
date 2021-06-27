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

    public void setup(EnigmaSetup setup) {
        r1 = setup.getR1();
        r2 = setup.getR2();
        r3 = setup.getR3();
        r1.setHead(setup.getR1Head());
        r2.setHead(setup.getR2Head());
        r3.setHead(setup.getR3Head());
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
        if (r1.getHead() == 0) {
            r2.rotate();
        }
        if (r2.getHead() == 0) {
            r3.rotate();
        }
    }

    private int sendForwardThrough(Rotor rotor, int inputPosition) {
        return rotor.getOutCharactersRelativePos(rotor.getOutChar(rotor.getCharacterByPosition(inputPosition, Rotor.IN), Rotor.IN),Rotor.IN);
    }

    private int sendBackwardThrough(Rotor rotor, int inputPosition) {
        return rotor.getOutCharactersRelativePos(rotor.getOutChar(rotor.getCharacterByPosition(inputPosition, Rotor.OUT), Rotor.OUT),Rotor.OUT);
    }

}
