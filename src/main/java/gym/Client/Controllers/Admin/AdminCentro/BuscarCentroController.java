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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    protected void onBuscarButtonClick(ActionEvent event) throws IOException, InterruptedException {

        //Revisar que pasa si el centro no existe

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








                //System.out.println(centroDeportivo.toString());
                System.out.println(centroDeportivoBuscado.getNombre());
                System.out.println(centroDeportivoBuscado.getMail());
            } else {
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


}