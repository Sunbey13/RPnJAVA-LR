package sample;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Collections;
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

public class SortAppOnPowerController {

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
    private TableColumn<Appliance, String> onOffColumn;

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 8000);
            Thread.sleep(2000);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            oos.writeUTF("get list");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Main.apps = (List<Appliance>) ois.readObject();
            System.out.println("Список приборов получен!");
            oos.flush();

            ObservableList<Appliance> apps = FXCollections.observableArrayList(Main.apps);

            name_column.setCellValueFactory(new PropertyValueFactory<Appliance, String>("NameApp"));
            power_column.setCellValueFactory(new PropertyValueFactory<Appliance, Integer>("PowerApp"));
            onOffColumn.setCellValueFactory(new PropertyValueFactory<Appliance, String>("On"));

            power_table.setItems(apps);
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
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
