package gym.Client.Controllers.Usuario;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class DarseBajaUsuarioController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private TextField contrasenaText;

    @FXML
    private Button bajaBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onBajaButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}