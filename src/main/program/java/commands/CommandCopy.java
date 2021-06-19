package main.program.java.commands;

import main.program.java.constants.Messages;
import main.program.java.constants.Regex;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CommandCopy implements ICommand {

    private static final String PARAMETER_REGEX = Regex.ALL_CHARS;

    @Override
    public String execute(String parameters) {
        if (parameters.matches(PARAMETER_REGEX)) {
            StringSelection stringSelection = new StringSelection(parameters);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            return Messages.COPIED;
        } else {
            return Messages.NOT_COPIED;
        }
    }
}
