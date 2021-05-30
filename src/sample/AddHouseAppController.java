package sample;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class AddHouseAppController {

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
    private Button back_to_menu;

    @FXML
    private CheckBox is_display;

    @FXML
    private CheckBox is_power;

    @FXML
    void initialize() {
        btn_add_app.setOnAction(event -> {
            String onOffApp;
            String appWithDisplay;

            if(is_power.isSelected()){
                onOffApp = "Включен";
            } else {
                onOffApp = "Выключен";
            }

            if(is_display.isSelected()){
                appWithDisplay = "Есть";
            } else {
                appWithDisplay = "Нет";
            }

            try {
                Socket socket = new Socket("localhost", 8000);
                Thread.sleep(2000);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("write houseApp info");
                dos.writeUTF(name_app.getText());
                dos.writeInt(parseInt(power_app.getText()));
                dos.writeUTF(onOffApp);
                dos.writeUTF(appWithDisplay);
                System.out.println("Информация о бытовом приборе отправлена!");
                dos.flush();

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

        back_to_menu.setOnAction(event -> {
            try {
                back_to_menu.getScene().getWindow().hide();

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
