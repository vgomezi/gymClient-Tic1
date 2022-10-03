package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CrearUsuarioController {


    @FXML
    private TextField Email;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField nombretext;

    @FXML
    private TextField Apellido;

    @FXML
    private TextField apellidotext;

    @FXML
    private TextField Telefono;

    @FXML
    private TextField telefonotext;

    @FXML
    private TextField Contraseña;

    @FXML
    private TextField contrasenatext;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}