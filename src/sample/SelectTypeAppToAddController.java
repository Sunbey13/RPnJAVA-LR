package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectTypeAppToAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_kitchen_app;

    @FXML
    private Button btn_house_app;

    @FXML
    private Button btn_cars_app;

    @FXML
    private Button back_to_main_menu;

    @FXML
    void initialize() {
        btn_kitchen_app.setOnAction(event -> {
            try {
                btn_kitchen_app.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/addKitchenApp.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene((new Scene(root, 700, 400)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        btn_house_app.setOnAction(event -> {
            try {
                btn_house_app.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/addHouseApp.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene((new Scene(root, 700, 400)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        btn_cars_app.setOnAction(event -> {
            try {
                btn_cars_app.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/addCarsApp.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene((new Scene(root, 700, 400)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        back_to_main_menu.setOnAction(event -> {
            try {
                back_to_main_menu.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/main.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene((new Scene(root, 700, 400)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}