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

    /**
     * This method sets the name of a command so that it can be accessed later on.
     * 
     * @param commandName the name of the command which is received as string and is
     *                    to be set
     */
    void setCommandName(String commandName);

    /**
     * This method gets the parameter regex as a string which is located at the
     * position "index" in the list of all parameters regex.
     * 
     * @param index the number which indicates the position in the list where the
     *              parameter regex should be gotten from
     * @return Returns the parameter regex as a string at the position index from
     *         the parameter regex list
     */
    String getParameterRegex(int index);

    /**
     * This method gets the name of the command.
     * 
     * @return Returns the name of the command as a string
     */
    String getCommandName();
}