package main.program.java;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.program.java.commands.CommandManager;
import main.program.java.commands.CommandManager.Commands;
import main.program.java.constants.Errors;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField inputField;
    @FXML
    private TextField resultField;
    @FXML
    private Label characterCount;
    @FXML
    private Label notification;
    private final CommandManager command = new CommandManager();
    private static final int CHAR_LIMIT = 100;

    public void generatePassword(ActionEvent e)  {
        String input = inputField.getText();
        String result = "";
        try {
            result = command.run(input, Commands.Encode);
        } catch (IOException exception) {
            notificationLabelFade(exception.getMessage());
        }
        resultField.setText(result);
    }

    public void copy(ActionEvent e) {
        String input = resultField.getText();
        String displayText = "";
        try {
            displayText = command.run(input, Commands.Copy);
        } catch (IOException exception) {
            displayText = exception.getMessage();
        }
        notificationLabelFade(displayText);
    }

    public void checkValidInputLength() {
        String textInput = inputField.getText();
        String hundredChars = "";
        if (textInput.length() >= CHAR_LIMIT) {
            for (int i = 0; i < CHAR_LIMIT; i++) {
                hundredChars += textInput.charAt(i);
            }
            inputField.setText(hundredChars);
            inputField.positionCaret(textInput.length());
            notificationLabelFade(Errors.TOO_MANY_PARAMETERS);
        }
        updateCharacterCountLabelText(textInput.length());
    }

    private void updateCharacterCountLabelText(int textLength) {
        String labelText = "/" + CHAR_LIMIT;
        if (textLength > CHAR_LIMIT) {
            labelText = CHAR_LIMIT + labelText;
        } else {
            labelText = textLength + labelText;
        }
        characterCount.setText(labelText);
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
}
