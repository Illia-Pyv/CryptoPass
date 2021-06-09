package main.program.java.commands;

import main.program.java.constants.Errors;
import main.program.java.core.Enigma;
import main.program.java.constants.Regex;

import java.io.IOException;

public class CommandEncode extends Command {

    private static final String PARAMETER_REGEX = Regex.INPUT_CHARACTERS;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandEncode() {
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public String execute(String parameters) throws IOException {
        Enigma enigma = new Enigma();

        if (matchesRegex(parameters) != -1) {
            return enigma.encode(parameters);
        } else {
            throw new IOException(Errors.INVALID_INPUT);
        }
    }

}
