package gym.Client.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class LoginController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private TextField contrasenaText;

    @FXML
    private Button ingresarBoton;

    @FXML
    private Button cancelarBoton;


    @FXML
    protected void onIngresarButtonClick() {
        /*String correoElectronico = emailText.getText();
        String contrasena = contrasenaText.getText();*/
/*
        if (!correoElectronico.isEmpty() && !contrasena.isEmpty()) {
            try {
                String json = "";
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode rest = mapper.createObjectNode();


                } catch (Exception e) {
                }
                HttpResponse<JsonNode> apiResponse = null;

                try {
                    apiResponse = Unirest.get("http://localhost:8987/api/login").header("Content-Type", "application/json").asJson();

                } catch (UnirestException el) {
                    throw new RuntimeException(el);
                }
            }catch (Exception e) {

            }

        } else {
            emailText.clear();
            contrasenaText.clear();
        }*/

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * String nR = nombre.getText();
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
        }
*/

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        if (emailText.getText() != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Login Centro");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Error");
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            emailText.clear();
            contrasenaText.clear();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }




}