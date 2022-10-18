package gym.Client.Controllers.Admin.AdminEmpresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class RegistrarEmpresaController {


    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private PasswordField contrasenaText;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onCrearButtonClick() {
        String nombre = nombreText.getText();
        String email = emailText.getText();
        String contrasena = contrasenaText.getText();

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()) {
            try {
                String json1 = "";
                String json2 = "";

                try {
                    ObjectMapper mapper1 = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    ObjectNode rest1 = mapper1.createObjectNode();
                    ObjectNode rest2 = mapper2.createObjectNode();
                    rest1.put("contrasena", contrasena);
                    rest1.put("mail", email);
                    rest1.put("tipoDeUsuario", "Empresa");
                    rest2.put("nombre", nombre);
                    rest2.put("mail", email);
                    json1 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(rest1);
                    json2 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(rest2);
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse1 = null;
                HttpResponse<JsonNode> apiResponse2 = null;
                try {
                    apiResponse1 = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json1).asJson();
                    System.out.println("Hecho login");
                    System.out.println(json2);
                    apiResponse2 = Unirest.post("http://localhost:8987/api/empresas").header("Content-Type", "application/json").body(json2).asJson();
                    System.out.println("Hecho centro");

                } catch (UnirestException el) {
                    throw new RuntimeException(el);
                }
                System.out.println("Hecho");
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }
        }
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        emailText.clear();
        contrasenaText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}