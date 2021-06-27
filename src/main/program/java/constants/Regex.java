package main.program.java.constants;

import java.util.ArrayList;

/**
 * This class contains all the regular expressions needed to construct more
 * complex regular expressions.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Regex {

    /**
     * This regex describes all characters on the keyboard and it does not matter in which amount.
     */
    public static final String ALL_CHARS = ".+";

    /**
     * This regex describes all characters on the keyboard and it does not matter in which amount.
     */
    public static final String SMALL_LETTERS = "[a-z]";

    /**
     * This regex describes all characters on the keyboard and it does not matter in which amount.
     */
    public static final String CAPITAL_LETTERS = "[A-Z]";

    /**
     * This regex describes all characters on the keyboard and it does not matter in which amount.
     */
    public static final String NUMBERS = "[0-9]";

    /**
     *
     */
    public static final ArrayList<Character> INPUT_CHAR_LIST = calculateInputCharList('!','~');

    // private constructor in a utility class
    private Regex() {

    }

    private static ArrayList<Character> calculateInputCharList(char startChar, char endChar) {
        ArrayList<Character> list = new ArrayList<>();
        char character = startChar;
        for (int i = startChar; i < endChar + 1; i++) {
            list.add(Character.valueOf(character));
            character++;
        }
        return list;
    }
}
