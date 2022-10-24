package gym.Client.Controllers.Admin.AdminCentro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import gym.Client.Classes.CentroDeportivoObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    protected void onBuscarButtonClick() throws IOException, InterruptedException {

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

                CentroDeportivoObject centroDeportivo = mapper.readValue(apiResponse.getBody(), CentroDeportivoObject.class);

                //System.out.println(centroDeportivo.toString());
                System.out.println(centroDeportivo.getNombre());
                System.out.println(centroDeportivo.getMail());
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