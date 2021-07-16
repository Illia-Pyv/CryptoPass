package main;

import main.program.GUI;
import main.program.java.constants.Regex;
import main.program.java.constants.UUPK;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String currentDir = Regex.ACCOUNT_PATH;
        boolean noFile = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentDir));
            UUPK.getInstance(reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            noFile = true;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        GUI.main(args, noFile);
    }
}
