package sample;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowAllAppsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back_to_menu;

    @FXML
    private TableView<Appliance> allAppsTable;

    @FXML
    private TableColumn<Appliance, String> nameAppColumn;

    @FXML
    private TableColumn<Appliance, Integer> powerAppColumn;

    @FXML
    private TableColumn<Appliance, String> onAppColumn;

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 8000);
            Thread.sleep(2000);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            oos.writeUTF("get list");
            System.out.println("Список приборов получен!");
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Main.apps = (List<Appliance>) ois.readObject();

            ObservableList<Appliance> apps = FXCollections.observableArrayList(Main.apps);

            nameAppColumn.setCellValueFactory(new PropertyValueFactory<Appliance, String>("NameApp"));
            powerAppColumn.setCellValueFactory(new PropertyValueFactory<Appliance, Integer>("PowerApp"));
            onAppColumn.setCellValueFactory(new PropertyValueFactory<Appliance, String>("On"));

            allAppsTable.setItems(apps);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        btn_back_to_menu.setOnAction(event -> {
            try {
                btn_back_to_menu.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/main.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene((new Scene(root, 700, 400)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}