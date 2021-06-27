package main.program.java.core;

import java.util.ArrayList;

public class Rotor {

    private ArrayList<String> rotorCharacters = new ArrayList<>();
    /**
     * Input pin of this rotor.
     */
    public static final int IN = 0;
    /**
     * Output pin of this rotor.
     */
    public static final int OUT = 1;
    private int head;

    public Rotor(String rotorCharactersIn, String rotorCharactersOut) {
        rotorCharacters.add(IN, rotorCharactersIn);
        rotorCharacters.add(OUT, rotorCharactersOut);
        this.head = 0;
    }

    public void setHead(int position) {
        this.head = position;
    }

    public int getHead() { return this.head; }

    public void rotate() {
        if (this.head == rotorCharacters.get(IN).length() - 1) {
            setHead(0);
        } else {
            setHead(this.head + 1);
        }
    }

    public char getOutChar(char character, int directionOfInput) {
        int in1 = directionOfInput;
        int output = getOut(directionOfInput);
        for (int i = 0; i < rotorCharacters.get(in1).length(); i++) {
            if (rotorCharacters.get(in1).charAt(i) == character) {
                return rotorCharacters.get(output).charAt(i);
            }
        }
        return ' ';
    }

    /**
     * This method calculates the position of a character on this rotor, relative to the position of the head.
     *
     * TODO rework this method, has a few unused variables
     *
     * @return The relative position of the character as an integer
     */
    public int getOutCharactersRelativePos(char character, int directionOfInput) {
        int i = 0;
        int n;
        int input = directionOfInput;
        int output = getOut(directionOfInput);
        char tempChar = rotorCharacters.get(input).charAt(head);
        for (; i < rotorCharacters.get(output).length(); i++) {
            n = head + i;
            if (n > rotorCharacters.get(output).length()-1) {
                tempChar = rotorCharacters.get(input).charAt(n-rotorCharacters.get(output).length());
            }
            tempChar = getCharacterByPosition(n, input);
            if (tempChar == character) {
                return i;
            }
        }
        return i;
    }

    /**
     * This method gets the character at the relative position to the head element.
     *
     * @param position the relative position to the head
     * @return the character of the rotor characters at the relative position to the head
     */
    public char getCharacterByPosition(int position, int directionOfInput) {
        char result;
        int input = directionOfInput;
        int absolutePos = position + head;
        if (absolutePos > rotorCharacters.get(input).length()-1) {
            while (absolutePos > rotorCharacters.get(input).length()-1) {
                absolutePos = absolutePos - rotorCharacters.get(input).length();
            }
            result = rotorCharacters.get(input).charAt(absolutePos);
        } else {
            result = rotorCharacters.get(input).charAt(absolutePos);
        }
        return result;
    }

    private int getOut(int directionOfInput) {
        if (directionOfInput == this.IN) {
            return OUT;
        } else {
            return IN;
        }
    }
}
