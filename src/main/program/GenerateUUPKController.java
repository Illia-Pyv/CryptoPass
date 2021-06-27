package main.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.program.java.commands.CommandManager;
import main.program.java.constants.UUPK;

import java.io.IOException;

public class GenerateUUPKController extends Controller {

    public void switchToTitleScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("titleScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToYourUUPKScene(ActionEvent event) {
        try {
            UUPK.getInstance("").setUUPK(generate());
            root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("yourUUPKScene.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String generate() throws IOException {
        return CommandManager.getInstance().run("", CommandManager.Commands.GenerateUUPK);
    }
}
