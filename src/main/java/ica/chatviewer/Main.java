package ica.chatviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage CurrentStage;

    @Override
    public void start(Stage stage) throws IOException {
        CurrentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chat-view.fxml"));
        HelperFunctions.InitImages(this.getClass());
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("ChatViewer");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}