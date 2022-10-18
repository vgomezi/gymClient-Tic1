package gym.Client.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.http.HttpRequest;


@Controller
public class LoginController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private PasswordField contrasenaText;

    @FXML
    private Button ingresarBoton;


    @FXML
    protected void onIngresarButtonClick(ActionEvent event) {
        String correoElectronico = emailText.getText();
        String contrasena = contrasenaText.getText();

        if (!correoElectronico.isEmpty() && !contrasena.isEmpty()) {
            String role = "";
            try {
                HttpResponse<String> apiResponse = null;

                apiResponse = Unirest.get("http://localhost:8987/api/login/role/" + correoElectronico).asObject(String.class);
                role = apiResponse.getBody().toString();
                System.out.println(role);
                System.out.println(apiResponse);
            }catch (Exception e) {
            }
            switch (role) {
                case "Admin": {

                    Node source = (Node) event.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/MainAdmin.fxml"));

                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.setTitle("Login Admin");
                        stage.getIcons().add(new Image("GymIcon.png"));
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        System.out.println("Error");
                    }
                    break;
                }
                case "Empresa": {
                    Node source = (Node) event.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesEmpresaPrueba/MainEmpresa.fxml"));

                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.setTitle("Login Empresa");
                        stage.getIcons().add(new Image("GymIcon.png"));
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        System.out.println("Error");
                    }

                    break;
                }
                case "Centro Deportivo": {
                    Node source = (Node) event.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesCentroPrueba/MainCentro.fxml"));

                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.setTitle("Login Centro Deportivo");
                        stage.getIcons().add(new Image("GymIcon.png"));
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        System.out.println("Error");
                    }
                    break;
                }
                case "Usuario": {
                    Node source = (Node) event.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/MainUsuario.fxml"));

                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.setTitle("Login Centro Deportivo");
                        stage.getIcons().add(new Image("GymIcon.png"));
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        System.out.println("Error");
                    }
                    break;
                }
                default:
                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/gym/Client/Error.fxml"));

                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.setTitle("Error");
                        stage.getIcons().add(new Image("GymIcon.png"));
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                        System.out.println("Error");
                    }

                    break;
            }

        } else {
            emailText.clear();
            contrasenaText.clear();

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/gym/Client/Error.fxml"));

                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Error");
                stage.getIcons().add(new Image("GymIcon.png"));
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Error");
            }

        }
    }

}