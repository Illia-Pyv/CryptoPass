package main;

import main.program.GUI;
import main.program.java.constants.UUPK;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Change the directory where the config file will be saved
        String currentDir = System.getProperty("user.dir") + "/config.txt";
        boolean noFile = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentDir));
            UUPK.getInstance(reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            noFile = true;
            System.out.println("NO file");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println(UUPK.getInstance("").getUUPK());

        GUI.main(args, noFile);
    }
}
