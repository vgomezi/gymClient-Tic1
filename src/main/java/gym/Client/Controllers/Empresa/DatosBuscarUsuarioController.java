package gym.Client.Controllers.Empresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.UserLoginObject;
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
public class DatosBuscarUsuarioController {

    @FXML
    private Label emailLabel;

    @FXML
    private Label emailDatoLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nombreDatoLabel;

    @FXML
    private Label apellidoLabel;

    @FXML
    private Label apellidoDatoLabel;

    @FXML
    private Label telefonoLabel;

    @FXML
    private Label telefonoDatoLabel;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(DatosBuscarUsuarioController.class.getResourceAsStream("/formularios/OpcionesEmpresaPrueba/BuscarUsuario.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public Label getEmailDatoLabel() {
        return emailDatoLabel;
    }

    public void setEmailDatoLabel(Label emailDatoLabel) {
        this.emailDatoLabel = emailDatoLabel;
    }

    public Label getNombreDatoLabel() {
        return nombreDatoLabel;
    }

    public void setNombreDatoLabel(Label nombreDatoLabel) {
        this.nombreDatoLabel = nombreDatoLabel;
    }

    public Label getApellidoDatoLabel() {
        return apellidoDatoLabel;
    }

    public void setApellidoDatoLabel(Label apellidoDatoLabel) {
        this.apellidoDatoLabel = apellidoDatoLabel;
    }

    public Label getTelefonoDatoLabel() {
        return telefonoDatoLabel;
    }

    public void setTelefonoDatoLabel(Label telefonoDatoLabel) {
        this.telefonoDatoLabel = telefonoDatoLabel;
    }


}