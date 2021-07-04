package main.program.java.core;

import java.util.ArrayList;

public class Rotor {

    private ArrayList<ArrayList<Character>> rotorCharacters = new ArrayList<>();
    /**
     * Input pin of this rotor.
     */
    public static final int IN = 0;
    /**
     * Output pin of this rotor.
     */
    public static final int OUT = 1;
    private int head;

    public Rotor(ArrayList<Character> rotorCharactersIn, ArrayList<Character> rotorCharactersOut) {
        rotorCharacters.add(IN, rotorCharactersIn);
        rotorCharacters.add(OUT, rotorCharactersOut);
        this.head = 0;
    }

    public void setHead(int position) {
        this.head = position;
    }

    public int getHead() { return this.head; }

    public void rotate() {
        if (this.head == rotorCharacters.get(IN).size() - 1) {
            setHead(0);
        } else {
            setHead(this.head + 1);
        }
    }

    /**
     * This method sends a character through the rotor and returns the position of the output character on the output pins of this rotor.
     *
     * @param position the position relative to the head of this rotor
     * @return Returns the position relative to the head of this rotor of the encoded character
     */
    public int encodeChar(int position) {
        char outChar = getOutChar(position);
        return getPosByChar(outChar);
    }

    private char getOutChar(int pos) {
        return rotorCharacters.get(Rotor.OUT).get((head + pos) % rotorCharacters.get(Rotor.IN).size());
    }

    private int getPosByChar(char c) {
        int character = rotorCharacters.get(Rotor.IN).indexOf(c);
        int temp;
        if (character < head) {
            temp = character + head;
        } else {
            temp = character - head;
        }
        return (temp) % rotorCharacters.get(Rotor.OUT).size();
    }
}
