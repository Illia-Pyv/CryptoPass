package main.program.java.commands;

import java.io.IOException;

/**
 * This class specifies how the command classes are to be structured.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public interface ICommand {

    /**
     * This method executes the function that the command that called this method
     * should perform.
     *
     * @param parameters  the parameters that the command should parse and execute
     * @return Returns a result of the type Result for easier processing of the
     *         output
     * @throws IOException Throws an exception if the user input was
     *                                    wrong
     */
    String execute(String parameters) throws IOException;

    /**
     * This method sets the multiple regular expressions if needed to store
     * 
     * @param parameterRegex the regex of parameters that has to be set for a
     *                       command
     */
    void setParameterRegex(String parameterRegex);
}
