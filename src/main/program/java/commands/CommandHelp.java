package main.program.java.commands;

public class CommandHelp extends Command {

    private static final String COMMAND_NAME = "help";
    private static final String PARAMETER_REGEX = "";
    private static final String PRINTOUT = "";

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandHelp() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public String execute(String parameters) {
        if (matchesRegex(parameters) != -1) {
            return PRINTOUT;
        }
        return null;
    }
}
