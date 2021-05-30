package sample;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class AddCarsAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add_app;

    @FXML
    private TextField name_app;

    @FXML
    private TextField power_app;

    @FXML
    private Button btn_back_to_menu;

    @FXML
    private CheckBox is_battery;

    @FXML
    private CheckBox is_power;

    @FXML
    void initialize() {

        btn_add_app.setOnAction(event -> {
            String onOffApp;
            String appWithBattary;

            if(is_power.isSelected()){
                onOffApp = "Включен";
            } else {
                onOffApp = "Выключен";
            }

            if(is_battery.isSelected()){
                appWithBattary = "Есть";
            } else {
                appWithBattary = "Нет";
            }

            try {
                Socket socket = new Socket("localhost", 8000);
                Thread.sleep(2000);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("write carsApp info");
                dos.writeUTF(name_app.getText());
                dos.writeInt(parseInt(power_app.getText()));
                dos.writeUTF(onOffApp);
                dos.writeUTF(appWithBattary);
                System.out.println("Информация об автомобильном приборе отправлена!");
                dos.flush();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            try {
                btn_add_app.getScene().getWindow().hide();

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

        btn_back_to_menu.setOnAction(event -> {
            try {
                btn_back_to_menu.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/selectTypeAppToAdd.fxml"));

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
