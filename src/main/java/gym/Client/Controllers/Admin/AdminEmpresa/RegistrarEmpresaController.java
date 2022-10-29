package gym.Client.Controllers.Admin.AdminEmpresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Classes.UserLoginObject;
import gym.Client.Controllers.Admin.AdminCentro.BuscarCentroController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.http.HttpRequest;
import java.nio.file.Files;


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
    private Label bonoLabel;

    @FXML
    private TextField bonoText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private PasswordField contrasenaText;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onRegistrarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();
        String email = emailText.getText();
        String bono = bonoText.getText();
        String contrasena = contrasenaText.getText();
        String imagen = registrarEmpresaAction(event);

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()) {
            try {
                String json = "";
                String json2 = "";

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Empresa");
                    EmpresaObject empresaObject = new EmpresaObject(userLoginObject, nombre, bono, email, imagen);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                    json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(empresaObject);
                    System.out.println(json);
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                apiResponse = Unirest.post("http://localhost:8987/api/empresas").header("Content-Type", "application/json").body(json2).asJson();
                System.out.println("Hecho Empresa");

                System.out.println("Hecho");

                nombreText.clear();
                emailText.clear();
                bonoText.clear();
                contrasenaText.clear();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }

        } else {
            System.out.println("Pantalla error datos incorrectos");
        }
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        emailText.clear();
        bonoText.clear();
        contrasenaText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(RegistrarEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public String registrarEmpresaAction(ActionEvent event) {
        String base64String = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen centro");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        try {
            //FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            //System.out.println(bytes);
            base64String = Base64.encodeBase64String(bytes);
            //System.out.println(base64String);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return base64String;
    }
}