package gym.Client.Controllers.Admin.AdminCentro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Controllers.Centro.RegistrarActividadController;
import gym.Client.Controllers.Empresa.MainEmpresaController;
import gym.Client.Controllers.ErrorController;
import gym.Client.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Controller;

import java.io.DataInput;
import java.io.IOException;

@Controller
public class BuscarCentroController {

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button buscarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onBuscarButtonClick(ActionEvent event) throws IOException, InterruptedException {

        String correo = emailText.getText();
        String centro  = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centrosMail/" + correo).asObject(String.class);
            centro = apiResponse.getBody().toString();
            System.out.println(centro);

            if (!centro.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if");

                System.out.println("Json hecho");

                CentroDeportivoObject centroDeportivoBuscado = mapper.readValue(apiResponse.getBody(), CentroDeportivoObject.class);
                System.out.println(centroDeportivoBuscado);

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(BuscarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/DatosBuscarCentro.fxml"));

                System.out.println("Voy a pasar datos");
                DatosBuscarCentroController datosBuscarCentroController = fxmlLoader.getController();
                System.out.println("Pase controller");
                datosBuscarCentroController.getEmailDatoLabel().setText(centroDeportivoBuscado.getMail());
                datosBuscarCentroController.getNombreDatoLabel().setText(centroDeportivoBuscado.getNombre());
                System.out.println("Pase datos");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("Centro " + centroDeportivoBuscado.getNombre());
                stage.setScene(new Scene(root1));
                stage.show();

            } else {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root1 = (Parent) fxmlLoader.load(BuscarCentroController.class.getResourceAsStream("/gym/Client/Error.fxml"));

                    ErrorController errorController = fxmlLoader.getController();
                    errorController.getErrorLabel().setText("Centro " + correo + " no existe");

                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);

                    stage.setTitle("Error");
                    stage.getIcons().add(new Image("ErrorIcon.png"));
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    System.out.println("Error");
                }

                System.out.println("centro " + correo + " no existe");
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(BuscarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador Centro");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}