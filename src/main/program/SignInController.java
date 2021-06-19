package main.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController extends Controller {

    public void switchToTitleScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("titleScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWelcomeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("welcome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
