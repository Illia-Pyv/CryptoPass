package main.program.java;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import main.program.java.commands.CommandEncode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.program.java.constants.Errors;
import main.program.java.constants.Messages;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Controller {

    @FXML
    private TextField inputField;
    @FXML
    private TextField resultField;
    @FXML
    private Label characterCount;
    @FXML
    private Label notification;
    private CommandEncode encode = new CommandEncode();
    private static final int CHAR_LIMIT = 100;

    public void generatePassword(ActionEvent e)  {
        String input = inputField.getText();
        String result = encode.execute(input);
        if (result == null) {
            notificationLabelFade(Errors.INVALID_INPUT);
        } else {
            resultField.setText(result);
        }
    }

    public void copy(ActionEvent e) {
        String myString = resultField.getText();
        String displayText = "";
        if (myString != null) {
            if (myString.equals("")) {
                displayText = Messages.NOT_COPIED;
            } else {
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                displayText = Messages.COPIED;
            }
        } else {
            displayText = Messages.NOT_COPIED;
        }
        notificationLabelFade(displayText);
    }

    public void checkValidInput() {
        String textInput = inputField.getText();
        String hundredChars = "";
        if (textInput.length() >= CHAR_LIMIT) {
            for (int i = 0; i < CHAR_LIMIT; i++) {
                hundredChars += textInput.charAt(i);
            }
            inputField.setText(hundredChars);
            inputField.positionCaret(textInput.length());
        }
        updateLabelText(textInput.length());
    }

    private void updateLabelText(int textLength) {
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
