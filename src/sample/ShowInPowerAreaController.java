package sample;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class ShowInPowerAreaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back_to_menu;

    @FXML
    private TextField min_power;

    @FXML
    private TextField max_power;

    @FXML
    private Button btn_show_apps_in_area;

    @FXML
    void initialize() {
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

        btn_show_apps_in_area.setOnAction(event -> {
            try {
                if (min_power.getText() != "" && max_power.getText() != ""){

                    Socket socket = new Socket("localhost", 8000);
                    Thread.sleep(2000);
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("send min max power");
                    dos.writeInt(parseInt(min_power.getText()));
                    dos.writeInt(parseInt(max_power.getText()));
                    System.out.println("Информация о минимальной и максимальной мощности отправлена!");
                    dos.flush();

                    btn_show_apps_in_area.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/appsInArea.fxml"));

                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene((new Scene(root, 700, 400)));
                    stage.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
