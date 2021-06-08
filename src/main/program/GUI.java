package main.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/sample.fxml"));

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("resources/css/ui.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image("main/program/resources/images/Logo.jpg");

        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setTitle("Crypto Pass");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
