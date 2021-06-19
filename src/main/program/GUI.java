package main.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.program.java.constants.UUPK;

public class GUI extends Application {

    private static boolean noFile;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        Scene initialScene;
        String css = this.getClass().getResource("resources/css/ui.css").toExternalForm();

        // Selection of a Scene to show first when the program starts.
        if (noFile || !UUPK.getInstance("").isValid()) {
            root = FXMLLoader.load(getClass().getResource("resources/fxml/titleScene.fxml"));
            initialScene = new Scene(root);
        } else {
            root = FXMLLoader.load(getClass().getResource("resources/fxml/cryptoPass.fxml"));
            initialScene = new Scene(root);
        }
        initialScene.getStylesheets().add(css);

        Image icon = new Image("main/program/resources/images/Logo.jpg");

        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setTitle("Crypto Pass");
        stage.setScene(initialScene);
        stage.show();
    }

    public static void main(String[] args, boolean noFile) {
        GUI.noFile = noFile;
        launch(args);
    }
}
