package main.program;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.program.java.constants.UUPK;

import java.io.IOException;

public class WelcomeController extends Controller {
    @FXML
    private Label welcome;
    @FXML
    private Label hint;

    public void initialize() {
        welcomeLabelFade();
    }

    private void welcomeLabelFade() {
        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setNode(welcome);
        fadeIn.setDuration(Duration.seconds(3));
        fadeIn.setInterpolator(Interpolator.LINEAR);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        FadeTransition hintIn = new FadeTransition();
        hintIn.setNode(hint);
        hintIn.setDuration(Duration.millis(100));
        hintIn.setInterpolator(Interpolator.LINEAR);
        hintIn.setFromValue(0);
        hintIn.setToValue(1);
        hintIn.setDelay(Duration.seconds(3));
        hintIn.play();
    }

    public void switchToCryptoPassScene(ActionEvent event) throws IOException {
        if (welcome.getOpacity() == 1) {
            root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("cryptoPass.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
