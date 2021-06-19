package main.program;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected static final String fxmlPath = "resources/fxml/";

    protected String calculatePathToFXML(String fxmlFile) {
        return fxmlPath + fxmlFile;
    }
}
