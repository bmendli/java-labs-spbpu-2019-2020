import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 5");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(
                    Paths.get("src", "main", "java", "semesters", "fourth", "lab5", "view","fxml",  "FxmlView.fxml")
                            .toFile()
                            .toURI()
                            .toURL()
            );
            GridPane gridPane = fxmlLoader.load();
            Scene scene = new Scene(gridPane, 1300, 850);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
