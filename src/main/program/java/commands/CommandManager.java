package main.program.java.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class manages all of the commands that are needed for further usage of
 * the program.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandManager {

    private HashMap<Commands, ICommand> commandList = new HashMap<>();

    /**
     * This is the constructor of this class. It puts all of the commands into the
     * commandList when this object is created.
     */
    public CommandManager() {
        commandList.put(Commands.Copy, new CommandCopy());
        commandList.put(Commands.Encode, new CommandEncode());
    }

    public String run(String parameters,Commands command) throws IOException {
        String result = "";
        result = commandList.get(command).execute(parameters);
        return result;
    }

    public enum Commands{
        Encode,
        Copy
    }
}
