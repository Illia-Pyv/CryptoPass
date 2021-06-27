package main.program.java.core;

import main.program.java.constants.Regex;

import java.util.ArrayList;

public class EnigmaSetup {

    private Rotor r1;
    private Rotor r2;
    private Rotor r3;
    private int r1Head;
    private int r2Head;
    private int r3Head;

    /**
     * This is the constructor of this class.
     *
     * @param uupk is a string with help of which new rotors can be created
     * @param wordToEncode a word which is to be encoded
     */
    public EnigmaSetup(String uupk, String wordToEncode) {
        calculateNewRotors(uupk);
        setRotorHeadPositions(wordToEncode);
    }

    public Rotor getR1() {
        return this.r1;
    }

    public Rotor getR2() {
        return this.r2;
    }

    public Rotor getR3() {
        return this.r3;
    }

    public int getR1Head() {
        return this.r1Head;
    }

    public int getR2Head() {
        return this.r2Head;
    }

    public int getR3Head() {
        return this.r3Head;
    }

    private void calculateNewRotors(String uupk) {
        String in = "";
        String out = "";
        ArrayList<Rotor> rotorList = new ArrayList<>();
        for (int i = 0; i < uupk.length(); i++) {
            char c = uupk.charAt(i);
            if (i % 2 == 0) {
                in = shuffle(c, Regex.INPUT_CHAR_LIST);
            } else {
                out = shuffle(c, Regex.INPUT_CHAR_LIST);
                rotorList.add(new Rotor(in, out));
            }
        }
        setRotors(rotorList);
    }

    private void setRotors(ArrayList<Rotor> list) {
        r1 = list.get(0);
        r2 = list.get(1);
        r3 = list.get(2);
    }

    private String shuffle(char someChar, ArrayList<Character> charList) {
        String result = "";
        int num = someChar;
        int index;
        int iterator = num;
        ArrayList<Character> copy = new ArrayList<>(charList);
        while (!copy.isEmpty()) {
            index = (iterator % copy.size());
            result += copy.get(index);
            num++;
            iterator += num;
            copy.remove(index);
        }
        return result;
    }

    private void setRotorHeadPositions(String parameters) {
        r1Head = calculatePosR1(parameters);
        r2Head = calculatePosR2(parameters);
        r3Head = calculatePosR3(parameters);
    }

    private int calculatePosR1 (String parameters){
        int hash = calculatePositiveHash(parameters);
        return (hash % Regex.INPUT_CHAR_LIST.size());
    }

    private int calculatePosR2 (String parameters){
        int hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters)))));
        return (hash % Regex.INPUT_CHAR_LIST.size());
    }

    private int calculatePosR3 (String parameters){
        int hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters)));
        return (hash % Regex.INPUT_CHAR_LIST.size());
    }

    private int calculatePositiveHash (String parameter){
        int hash = parameter.hashCode();
        if (hash < 0) {
            hash = hash * (-1);
        }
        return hash;
    }
}
