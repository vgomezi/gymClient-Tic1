package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CrearUsuarioController {


    @FXML
    private TextField Email;

    @FXML
    private Label emailtext;

    @FXML
    private TextField Nombre;

    @FXML
    private Label nombretext;

    @FXML
    private TextField Apellido;

    @FXML
    private Label apellidotext;

    @FXML
    private TextField Telefono;

    @FXML
    private Label telefonotext;

    @FXML
    private TextField Contraseña;

    @FXML
    private Label contrasenatext;

    @FXML
    private Button onCrearButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}