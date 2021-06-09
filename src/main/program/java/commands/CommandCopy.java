package main.program.java.commands;

import main.program.java.constants.Messages;
import main.program.java.constants.Regex;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CommandCopy extends Command {

    private static final String PARAMETER_REGEX = Regex.ALL_CHARS;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandCopy() {
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public String execute(String parameters) {
        if (matchesRegex(parameters) != -1) {
            StringSelection stringSelection = new StringSelection(parameters);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            return Messages.COPIED;
        } else {
            return Messages.NOT_COPIED;
        }
    }
}
