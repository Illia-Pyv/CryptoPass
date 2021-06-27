package main.program.java.commands;

import main.program.java.constants.Regex;
import main.program.java.core.Enigma;
import main.program.java.core.EnigmaSetup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommandGenerateUUPK implements ICommand {
    private Enigma enigma;
    private static final int RANDOM_STRING_LENGTH = 6;

    public CommandGenerateUUPK() {
        enigma = Enigma.getInstance();
    }

    @Override
    public String execute(String parameters) throws IOException {
        String time = calculateTime();
        enigma.setup(new EnigmaSetup(randomString(RANDOM_STRING_LENGTH, Regex.INPUT_CHAR_LIST), time));
        return enigma.encode(time);
    }

    private String randomString(int stringLength, ArrayList<Character> list) {
        String result = "";
        int max = list.size() - 1;
        int min = 0;
        int random = 0;
        for (int i = 0; i < stringLength; i++) {
            random = (int) (Math.random() * (max - min + 1) + min);
            result += list.get(random);
        }
        return result;
    }

    private String calculateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        return formatter.format(date);
    }
}
