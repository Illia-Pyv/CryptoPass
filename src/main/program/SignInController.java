package main.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.program.java.constants.UUPK;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignInController extends Controller {

    @FXML
    private TextField uupkInput;

    public void switchToTitleScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("titleScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWelcomeScene(ActionEvent event) throws IOException {
        UUPK.getInstance("").setUUPK(uupkInput.getText());
        if (UUPK.getInstance("").isValid()) {
            saveToFile();
            root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("welcome.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
