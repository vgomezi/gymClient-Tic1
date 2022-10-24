package gym.Client.Controllers.Empresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.UserLoginObject;
import gym.Client.Controllers.Usuario.MainUsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class VerSaldosController {


    @FXML
    private Button volverBoton;

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainUsuarioController.class.getResourceAsStream("/formularios/OpcionesEmpresaPrueba/MainEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}