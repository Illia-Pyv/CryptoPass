package main.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.program.java.constants.UUPK;

import java.io.*;

public class YourUUPKController extends Controller{

    @FXML
    private Label uupk;

    public void initialize() {
        uupk.setText(UUPK.getInstance("").getUUPK());
    }

    public void switchToWelcomeScene(ActionEvent event) throws IOException {
        saveToFile();
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("welcome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
