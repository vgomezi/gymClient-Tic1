package gym.Client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.*;

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

        /*
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainEmpresa.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);

                stage.setTitle("Login Empresa");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

         */
    }

    @FXML
    protected void onEntrarCDButtonClick() {}

    @FXML
    protected void onAButtonClick() {}




}