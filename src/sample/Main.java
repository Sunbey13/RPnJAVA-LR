package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    static List<Appliance> apps = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/main.fxml"));
        primaryStage.setTitle("Belousov LR3");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}
