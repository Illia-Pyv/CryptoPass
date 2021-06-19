package main.program;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.program.java.commands.CommandManager;
import main.program.java.constants.Errors;
import main.program.java.constants.Regex;
import main.program.java.constants.UUPK;

import java.io.IOException;

public class GenerateUUPKController extends Controller {

    @FXML
    private TextField input;
    @FXML
    private Label notification;
    private static final int MIN_LENGTH = 8;
    private static final int MIN_VARIATION = 2;

    public void switchToTitleScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("titleScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToYourUUPKScene(ActionEvent event) {
        try {
            UUPK.getInstance("").setUUPK(generate(input.getText()));
            root = FXMLLoader.load(getClass().getResource(calculatePathToFXML("yourUUPKScene.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            notificationLabelFade(exception.getMessage());
        }
    }

    public String generate(String userInput) throws IOException {
        isValid(userInput);
        return CommandManager.getInstance().run(userInput, CommandManager.Commands.GenerateUUPK);
    }

    private void notificationLabelFade(String displayText) {
        notification.setText(displayText);
        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setNode(notification);
        fadeIn.setDuration(Duration.millis(300));
        fadeIn.setInterpolator(Interpolator.LINEAR);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setNode(notification);
        fadeOut.setDuration(Duration.millis(300));
        fadeOut.setInterpolator(Interpolator.LINEAR);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(1));
        fadeOut.play();
    }

    private void isValid(String parameters) throws IOException {
        int length = parameters.length();
        int smallLetters = 0;
        int capitalLetters = 0;
        int numbers = 0;
        int special = 0;
        if (length < MIN_LENGTH) {
            throw new IOException(Errors.TOO_SHORT);
        }
        for (int i = 0; i < length; i++) {
            String c = String.format("%s", parameters.charAt(i));
            if (c.matches(Regex.SMALL_LETTERS)) {
                smallLetters++;
            } else if (c.matches(Regex.CAPITAL_LETTERS)) {
                capitalLetters++;
            } else if (c.matches(Regex.NUMBERS)) {
                numbers++;
            } else {
                special++;
            }
        }

        if (smallLetters < MIN_VARIATION || capitalLetters < MIN_VARIATION || numbers < MIN_VARIATION || special < MIN_VARIATION) {
            throw new IOException(Errors.INVALID_INPUT);
        }
    }
}
