package sample;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
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

public class AppsInAreaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back_to_menu;

    @FXML
    private TableView<Appliance> power_table;

    @FXML
    private TableColumn<Appliance, String> name_column;

    @FXML
    private TableColumn<Appliance, Integer> power_column;

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 8000);
            Thread.sleep(2000);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("get list apps in power area");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Main.apps = (List<Appliance>) ois.readObject();
            System.out.println("Список приборов из диапазона получен!");
            dos.flush();

            ObservableList<Appliance> areaList = FXCollections.observableArrayList(Main.apps);
            name_column.setCellValueFactory(new PropertyValueFactory<Appliance, String>("NameApp"));
            power_column.setCellValueFactory(new PropertyValueFactory<Appliance, Integer>("PowerApp"));

            power_table.setItems(areaList);
        } catch (Exception e){
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
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
