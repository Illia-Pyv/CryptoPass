package main.program.java.commands;

import main.program.java.core.Enigma;
import main.program.java.constants.Regex;

public class CommandEncode extends Command {

    private static final String COMMAND_NAME = "encode";
    private static final String PARAMETER_REGEX = Regex.INPUT_CHARACTERS;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandEncode() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public String execute(String parameters) {
        Enigma enigma = new Enigma();

        if (matchesRegex(parameters) != -1) {
            return enigma.encode(parameters);
        } else {
            return null;
            // TODO make an error exception with the error Errors.InvalidParameters
        }
    }

}
