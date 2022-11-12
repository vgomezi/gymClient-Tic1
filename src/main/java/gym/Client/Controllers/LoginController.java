package gym.Client.Controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Controllers.Nuevos.Admin.MainAdminController;
import gym.Client.Controllers.Nuevos.MainCentroRegistrarIngresoUsuarioController;
import gym.Client.Controllers.Nuevos.MainEmpresaTodosUsuariosController;
import gym.Client.Controllers.Nuevos.MainUsuarioTodasActividadesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
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
                                    //Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/MainAdmin.fxml"));
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/nuevo/admin/MainAdmin.fxml"));

                                    MainAdminController mainAdminController = fxmlLoader.getController();
                                    mainAdminController.datosAdmin();

                                    Stage stage = new Stage();

                                    stage.initModality(Modality.APPLICATION_MODAL);

                                    stage.setTitle("Login Admin");
                                    stage.setIconified(false);
                                    stage.setResizable(false);
                                    stage.getIcons().add(new Image("FitnessIcon.png"));
                                    stage.setScene(new Scene(root1));
                                    stage.show();

                                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            System.exit(0);
                                        }
                                    });

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
                                    //Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesEmpresa/MainEmpresa.fxml"));
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/nuevo/MainEmpresaTodosUsuarios.fxml"));

                                    //Parent root1 = FXMLLoader.load(getClass().getResource("/gym/Client/nuevo/MainEmpresaTodosUsuarios.fxml"));
                                    //Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesEmpresa/MainEmpresa.fxml"));

                                    //MainEmpresaController mainEmpresaController = fxmlLoader.getController();
                                    //mainEmpresaController.setUsuarioEmpresaMain(correoElectronico);

                                    MainEmpresaTodosUsuariosController mainEmpresaTodosUsuariosController = fxmlLoader.getController();
                                    mainEmpresaTodosUsuariosController.setEmpresaLogInMail(correoElectronico);
                                    mainEmpresaTodosUsuariosController.datosEmpresa(correoElectronico);
                                    mainEmpresaTodosUsuariosController.empleadosEmpresa();

                                    Stage stage = new Stage();

                                    //stage.initModality(Modality.APPLICATION_MODAL);

                                    stage.setTitle("SENSE FIT");
                                    stage.setIconified(false);
                                    stage.setResizable(false);
                                    stage.getIcons().add(new Image("FitnessIcon.png"));
                                    stage.setScene(new Scene(root1));
                                    stage.show();

                                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            System.exit(0);
                                        }
                                    });

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
                                    //Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesCentro/MainCentro.fxml"));
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/nuevo/MainCentroRegistrarIngresoUsuario.fxml"));

                                    MainCentroRegistrarIngresoUsuarioController mainCentroRegistrarIngresoUsuarioController = fxmlLoader.getController();
                                    mainCentroRegistrarIngresoUsuarioController.datosCentro(correoElectronico);
                                    mainCentroRegistrarIngresoUsuarioController.actividadesProximasCentro();
                                    mainCentroRegistrarIngresoUsuarioController.actividadesCentro();
                                    mainCentroRegistrarIngresoUsuarioController.desplegarInfoActividadSeleccionada(null);


                                   // MainCentroController mainCentroController = fxmlLoader.getController();
                                    //mainCentroController.setUsuarioMainCentro(correoElectronico);

                                    Stage stage = new Stage();

                                    stage.initModality(Modality.APPLICATION_MODAL);

                                    stage.setTitle("Login Centro Deportivo");
                                    stage.getIcons().add(new Image("FitnessIcon.png"));
                                    stage.setScene(new Scene(root1));
                                    stage.show();

                                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            System.exit(0);
                                        }
                                    });

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

                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/nuevo/MainUsuarioTodasActividades.fxml"));

                                    MainUsuarioTodasActividadesController mainUsuarioTodasActividadesController = fxmlLoader.getController();
                                    System.out.println(emailText.getText());
                                    mainUsuarioTodasActividadesController.datosUsuario(correoElectronico);
                                    mainUsuarioTodasActividadesController.actividadesDisponibles();

                                    Stage stage = new Stage();

                                    stage.setTitle("Login USUARIO");
                                    stage.setIconified(false);
                                    //stage.setResizable(false);
                                    stage.getIcons().add(new Image("FitnessIcon.png"));
                                    stage.setScene(new Scene(root1));
                                    stage.show();

                                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            System.exit(0);
                                        }
                                    });

                                } catch (Exception ex) {
                                    System.out.println(ex.toString());
                                    System.out.println("Error");
                                }
                                break;
                            }
                            default:
                                try {

                                    FXMLLoader fxmlLoader = new FXMLLoader();
                                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Error.fxml"));

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

                                break;
                        }
                    }
                }
                System.out.println(role);
            }catch (Exception e) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Error.fxml"));

                    ErrorController errorController = fxmlLoader.getController();
                    errorController.getErrorLabel().setText("Server no responde");

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
            }

        } else {
            emailText.clear();
            contrasenaText.clear();
            errorLabel.setText("Datos ingresados incorrectamente");
        }
    }

    public TextField getEmailText() {
        return emailText;
    }

    public void setEmailText(TextField emailText) {
        this.emailText = emailText;
    }
}