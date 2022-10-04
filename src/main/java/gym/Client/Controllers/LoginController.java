package gym.Client.Controllers;

import gym.Client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.logging.Logger;

@Component
@Controller
public class LoginController {


    @FXML
    private Label Email;

    @FXML
    private TextField emailtext;

    @FXML
    private Label Contraseña;

    @FXML
    private TextField contrasenatext;

    @FXML
    private Button ingresarboton;

    @FXML
    private Button cancelarboton;

    @FXML
    private Button entrarempresaBoton;

    @FXML
    private Button entrarCDboton;

    @FXML
    private Button Aboton;

    @FXML
    protected void onIngresarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}

    @FXML
    protected void onEntrarEmpresaButtonClick() {

        try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesEmpresa/MainEmpresa.fxml"));
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

    @FXML
    protected void onEntrarCDButtonClick() {}

    @FXML
    protected void onAButtonClick() {}




}