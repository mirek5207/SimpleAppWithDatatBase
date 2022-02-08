package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL resource = this.getClass().getClassLoader().getResource("javafx.fxml");
        Parent root = FXMLLoader.load(resource);
        scene = new Scene(root, 948, 547);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
