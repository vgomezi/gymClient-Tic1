package gym.Client.Controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Controllers.Admin.MainAdminController;
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
    private Label errorLabel;


    @FXML
    protected void onIngresarButtonClick(ActionEvent event) {
        String correoElectronico = emailText.getText();
        String contrasena = contrasenaText.getText();

        if (!correoElectronico.isEmpty() && !contrasena.isEmpty()) {
            String role = "";
            try {
                HttpResponse<String> apiResponse = null;

                apiResponse = Unirest.get("http://localhost:8987/api/login/role/" + correoElectronico).asObject(String.class);
                System.out.println(apiResponse.getBody());

                if (apiResponse.getBody().isBlank()) {
                    errorLabel.setText("Correo no registrado");
                } else {
                    System.out.println("Entro else");
                    String contrasenaCorrecta = "false";
                    try {
                        HttpResponse<String> apiResponseP = Unirest.get("http://localhost:8987/api/login/password/" + correoElectronico + "/" + contrasena).asString();
                        contrasenaCorrecta = apiResponseP.getBody();
                        System.out.println(apiResponseP.getBody().toString());
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Error");
                    }
                    if (contrasenaCorrecta.equals("false")) {
                        errorLabel.setText("Contraseña incorrecta");
                    } else {
                        role = apiResponse.getBody();
                        switch (role) {
                            case "Admin": {

                                Node source = (Node) event.getSource();
                                Stage stage1 = (Stage) source.getScene().getWindow();
                                stage1.close();

                                try {

                                    FXMLLoader fxmlLoader = new FXMLLoader();
                                    System.out.println("Entro Admin Login");
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/MainAdmin.fxml"));

                                    MainAdminController mainAdminController = fxmlLoader.getController();
                                    mainAdminController.setUsuarioAdminMain(correoElectronico);

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
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/MainUsuariono.fxml"));

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
                    }
                }
                System.out.println(role);
            }catch (Exception e) {
            }

        } else {
            emailText.clear();
            contrasenaText.clear();
            errorLabel.setText("Datos ingresados incorrectamente");

            /*try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Error.fxml"));

                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Error");
                stage.getIcons().add(new Image("GymIcon.png"));
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Error formulario");
            }*/

        }
    }

}