package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class ActualizarUsuarioController {


    @FXML
    private Label Email;

    @FXML
    private TextField emailtext;

    @FXML
    private Label Nombre;

    @FXML
    private TextField nombretext;

    @FXML
    private Label Apellido;

    @FXML
    private TextField apellidotext;

    @FXML
    private Label Telefono;

    @FXML
    private TextField telefonotext;

    @FXML
    private Label Contraseña;

    @FXML
    private TextField contrasenatext;

    @FXML
    private Button actualizarboton;

    @FXML
    private Button cancelarboton;

    @FXML
    protected void onActualizarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}