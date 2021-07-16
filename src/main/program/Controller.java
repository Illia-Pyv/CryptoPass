package main.program;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.program.java.constants.Messages;
import main.program.java.constants.Regex;
import main.program.java.constants.UUPK;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Controller {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected static final String fxmlPath = "resources/fxml/";

    protected String calculatePathToFXML(String fxmlFile) {
        return fxmlPath + fxmlFile;
    }

    protected void saveToFile() throws IOException {
        String currentDir = Regex.ACCOUNT_PATH;
        BufferedWriter writer = new BufferedWriter(new FileWriter(currentDir));
        Runtime.getRuntime().exec("attrib +H " + currentDir);
        writer.write(UUPK.getInstance("").getUUPK());
        writer.write("\n" + Messages.WARNING);
        writer.close();
    }
}
