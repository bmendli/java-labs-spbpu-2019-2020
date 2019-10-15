package lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UserInterface extends Application {



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 7");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(
                new File("D:\\Program Files (x86)\\java-labs-spbpu-2019-2020\\src\\main\\java\\lab7\\fxmlflabs\\MainMenu.fxml").toURL()
            );
            TabPane tabPane = fxmlLoader.load();
            Scene scene = new Scene(tabPane, 1300, 850);
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
