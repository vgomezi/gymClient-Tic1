package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class CrearUsuarioController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label apellidoLabel;

    @FXML
    private TextField apellidoText;

    @FXML
    private Label telefonoLabel;

    @FXML
    private TextField telefonoText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private TextField contrasenaText;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}