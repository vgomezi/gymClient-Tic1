package gym.Client.Controllers.Admin.AdminCentro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.TipoActividadObject;
import gym.Client.Classes.UserLoginObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.nio.file.Files;


@Controller
public class RegistrarCentroController {


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
    private TextField TipoActividadField;

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
        String contrasena = contrasenaText.getText();
        String tipo = TipoActividadField.getText();
        String imagen = registrarCentroAction(event);

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()) {
            try {
                String json = "";
                String json2 = "";
                String json3 = "";

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    ObjectMapper mapper3 = new ObjectMapper();
                    UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Centro Deportivo");
                    CentroDeportivoObject centroDeportivoObject = new CentroDeportivoObject(userLoginObject, nombre, email, imagen);
                    TipoActividadObject tipoActividadObject = new TipoActividadObject(tipo);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                    json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(centroDeportivoObject);
                    json3 = mapper3.writerWithDefaultPrettyPrinter().writeValueAsString(tipoActividadObject);
                    System.out.println(json);
                    System.out.println(json3);
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                apiResponse = Unirest.post("http://localhost:8987/api/centroDeportivo").header("Content-Type", "application/json").body(json2).asJson();
                apiResponse = Unirest.post("http://localhost:8987/api/tipoactividad").header("Content-Type", "application/json").body(json3).asJson();
                System.out.println("Hecho Centro Deportivo");

                System.out.println("Hecho");

                nombreText.clear();
                emailText.clear();
                contrasenaText.clear();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }
        } else {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error");
            window.setMinWidth(250);

            Label label = new Label();
            label.setText("Los datos no han sido completados correctamente");
            Button closeButton = new Button("Aceptar");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();


        }

        /*String nR = nombre.getText();
        String tR = telefono.getText();
        String dR = direccion.getText();

        if (!nR.isEmpty() && !dR.isEmpty() && !tR.isEmpty()) {
            try {
                Integer tR2 = Integer.parseInt(telefono.getText());
                try {
                    String json = "";

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        ObjectNode rest = mapper.createObjectNode();
                        rest.put("nombre", nR);
                        rest.put("telefono", tR);
                        rest.put("direccion", dR);
                        json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rest);
                    } catch (Exception ignored) {
                    }
                    HttpResponse<JsonNode> apiResponse = null;
                    try {
                        apiResponse = Unirest.post("http://localhost:8987/api/restaurant").header("Content-Type", "application/json").body(json).asJson();

                    } catch (UnirestException el) {
                        throw new RuntimeException(el);
                    }

                    welcomeText.setText("Se creó el restaurante!");
                } catch (Exception e) {
                    e.printStackTrace();
                    welcomeText.setText("Restaurante ya registrado");
                }
                nombre.clear();
                telefono.clear();
                direccion.clear();
            } catch (Exception E) {
                welcomeText.setText("Teléfono no válido");
            }
        } else {
            nombre.clear();
            telefono.clear();
            direccion.clear();
            welcomeText.setText("Error en la entrada de datos");
        }*/
    }

    public String registrarCentroAction(ActionEvent event) {
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



    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        emailText.clear();
        contrasenaText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(RegistrarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));

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