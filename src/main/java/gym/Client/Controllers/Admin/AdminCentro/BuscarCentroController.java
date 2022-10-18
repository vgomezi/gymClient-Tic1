package gym.Client.Controllers.Admin.AdminCentro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

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

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rest = mapper.createObjectNode();
            rest.put("mail", emailText.getText());
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rest);
            System.out.println(json);
            HttpRequest request = Unirest.get("http://localhost:8987/api/login/role/" + emailText.getText());
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8987/api/login/role/"  + emailText.getText()).asJson();

            /*ObjectMapper mapper = new ObjectMapper();
            ObjectNode rest = mapper.createObjectNode();
            rest.get(jsonResponse.getBody().toString());*/
            System.out.println(request.getUrl());
            System.out.println(jsonResponse.getBody().toString());
            //System.out.println(request.getBody().toString());
            /*ObjectMapper mapper1 = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    ObjectNode rest1 = mapper1.createObjectNode();
                    ObjectNode rest2 = mapper2.createObjectNode();
                    rest1.put("contrasena", contrasena);
                    rest1.put("mail", email);
                    //rest1.put("tipoDeUsuario", "Centro Deportivo");
                    rest2.put("mail", email);
                    rest2.put("nombre", nombre);

                    json2 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(rest2);*/
            //System.out.println(jsonResponse.getBody().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error");
        }


/*HttpResponse<JsonNode> apiResponse1 = null;
                HttpResponse<JsonNode> apiResponse2 = null;
                try {
                    apiResponse1 = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json1).asJson();
                    apiResponse2 = Unirest.post("http://localhost:8987/api/centroDeportivo").header("Content-Type", "application/json").body(json2).asJson();
                    System.out.println("Hecho apiResponse");

                } catch (UnirestException el) {
                    throw new RuntimeException(el);
                }*/


        System.out.println("Se buscará el centro");
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}