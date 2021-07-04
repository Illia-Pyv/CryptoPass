package main.program;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.program.java.commands.CommandManager;
import main.program.java.commands.CommandManager.Commands;
import main.program.java.constants.Errors;

import java.io.IOException;

public class CryptoPassController {

    @FXML
    private TextField inputField;
    @FXML
    private TextField resultField;
    @FXML
    private Label characterCount;
    @FXML
    private Label notification;
    @FXML
    private Tooltip tooltip;
    @FXML
    private Button copyButton;
    private static final int CHAR_LIMIT = 32;
    private static final String CHARACTER_COUNT_LABEL = "/" + CHAR_LIMIT;

    public void initialize() {
        characterCount.setText("0" + CHARACTER_COUNT_LABEL);
    }

    public void onTooltipShown() {
        tooltip.setShowDelay(Duration.millis(300));
        tooltip.setShowDuration(Duration.INDEFINITE);
    }

    public void generatePassword(ActionEvent e)  {
        String input = inputField.getText();
        String result = "";
        notification.setOpacity(0);
        try {
            result = CommandManager.getInstance().run(input, Commands.Encode);
            copyButton.setDisable(false);
        } catch (IOException exception) {
            result = exception.getMessage();
            copyButton.setDisable(true);
        }
        resultField.setText(result);
    }

    public void copy(ActionEvent e) {
        String input = resultField.getText();
        String displayText = "";
        try {
            displayText = CommandManager.getInstance().run(input, Commands.Copy);
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
            if (textInput.length() > CHAR_LIMIT) {
                notificationLabelFade(Errors.TOO_MANY_PARAMETERS);
            }
        }
        updateCharacterCountLabelText(textInput.length());
    }

    private void updateCharacterCountLabelText(int textLength) {
        String labelText = "";
        if (textLength > CHAR_LIMIT) {
            labelText = CHAR_LIMIT + CHARACTER_COUNT_LABEL;
        } else {
            labelText = textLength + CHARACTER_COUNT_LABEL;
        }
        characterCount.setText(labelText);
    }

    private void notificationLabelFade(String displayText) {
        if (notification.getOpacity() == 0) {
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
}
